package org.example.tankgame1.Environment;

import javafx.scene.layout.Pane;

import java.util.List;

public class GameEnvironment {
    private List<Wall> walls;
    private Pane gamePane;

    public GameEnvironment(List<Wall> walls, Pane gamePane){
        this.walls = walls;
        this.gamePane = gamePane;
    }

    public List<Wall> getWalls(){
        return walls;
    }

    public Pane getGamePane() {
        return gamePane;
    }
}
