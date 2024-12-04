package org.example.tankgame1.Tank;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.MedPack.MedPack;
import org.example.tankgame1.Environment.Wall.Wall;

public abstract class Tank {
    public final double SPEED = 5;
    private double xPos, yPos;
    private ImageView imageView;
    private final Image tankUp, tankDown, tankLeft, tankRight;
    private Direction direction = Direction.UP;
    private int health = 3;
    private GameEnvironment gameEnvironment;

    public Tank(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.gameEnvironment = GameEnvironment.getInstance();

        // Load the tank images
        tankUp = new Image(getClass().getResource("/images/tankU.gif").toExternalForm());
        tankDown = new Image(getClass().getResource("/images/tankD.gif").toExternalForm());
        tankLeft = new Image(getClass().getResource("/images/tankL.gif").toExternalForm());
        tankRight = new Image(getClass().getResource("/images/tankR.gif").toExternalForm());

        // Initialize the ImageView
        imageView = new ImageView(tankUp);
        imageView.setX(xPos);
        imageView.setY(yPos);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public Direction getDirection() {
        return direction;
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public UserTank getUserTank(){
        return gameEnvironment.getUserTank();
    }

    public void moveUp(){
        double newY = yPos - SPEED;
        if((yPos > 0) && (checkCollision(xPos, newY))) {
            yPos = newY;
            updatePosition();
        }
        imageView.setImage(tankUp);
        direction = Direction.UP;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveDown(){
        double newY = yPos + SPEED;
        if((yPos < 390) && (checkCollision(xPos, newY))) {
            yPos = newY;
            updatePosition();
        }
        imageView.setImage(tankDown);
        direction = Direction.DOWN;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveLeft(){
        double newX = xPos - SPEED;
        if((xPos > 0) && (checkCollision(newX, yPos))) {
            xPos = newX;
            updatePosition();
        }
        imageView.setImage(tankLeft);
        direction = Direction.LEFT;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveRight(){
        double newX = xPos + SPEED;
        if((xPos < 740) && (checkCollision(newX, yPos))) {
            xPos = newX;
            updatePosition();
        }
        imageView.setImage(tankRight);
        direction = Direction.RIGHT;

        checkMedPackCollision(xPos, yPos);

    }

    public void updatePosition(){
        imageView.setX(xPos);
        imageView.setY(yPos);
    }

    public boolean checkCollision(double newX, double newY) {
        Rectangle tankBounds = new Rectangle(newX, newY, imageView.getFitWidth(), imageView.getFitHeight());
        for(Wall wall : gameEnvironment.getWalls()) {
            if (tankBounds.intersects(wall.getRectangle().getX(), wall.getRectangle().getY(),
                    wall.getRectangle().getWidth(), wall.getRectangle().getHeight())) {
                return false;
            }
        }
        return true;
    }

    public void checkMedPackCollision(double newX, double newY){
        Rectangle tankBounds = new Rectangle(newX, newY, imageView.getFitWidth(), imageView.getFitHeight());
        for(MedPack medPack : gameEnvironment.getMedPacks()){
            if(tankBounds.intersects(medPack.getXPos(), medPack.getYPos(),
                    medPack.getWidth(), medPack.getHeight())){
                medPack.applyEffect(this);
                return;
            }
        }
    }

    // When hit by missile
    public void takeDamage(){
        health--;
        if(health == 0)
            destroy();
    }

    public void repair(){
        if (health < 3)
            health++;
    }

    // Destroy tank
    private void destroy(){
        gameEnvironment.getGamePane().getChildren().remove(imageView); //LOD!!!!!!!!!!!!!!!!!!
        gameEnvironment.removeTank(this);
        if(this instanceof EnemyTank){
            gameEnvironment.removeEnemyTank((EnemyTank) this);
        }
    }

}
