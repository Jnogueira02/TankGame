package org.example.tankgame1.Tank;

import org.example.tankgame1.ObserverPattern.Observable;
import org.example.tankgame1.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;

// UserTank implements observable to update healthbar
public class UserTank extends Tank implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    public UserTank(double xPos, double yPos) {
        super(xPos, yPos);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void takeDamage(){
        super.takeDamage();
        notifyObservers();
    }

    @Override
    public void repair(){
        super.repair();
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(getHealth());
        }
    }
}
