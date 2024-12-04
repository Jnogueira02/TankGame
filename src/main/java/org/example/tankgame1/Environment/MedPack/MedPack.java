package org.example.tankgame1.Environment.MedPack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.tankgame1.Tank.Tank;

public class MedPack {
    private ImageView imageView;
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private boolean isActive;

    public MedPack(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        isActive = true;
        initializeImage();
    }

    private void initializeImage() {
        Image image = new Image(getClass().getResource("/images/medpack.png").toExternalForm());
        imageView = new ImageView(image);
        imageView.setX(xPos);
        imageView.setY(yPos);
        width = 30;
        height = 25;
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isActive() {
        return isActive;
    }

    public void applyEffect(Tank tank) {
        if (isActive) {
            tank.repair();  // Add one health point to the tank
            isActive = false;   // Deactivate the medpack after use
            imageView.setVisible(false);  // Hide the medpack
        }
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

