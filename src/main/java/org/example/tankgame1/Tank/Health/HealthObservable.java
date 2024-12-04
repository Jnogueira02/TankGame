package org.example.tankgame1.Tank.Health;

public interface HealthObservable {
    void addHealthObserver(HealthObserver observer);
    void removeHealthObserver(HealthObserver observer);
    void notifyHealthObservers();
}