package org.example.tankgame1.Missile.StrategyPattern;

import javafx.scene.image.ImageView;

// Strategy Pattern
public interface MissileStrategy {
    void setMissileImage(ImageView imageView);
    void updatePosition(double speed, double[] pos);}
