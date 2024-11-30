package org.example.tankgame1.Missile;

import javafx.scene.image.ImageView;

public interface MissileStrategy {
    void setMissileImage(ImageView imageView);
    void updatePosition(double speed, double[] pos);}
