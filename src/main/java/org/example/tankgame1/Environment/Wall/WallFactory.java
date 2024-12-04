package org.example.tankgame1.Environment.Wall;

public class WallFactory {
    public Wall createWall(double xPos, double yPos, double width, double height){
        return new Wall(xPos, yPos, width, height);
    }
}
