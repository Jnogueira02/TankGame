package org.example.tankgame1.Missile;

import org.example.tankgame1.Tank.Tank;

public class MissileFactory {
    public Missile createMissile(Tank tank){
        return new Missile(tank);
    }
}
