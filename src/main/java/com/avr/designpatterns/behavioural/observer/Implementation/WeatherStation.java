package com.avr.designpatterns.behavioural.observer.Implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avr.designpatterns.behavioural.observer.abstractions.IObservable;
import com.avr.designpatterns.behavioural.observer.abstractions.IObserver;

import lombok.Getter;

@Getter
public class WeatherStation implements IObservable {
    int temp;
    int humidity;
    int pressure;
    List<IObserver> observers;

    @Override
    public void registerObserver(IObserver observer) {
        if(observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        if(observers == null || observers.isEmpty())
            return;
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers)
            observer.update(this);
    }

    public void setMeasures(int temp, int humidity, int pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

}
