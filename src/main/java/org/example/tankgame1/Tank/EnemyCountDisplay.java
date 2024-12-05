package org.example.tankgame1.Tank;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.ObserverPattern.Observer;

// EnemyCountDisplay implements Observer to update Enemy count label
public class EnemyCountDisplay implements Observer {
    private final Label displayLabel;
    private final GameEnvironment gameEnvironment;

    public EnemyCountDisplay(Label displayLabel) {
        this.gameEnvironment = GameEnvironment.getInstance();
        this.displayLabel = displayLabel;
        this.gameEnvironment.addObserver(this);
    }

    @Override
    public void update(double health) {
        int count = gameEnvironment.getEnemyCount();
        Platform.runLater(() -> displayLabel.setText("Enemies: " + count));
    }
}

