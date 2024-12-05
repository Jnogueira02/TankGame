package org.example.tankgame1.Environment.Image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Singleton Class
public class ImageViewFactory {
    private static ImageViewFactory instance;
    private final ImageFactory imageFactory;

    private ImageViewFactory(){
        imageFactory = ImageFactory.getInstance();
    }

    public static ImageViewFactory getInstance(){
        if(instance == null){
            instance = new ImageViewFactory();
        }
        return instance;
    }

    // Create and return an ImageView given various inputs (path version)
    public ImageView createImageView(String path, double xPos, double yPos, double width, double height){
        Image image = imageFactory.createImage(path);
        ImageView imageView = new ImageView(image);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }

    // Create and return an ImageView given various inputs (image version)
    public ImageView createImageView(Image image, double xPos, double yPos, double width, double height){
        ImageView imageView = new ImageView(image);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }
}
