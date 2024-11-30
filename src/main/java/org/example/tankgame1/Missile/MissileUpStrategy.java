package org.example.tankgame1.Missile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MissileUpStrategy implements MissileStrategy{
    @Override
    public void setMissileImage(ImageView imageView) {
        imageView.setImage(new Image(getClass().getResource("/images/missileU.gif").toExternalForm()));
    }

    @Override
    public void updatePosition(double speed, double[] pos) {
        pos[1] -= speed;    }
}
