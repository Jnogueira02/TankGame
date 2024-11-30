package org.example.tankgame1.Tank;

import org.example.tankgame1.Environment.GameEnvironment;

// Singleton Class
public class TankFactory {
    private static TankFactory instance;

    private TankFactory() {}

    public static TankFactory getInstance() {
        if (instance == null) {
            instance = new TankFactory();
        }
        return instance;
    }

    public Tank createTank(TankType type, double xPos, double yPos) {
        return switch (type) {
            case USER -> new UserTank(xPos, yPos);
            case ENEMY -> new EnemyTank(xPos, yPos);
            default -> throw new IllegalArgumentException("Unknown tank type: " + type);
        };
    }
}