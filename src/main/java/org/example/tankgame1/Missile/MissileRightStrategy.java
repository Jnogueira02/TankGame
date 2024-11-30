package org.example.tankgame1.Missile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MissileRightStrategy implements MissileStrategy{
    @Override
    public void setMissileImage(ImageView imageView) {
        imageView.setImage(new Image(getClass().getResource("/images/missileR.gif").toExternalForm()));
    }

    @Override
    public void updatePosition(double speed, double[] pos) {
        pos[0] += speed;    }
}