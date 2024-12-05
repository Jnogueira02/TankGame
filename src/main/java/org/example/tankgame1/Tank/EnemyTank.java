package org.example.tankgame1.Tank;

import org.example.tankgame1.Missile.Missile;
import org.example.tankgame1.Missile.MissileFactory;

public class EnemyTank extends Tank{
    private final UserTank userTank;
    private static final double MIN_DISTANCE = 100; // The enemy tank must keep this distance from the player
    private long lastDirectionChangeTime = 0;
    private long lastShotTime = 0;
    private static final long DIRECTION_CHANGE_COOLDOWN = 200;
    private static final long SHOOTING_COOLDOWN = 3000;
    private final MissileFactory missileFactory;

    public EnemyTank(double xPos, double yPos) {
        super(xPos, yPos);
        this.userTank = getUserTank();
        this.missileFactory = MissileFactory.getInstance();
    }

    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDirectionChangeTime < DIRECTION_CHANGE_COOLDOWN) {
            return; // Skip the move logic if the cooldown period has not elapsed
        }

        double dx = userTank.getXPos() - this.getXPos();
        double dy = userTank.getYPos() - this.getYPos();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > MIN_DISTANCE) {
            // Normalize the direction
            dx /= distance;
            dy /= distance;

            // Move towards the user
            moveInDirection(dx, dy);
        } else {
            // Too close, choose to wander or stop
            wander();
        }
        lastDirectionChangeTime = currentTime;
    }

    private void moveInDirection(double dx, double dy) {
        double newX = this.getXPos() + dx * SPEED;
        double newY = this.getYPos() + dy * SPEED;

        // Attempt move if no collision
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && checkWallCollision(newX, this.getYPos())) {
                moveRight();
            } else if (dx < 0 && checkWallCollision(newX, this.getYPos())) {
                moveLeft();
            }
        } else {
            if (dy > 0 && checkWallCollision(this.getXPos(), newY)) {
                moveDown();

            } else if (dy < 0 && checkWallCollision(this.getXPos(), newY)) {
                moveUp();
            }
        }
    }

    // Simple tank wander
    private void wander() {
        int choice = (int) (Math.random() * 4);
        switch (choice) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    // Try to shoot a missile
    public void attemptToShoot(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastShotTime > SHOOTING_COOLDOWN){
            lastShotTime = currentTime;
            shoot();
        }
    }

    // Shoot a missile
    private void shoot(){
        Missile missile = missileFactory.createMissile(this);
        getGameEnvironment().getChildren().add(missile.getImageView());
        getGameEnvironment().addMissile(missile);
    }

}
