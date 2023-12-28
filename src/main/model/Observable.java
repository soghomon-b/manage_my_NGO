package model;

import java.util.*;

public class Observable {
    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        if (!this.observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyObserver(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
