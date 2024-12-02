package org.example.tankgame1.Environment;

import javafx.scene.layout.Pane;
import org.example.tankgame1.Tank.UserTank;

import java.util.List;

// Singleton Class
public class GameEnvironment {
    private List<Wall> walls;
    private Pane gamePane;
    private UserTank userTank;
    private static GameEnvironment instance;
    private static boolean isInitialized = false; // Flag to check if instance is initialized

    // Private constructor to prevent external instantiation
    private GameEnvironment() { }

    public static GameEnvironment getInstance() {
        if (instance == null) {
            instance = new GameEnvironment();
        }
        return instance;
    }

    // Initialization method to set walls and pane
    public void initialize(List<Wall> walls, Pane gamePane, UserTank userTank) {
        if (!isInitialized) { // Only allow initialization once
            this.walls = walls;
            this.gamePane = gamePane;
            this.userTank = userTank;
            isInitialized = true; // Set the flag as initialized
        } else {
            throw new IllegalStateException("GameEnvironment can only be initialized once.");
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public UserTank getUserTank() {
        return userTank;
    }
}