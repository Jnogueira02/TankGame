package org.example.tankgame1.Environment;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
    private Rectangle rectangle;

    public Wall(double xPos, double yPos, double width, double height){
        rectangle = new Rectangle(xPos, yPos, width, height);
        rectangle.setFill(Color.GRAY);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public double getX(){
        return rectangle.getX();
    }
    public double getY(){
        return rectangle.getY();
    }
}
