package controller;


import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Gallery;
import model.Photo;

import javafx.scene.image.ImageView;

public class GalleryController {

    private Gallery galleryModel;
    @FXML
    private TextField imageNameField;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView imagesListView;

    @FXML
    public void initialize() {
            // TODO additional FX controls initialization
//        imagesListView
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        bindSelectedPhoto(gallery.getPhotos().get(0));
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        // TODO view <-> model bindings configuration
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
        imageNameField.textProperty().bind(selectedPhoto.nameProperty());
    }
}

