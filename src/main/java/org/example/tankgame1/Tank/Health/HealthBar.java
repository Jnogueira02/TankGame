package org.example.tankgame1.Tank.Health;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import org.example.tankgame1.ObserverPattern.Observer;

public class HealthBar implements Observer {
    private final ProgressBar progressBar;

    public HealthBar() {
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);
        progressBar.setProgress(1);
        progressBar.setStyle("-fx-accent: red;");
    }

    @Override
    public void update(double health) {
        Platform.runLater(() -> progressBar.setProgress(health / 3));
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
