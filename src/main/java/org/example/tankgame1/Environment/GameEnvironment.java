package org.example.tankgame1.Environment;

import javafx.scene.layout.Pane;
import org.example.tankgame1.Missile.Missile;
import org.example.tankgame1.Tank.Tank;
import org.example.tankgame1.Tank.UserTank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Singleton Class
public class GameEnvironment {
    private List<Wall> walls;
    private Pane gamePane;
    private UserTank userTank;
    private List<Tank> tanks;
    private List<Missile> activeMissiles = new ArrayList<>();
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

    public void addTanks(List<Tank> tanks){
        this.tanks = tanks;
    }

    public void addMissile(Missile missile) {
        activeMissiles.add(missile);
    }

    public List<Missile> getActiveMissiles() {
        return activeMissiles;
    }

    public void updateMissiles() {
        Iterator<Missile> it = activeMissiles.iterator();
        while (it.hasNext()) {
            Missile missile = it.next();
            if (!missile.isActive()) {
                it.remove();  // Remove the missile if it's no longer active
            } else {
                missile.move();
            }
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

    public List<Tank> getTanks(){
        return tanks;
    }
}