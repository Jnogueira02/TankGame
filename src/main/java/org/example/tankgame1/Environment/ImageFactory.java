package org.example.tankgame1.Environment;

import javafx.scene.image.Image;

import java.util.Objects;

// Singleton Class
public class ImageFactory {
    private static ImageFactory instance;

    private ImageFactory(){}
    
    public static ImageFactory getInstance(){
        if (instance == null){
            instance = new ImageFactory();
        }
        return instance;
    }
    public Image createImage(String filePath){
        return new Image(Objects.requireNonNull(getClass().getResource(filePath)).toExternalForm());
    }


}
