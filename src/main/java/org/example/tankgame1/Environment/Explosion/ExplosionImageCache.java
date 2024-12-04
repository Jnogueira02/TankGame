package org.example.tankgame1.Environment.Explosion;

import javafx.scene.image.Image;
import org.example.tankgame1.Environment.ImageFactory;

import java.util.HashMap;
import java.util.Map;

// Singleton Class
public class ExplosionImageCache {
    private static ExplosionImageCache instance;
    private final Map<String, Image> imageMap;
    private final ImageFactory imageFactory;

    private ExplosionImageCache() {
        imageMap = new HashMap<>();
        imageFactory = ImageFactory.getInstance();
        loadImages();
    }

    public static ExplosionImageCache getInstance() {
        if (instance == null) {
            instance = new ExplosionImageCache();
        }
        return instance;
    }

    private void loadImages() {
        // Load all your images here
        String basePath = "/images/";
        for (int i = 0; i <= 10; i++) {
            String path = basePath + i + ".gif";
            imageMap.put(path, imageFactory.createImage(path));
        }
    }

    public Image getImage(String path) {
        return imageMap.get(path);
    }
}

