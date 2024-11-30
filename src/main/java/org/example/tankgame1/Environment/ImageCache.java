package org.example.tankgame1.Environment;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static ImageCache instance;
    private Map<String, Image> imageMap;

    private ImageCache() {
        imageMap = new HashMap<>();
        loadImages();
    }

    public static ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    private void loadImages() {
        // Load all your images here
        String basePath = "/images/";
        for (int i = 0; i <= 10; i++) {
            String path = basePath + i + ".gif";
            imageMap.put(path, new Image(getClass().getResource(path).toExternalForm()));
        }
    }

    public Image getImage(String path) {
        return imageMap.get(path);
    }
}

