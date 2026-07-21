package com.avr.designpatterns.behavioural.observer.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.avr.designpatterns.behavioural.observer.abstractions.IObservable;
import com.avr.designpatterns.behavioural.observer.abstractions.IObserver;

public class StatisticalConditionsDisplay implements IObserver {
    List<Integer> temps;
    List<Integer> humidities;
    List<Integer> pressures;

    public StatisticalConditionsDisplay() {
        temps = new ArrayList<>();
        humidities = new ArrayList<>();
        pressures = new ArrayList<>();
    }

    @Override
    public void update(IObservable observable) {
        if(!(observable instanceof WeatherStation))
            return;
        WeatherStation weatherStation = (WeatherStation) observable;
        temps.add(weatherStation.getTemp());
        humidities.add(weatherStation.getHumidity());
        pressures.add(weatherStation.getPressure());

        System.out.println("*************** AVERAGES ****************");
        System.out.println("Average values are:");
        System.out.println("Avg Temp: " + temps.stream().mapToInt(Integer::valueOf).sum());
        System.out.println("Avg Humidity: " + humidities.stream().mapToInt(Integer::valueOf).sum());
        System.out.println("Avg Pressure: " + pressures.stream().mapToInt(Integer::valueOf).sum());
        System.out.println("******************************************");
        System.out.println();
    }
}
