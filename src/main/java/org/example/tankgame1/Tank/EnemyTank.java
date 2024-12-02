package org.example.tankgame1.Tank;

import org.example.tankgame1.Environment.GameEnvironment;

public class EnemyTank extends Tank{
    private UserTank userTank;
    private static final double MIN_DISTANCE = 100; // The enemy tank must keep this distance from the player
    private long timeFromLastDirectionChange = 0;
    private static final long DIRECTION_CHANGE_COOLDOWN = 200;

    public EnemyTank(double xPos, double yPos) {
        super(xPos, yPos);
        this.userTank = getUserTank();
    }

    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - timeFromLastDirectionChange < DIRECTION_CHANGE_COOLDOWN) {
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
        timeFromLastDirectionChange = currentTime;
    }

    private void moveInDirection(double dx, double dy) {
        double newX = this.getXPos() + dx * SPEED;
        double newY = this.getYPos() + dy * SPEED;

        // Attempt move if no collision
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && checkCollision(newX, this.getYPos())) {
                moveRight();
            } else if (dx < 0 && checkCollision(newX, this.getYPos())) {
                moveLeft();
            }
        } else {
            if (dy > 0 && checkCollision(this.getXPos(), newY)) {
                moveDown();
            } else if (dy < 0 && checkCollision(this.getXPos(), newY)) {
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
}
