package org.example.tankgame1.Environment;

public class ExplosionFactory {
    private ExplosionImageCache imageCache;

    public ExplosionFactory() {
        this.imageCache = ExplosionImageCache.getInstance();
    }

    public Explosion createExplosion(double xPos, double yPos) {
        return new Explosion(xPos, yPos, imageCache);
    }
}

