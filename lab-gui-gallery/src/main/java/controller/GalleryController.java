package controller;


import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Gallery;
import model.Photo;

import javafx.scene.image.ImageView;
import util.PhotoDownloader;

public class GalleryController {

    private Gallery galleryModel;
    @FXML
    private TextField imageNameField;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView<Photo> imagesListView;
    @FXML
    private TextField searchTextField;
    @FXML
    private ProgressIndicator progressIndicator;
    public static BooleanProperty searching=new SimpleBooleanProperty(false);
    @FXML
    public void initialize() {
            // TODO additional FX controls initialization
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });
        imagesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=null){
                unBindOldPhoto(oldValue);
            }
            bindSelectedPhoto(newValue);
        });
        progressIndicator.visibleProperty().bind(searchingProperty());
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        // TODO view <-> model bindings configuration
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
        imageNameField.textProperty().bindBidirectional(selectedPhoto.nameProperty());
    }

    private void unBindOldPhoto(Photo oldPhoto) {
        imageNameField.textProperty().unbindBidirectional(oldPhoto.nameProperty());
    }

    public void searchButtonClicked(ActionEvent event) {
        PhotoDownloader photoDownloader=new PhotoDownloader();
        galleryModel.clear();
        setSearching(true);
        photoDownloader.searchForPhotos(searchTextField.textProperty().getValue())
                .subscribe(photo -> {galleryModel.addPhoto(photo);
                            System.out.println(isSearching());},
                        throwable -> {
                            // Obsługa błędów
                            System.err.println("Błąd: " + throwable.getMessage());
                        },
                        () -> {
                            // To zostanie wykonane po zakończeniu subskrypcji
                            System.out.println("Zakończono subskrypcję");
                            setSearching(false);
                        });
//        System.out.println(searchTextField.textProperty().getValue());
    }

    public static BooleanProperty searchingProperty() {
        return searching;
    }
    public static boolean isSearching() {
        return searching.get();
    }

    public void setSearching(boolean value) {
        searching.set(value);
    }

}

