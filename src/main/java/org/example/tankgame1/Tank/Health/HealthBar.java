package org.example.tankgame1.Tank.Health;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class HealthBar implements HealthObserver{
    private final ProgressBar progressBar;

    public HealthBar() {
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);
        progressBar.setProgress(1);
        progressBar.setStyle("-fx-background-color: red;");
    }

    @Override
    public void updateHealth(double health) {
        Platform.runLater(() -> progressBar.setProgress(health / 3));
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
