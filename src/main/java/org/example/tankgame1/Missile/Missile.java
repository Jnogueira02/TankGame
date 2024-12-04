package org.example.tankgame1.Missile;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.example.tankgame1.Environment.Explosion.Explosion;
import org.example.tankgame1.Environment.Explosion.ExplosionFactory;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.Wall.Wall;
import org.example.tankgame1.Tank.Tank;

public class Missile {
    private final double SPEED = 10;
    private boolean isActive;
    private double xPos, yPos;
    private final ImageView imageView;
    private MissileStrategy strategy;
    private final Tank shooterTank;
    private final GameEnvironment gameEnvironment;
    private final ExplosionFactory explosionFactory;

    public Missile(Tank shooterTank) {
        this.shooterTank = shooterTank;
        this.xPos = shooterTank.getXPos();
        this.yPos = shooterTank.getYPos();
        this.isActive = true;
        this.gameEnvironment = shooterTank.getGameEnvironment();
        this.imageView = new ImageView();
        imageView.setX(xPos);
        imageView.setY(yPos);

        // Strategy setup based on tank's direction
        switch (shooterTank.getDirection()) {
            case UP -> strategy = new MissileUpStrategy();
            case DOWN -> strategy = new MissileDownStrategy();
            case LEFT -> strategy = new MissileLeftStrategy();
            case RIGHT -> strategy = new MissileRightStrategy();
        }

        strategy.setMissileImage(imageView);

        // Initialize explosion factory (POSSIBLE REFACTOR to singleton)
        this.explosionFactory = new ExplosionFactory();
    }

    public void move() {
        if(!isActive){
            return;
        }

        double[] pos = {xPos, yPos};
        strategy.updatePosition(SPEED, pos);
        xPos = pos[0];
        yPos = pos[1];
        if(checkCollisionWithWall() || checkCollisionWithTank()){
            triggerExplosion();
            imageView.setVisible(false);
            isActive = false;
            return;
        }
        updatePosition();
    }

    private void updatePosition() {
        imageView.setX(xPos);
        imageView.setY(yPos);
    }

    private boolean checkCollisionWithWall(){
        Rectangle missileBounds = new Rectangle(xPos, yPos, imageView.getFitWidth(), imageView.getFitHeight());
        for(Wall wall : gameEnvironment.getWalls()){
            if(missileBounds.intersects(wall.getRectangle().getBoundsInParent())){
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionWithTank(){
        Rectangle missileBounds = new Rectangle(xPos, yPos, imageView.getFitWidth(), imageView.getFitHeight());
        for (Tank tank : gameEnvironment.getTanks()) {
            if (tank != shooterTank && missileBounds.intersects(tank.getImageView().getBoundsInParent())) {
                tank.takeDamage();
                return true;
            }
        }
        return false;
    }

    private void triggerExplosion(){
        Explosion explosion = explosionFactory.createExplosion(xPos, yPos);
        gameEnvironment.getGamePane().getChildren().add(explosion.getImageView());
        explosion.play();
    }

    public boolean isActive() {
        return isActive;
    }

    private boolean isOutOfBounds() {
        return xPos < 0 || xPos > gameEnvironment.getGamePane().getWidth() ||
                yPos < 0 || yPos > gameEnvironment.getGamePane().getHeight();
    }

    public ImageView getImageView() {
        return imageView;
    }
}
