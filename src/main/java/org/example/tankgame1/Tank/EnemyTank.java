package org.example.tankgame1.Tank;

import org.example.tankgame1.Environment.GameEnvironment;

public class EnemyTank extends Tank{
    private Tank userTank;
    public EnemyTank(double xPos, double yPos) {
        super(xPos, yPos);
        this.userTank = getUserTank();
    }

    public void move(){
        // Calculate the direction of the user tank
        double dx = userTank.getXPos() - this.getXPos();
        double dy = userTank.getYPos() - this.getYPos();
        double distance = Math.sqrt(dx * dx + dy * dy); // Distance formula
        dx /= distance;
        dy /= distance;

        // Try to move in new direction
        double newXPos = this.getXPos() + dx * SPEED;
        double newYPos = this.getYPos() + dy * SPEED;

        // Check each potential movement and use existing move methods if they won't result in a collision
        if (Math.abs(dx) > Math.abs(dy)) {  // Major movement in x direction
            if (dx > 0 && checkCollision(newXPos, this.getYPos())) {
                moveRight();
            } else if (dx < 0 && checkCollision(newXPos, this.getYPos())) {
                moveLeft();
            }
        }

        if (Math.abs(dy) > Math.abs(dx)) {  // Major movement in y direction
            if (dy > 0 && checkCollision(this.getXPos(), newYPos)) {
                moveDown();
            } else if (dy < 0 && checkCollision(this.getXPos(), newYPos)) {
                moveUp();
            }
        }
    }
}
