package org.example.tankgame1.Missile;

import org.example.tankgame1.Tank.UserTank;

public class MissileFactory {
    public Missile createMissile(UserTank tank){
        return new Missile(tank);
    }
}
