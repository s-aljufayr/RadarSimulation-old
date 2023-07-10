package com.example.chattingapp.Infantry;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class MapViewController {
    @FXML
    private WebView mapWebView;

    public MapViewController() {
    }

    @FXML
    public void initialize() {
        // Here you can add your link
        mapWebView.getEngine().load("https://www.google.com/maps/@24.8208935,46.6054987,14.98z?authuser=0&entry=ttu");
    }
}
