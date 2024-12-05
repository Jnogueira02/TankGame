package org.example.tankgame1.Tank.Health;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import org.example.tankgame1.ObserverPattern.Observer;

// HealthBar for our UI
public class HealthBar implements Observer {
    private final ProgressBar progressBar;

    public HealthBar() {
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);
        progressBar.setProgress(1);
        progressBar.setStyle("-fx-accent: red;");
    }

    // Update the healthbar with a change in health
    @Override
    public void update(double health) {
        Platform.runLater(() -> progressBar.setProgress(health / 3));
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
