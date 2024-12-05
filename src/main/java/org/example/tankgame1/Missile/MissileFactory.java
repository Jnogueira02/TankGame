package org.example.tankgame1.Missile;

import org.example.tankgame1.Tank.Tank;

// Singleton Class
public class MissileFactory {

    private static MissileFactory instance;

    private MissileFactory(){}

    public static MissileFactory getInstance(){
        if(instance == null){
            return new MissileFactory();
        }
        return instance;
    }
    public Missile createMissile(Tank tank){
        return new Missile(tank);
    }
}
