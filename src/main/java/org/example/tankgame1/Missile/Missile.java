package org.example.tankgame1.Missile;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.example.tankgame1.Environment.Explosion;
import org.example.tankgame1.Environment.ExplosionFactory;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.Wall;
import org.example.tankgame1.Tank.UserTank;

public class Missile {
    private final double SPEED = 10;
    private double xPos, yPos;
    private ImageView imageView;
    private MissileStrategy strategy;
    private GameEnvironment gameEnvironment;
    private ExplosionFactory explosionFactory;

    public Missile(UserTank tank) {
        this.xPos = tank.getXPos();
        this.yPos = tank.getYPos();
        this.gameEnvironment = tank.getGameEnvironment();
        this.imageView = new ImageView();
        imageView.setX(xPos);
        imageView.setY(yPos);

        // Strategy setup based on tank's direction
        switch (tank.getDirection()) {
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
        double[] pos = {xPos, yPos};
        strategy.updatePosition(SPEED, pos);
        xPos = pos[0];
        yPos = pos[1];
        if(checkCollisionWithWall()){
            triggerExplosion();
            imageView.setVisible(false);
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

    private void triggerExplosion(){
//        Explosion explosion = new Explosion(xPos, yPos, imageCache);
        Explosion explosion = explosionFactory.createExplosion(xPos, yPos);
        gameEnvironment.getGamePane().getChildren().add(explosion.getImageView());
        explosion.play();
    }

    public ImageView getImageView() {
        return imageView;
    }
}
