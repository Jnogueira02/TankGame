package org.example.tankgame1.Environment.MedPack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.tankgame1.Environment.Image.ImageFactory;
import org.example.tankgame1.Environment.Image.ImageViewFactory;
import org.example.tankgame1.Tank.Tank;

public class MedPack {
    private ImageView imageView;
    private final ImageViewFactory imageViewFactory;
    private final double xPos;
    private final double yPos;
    private final double width = 30;
    private final double height = 25;
    private boolean isActive;

    public MedPack(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        isActive = true;
        imageViewFactory = ImageViewFactory.getInstance();
        initializeImage();
    }

    private void initializeImage() {
        imageView = imageViewFactory.createImageView("/images/medpack.png", xPos, yPos, width, height);
    }

    public ImageView getImageView() {
        return imageView;
    }

    // Repair a tank by one health point, and hide the MedPack
    public void applyEffect(Tank tank) {
        if (isActive) {
            tank.repair();
            isActive = false;
            imageView.setVisible(false);
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

