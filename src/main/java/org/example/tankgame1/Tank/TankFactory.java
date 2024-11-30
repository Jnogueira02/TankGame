package org.example.tankgame1.Tank;

import org.example.tankgame1.Environment.GameEnvironment;

public class TankFactory {
    public Tank createTank(double xPos, double yPos, GameEnvironment gameEnvironment){
        return new Tank(xPos, yPos, gameEnvironment);
    }
}
