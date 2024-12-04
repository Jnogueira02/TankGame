package org.example.tankgame1.Tank;

import org.example.tankgame1.Tank.Health.HealthObservable;
import org.example.tankgame1.Tank.Health.HealthObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserTank extends Tank implements HealthObservable {
    private List<HealthObserver> observers = new ArrayList<>();
    public UserTank(double xPos, double yPos) {
        super(xPos, yPos);
    }

    @Override
    public void addHealthObserver(HealthObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeHealthObserver(HealthObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyHealthObservers() {
        for(HealthObserver observer : observers){
            observer.updateHealth(getHealth());
        }
    }
}
