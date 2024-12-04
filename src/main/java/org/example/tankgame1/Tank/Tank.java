package org.example.tankgame1.Tank;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.Image.ImageFactory;
import org.example.tankgame1.Environment.Image.ImageViewFactory;
import org.example.tankgame1.Environment.MedPack.MedPack;
import org.example.tankgame1.Environment.Wall.Wall;
import org.example.tankgame1.Tank.Health.HealthObservable;

public abstract class Tank {
    public final double SPEED = 5;
    private double xPos, yPos;
    private final ImageView imageView;
    private final Image tankUp, tankDown, tankLeft, tankRight;
    private Direction direction = Direction.UP;
    private final double height = 40;
    private final double width = 40;
    private double health = 3;
    private final GameEnvironment gameEnvironment = GameEnvironment.getInstance();

    public Tank(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;

        // Load the tank images
        ImageFactory imageFactory = ImageFactory.getInstance();
        tankUp = imageFactory.createImage("/images/tankU.gif");
        tankDown = imageFactory.createImage("/images/tankD.gif");
        tankLeft = imageFactory.createImage("/images/tankL.gif");
        tankRight = imageFactory.createImage("/images/tankR.gif");

        // Initialize the ImageView
        ImageViewFactory imageViewFactory = ImageViewFactory.getInstance();
        imageView = imageViewFactory.createImageView(tankUp, xPos, yPos, width, height);
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

    public double getHealth(){
        return health;
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public UserTank getUserTank(){
        return gameEnvironment.getUserTank();
    }

    public void moveUp(){
        double newY = yPos - SPEED;
        if((yPos > 0) && (checkWallCollision(xPos, newY))) {
            yPos = newY;
            updatePosition();
        }
        imageView.setImage(tankUp);
        direction = Direction.UP;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveDown(){
        double newY = yPos + SPEED;
        if((yPos < 390) && (checkWallCollision(xPos, newY))) {
            yPos = newY;
            updatePosition();
        }
        imageView.setImage(tankDown);
        direction = Direction.DOWN;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveLeft(){
        double newX = xPos - SPEED;
        if((xPos > 0) && (checkWallCollision(newX, yPos))) {
            xPos = newX;
            updatePosition();
        }
        imageView.setImage(tankLeft);
        direction = Direction.LEFT;

        checkMedPackCollision(xPos, yPos);
    }

    public void moveRight(){
        double newX = xPos + SPEED;
        if((xPos < 740) && (checkWallCollision(newX, yPos))) {
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

    public boolean checkWallCollision(double newX, double newY) {
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
        Rectangle tankBounds = new Rectangle(newX, newY, width, height);
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

    public void setHealth(double health){
        this.health = health;
    }


    // Destroy tank
    public void destroy(){
        gameEnvironment.getChildren().remove(imageView);
        gameEnvironment.removeTank(this);
        if(this instanceof EnemyTank){
            gameEnvironment.removeEnemyTank((EnemyTank) this);
        }
    }

}
