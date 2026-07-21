package com.avr.designpatterns.behavioural.observer.abstractions;

public interface IObservable {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
