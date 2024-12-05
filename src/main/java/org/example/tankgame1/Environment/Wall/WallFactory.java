package org.example.tankgame1.Environment.Wall;

// Singleton Class
public class WallFactory {
    private static WallFactory instance;

    private WallFactory(){}

    public static WallFactory getInstance(){
        if(instance == null){
            instance = new WallFactory();
        }
        return instance;
    }

    public Wall createWall(double xPos, double yPos, double width, double height){
        return new Wall(xPos, yPos, width, height);
    }
}
