package org.example.tankgame1.Environment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Explosion {
    private ImageView imageView;
    private Timeline animation;

    public Explosion(double xPos, double yPos){
        imageView = new ImageView();
        imageView.setX(xPos);
        imageView.setY(yPos);
        setupAnimation();
    }

    private void setupAnimation(){
        Image[] frames = {
            new Image(getClass().getResource("/images/0.gif").toExternalForm()),
            new Image(getClass().getResource("/images/1.gif").toExternalForm()),
            new Image(getClass().getResource("/images/2.gif").toExternalForm()),
            new Image(getClass().getResource("/images/3.gif").toExternalForm()),
            new Image(getClass().getResource("/images/4.gif").toExternalForm()),
            new Image(getClass().getResource("/images/5.gif").toExternalForm()),
            new Image(getClass().getResource("/images/6.gif").toExternalForm()),
            new Image(getClass().getResource("/images/7.gif").toExternalForm()),
            new Image(getClass().getResource("/images/8.gif").toExternalForm()),
            new Image(getClass().getResource("/images/9.gif").toExternalForm()),
            new Image(getClass().getResource("/images/10.gif").toExternalForm()),
        };

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
