package org.example.tankgame1.Environment;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageFactory {
    public Image createImage(String filePath){
        return new Image(Objects.requireNonNull(getClass().getResource(filePath)).toExternalForm());
    }
}
