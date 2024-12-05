package org.example.tankgame1.Missile.StrategyPattern;

import javafx.scene.image.ImageView;
import org.example.tankgame1.Environment.Image.ImageFactory;

// Strategy for upwards missiles
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
