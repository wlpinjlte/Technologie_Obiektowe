package app;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            Observable<Photo> downloadedExamples = photoDownloader.getPhotoExamples();
            downloadedExamples
                    .compose(this::processPhotos)
                    .subscribe(photoSerializer::savePhoto);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query) throws IOException, InterruptedException {
        Observable<Photo> observable=photoDownloader.searchForPhotos(query);
        observable
                .doOnError(error -> {
                    // Tutaj możesz obsłużyć błąd, jeśli to konieczne
                    // Na przykład, zalogować go
                    System.err.println("Błąd podczas pobierania zdjęć: " + error.getMessage());
                })
                .compose(this::processPhotos)
                .subscribe(photoSerializer::savePhoto);
    }

    public void downloadPhotosForMultipleQueries(List<String> queries) throws IOException, InterruptedException {
        Observable<Photo> observable=photoDownloader.searchForPhotos(queries);
        observable
                .onErrorResumeNext(error -> {
                    // Tutaj możesz obsłużyć błąd, na przykład zalogować go
                    System.err.println("Błąd podczas pobierania zdjęć: " + error.getMessage());

                    // Zwracamy nowy obserwator lub inny obserwowalny
                    return Observable.empty();
                })
                .compose(this::processPhotos)
                .subscribe(photoSerializer::savePhoto);
    }
    public Observable<Photo> processPhotos(Observable<Photo> photoObservable){
        return photoObservable.filter(photoProcessor::isPhotoValid).map(photoProcessor::convertToMiniature);
    }
}
