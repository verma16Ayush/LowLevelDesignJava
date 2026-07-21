package com.avr.designpatterns.behavioural.observer.Implementation;

import com.avr.designpatterns.behavioural.observer.abstractions.IObservable;
import com.avr.designpatterns.behavioural.observer.abstractions.IObserver;

public class CurrentConditionsDisplay implements IObserver {

    @Override
    public void update(IObservable observable) {
        if(!(observable instanceof WeatherStation)) {
            return;
        }
        WeatherStation weatherStation = (WeatherStation) observable;

        System.out.println("*************** CURRENTS ****************");
        System.out.println("current conditions as follows: ");
        System.out.println("Temperature: " + weatherStation.getTemp());
        System.out.println("Humidity: " + weatherStation.getHumidity());
        System.out.println("Pressure: " + weatherStation.getPressure());
        System.out.println("******************************************");
        System.out.println();

    }
    
}
