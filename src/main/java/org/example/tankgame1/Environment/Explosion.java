package org.example.tankgame1.Environment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Explosion {
    private ImageView imageView;
    private Timeline animation;

    public Explosion(double xPos, double yPos, ExplosionImageCache imageCache){
        imageView = new ImageView();
        imageView.setX(xPos);
        imageView.setY(yPos);
        setupAnimation(imageCache);
    }

    private void setupAnimation(ExplosionImageCache imageCache){
        Image[] frames = new Image[11];
        for(int i = 0; i <= 10; i++){
            frames[i] = imageCache.getImage("/images/" + i + ".gif");
        }

        animation = new Timeline();
        for(int i = 0; i < frames.length; i++){
            final int index = i;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 100), e -> imageView.setImage(frames[index]));
            animation.getKeyFrames().add(frame);
        }
        animation.setCycleCount(1);
        animation.setOnFinished(e -> imageView.setVisible(false));
    }

    public ImageView getImageView(){
        return imageView;
    }

    public void play(){
        imageView.setVisible(true);
        animation.play();
    }
}
