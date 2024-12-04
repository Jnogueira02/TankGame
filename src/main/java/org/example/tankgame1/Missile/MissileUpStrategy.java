package org.example.tankgame1.Missile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.tankgame1.Environment.ImageFactory;

public class MissileUpStrategy implements MissileStrategy{
    ImageFactory imageFactory = ImageFactory.getInstance();
    @Override
    public void setMissileImage(ImageView imageView) {
        imageView.setImage(imageFactory.createImage("/images/missileU.gif"));
    }

    @Override
    public void updatePosition(double speed, double[] pos) {
        pos[1] -= speed;    }
}
