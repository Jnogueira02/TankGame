package org.example.tankgame1.Environment.Explosion;

public class ExplosionFactory {
    private final ExplosionImageCache imageCache;
    private static ExplosionFactory instance;

    private ExplosionFactory() {
        this.imageCache = ExplosionImageCache.getInstance();
    }

    public static ExplosionFactory getInstance(){
        if(instance == null){
            return new ExplosionFactory();
        }
        return instance;
    }

    public Explosion createExplosion(double xPos, double yPos) {
        return new Explosion(xPos, yPos, imageCache);
    }
}

