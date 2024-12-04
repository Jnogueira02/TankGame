package org.example.tankgame1.Environment.Explosion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Explosion {
    private final ImageView imageView;
    private Timeline animation;

    public Explosion(double xPos, double yPos, ExplosionImageCache imageCache){
        imageView = new ImageView();
        imageView.setX(xPos);
        imageView.setY(yPos);
        setupAnimation(imageCache);
    }

    // Add the growing explosion images to an animation to be played
    private void setupAnimation(ExplosionImageCache imageCache){
        animation = new Timeline();
        for(int i = 0; i < 11; i++){
            final int index = i;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 100), e -> imageView.setImage(imageCache.getImage("/images/" + index + ".gif")));
            animation.getKeyFrames().add(frame);
        }
        animation.setCycleCount(1);
        animation.setOnFinished(e -> imageView.setVisible(false));
    }

    public ImageView getImageView(){
        return imageView;
    }

    // Play and display the growing explosion animation
    public void play(){
        imageView.setVisible(true);
        animation.play();
    }
}
