package com.avr.designpatterns.behavioural.observer;

import com.avr.designpatterns.behavioural.observer.Implementation.CurrentConditionsDisplay;
import com.avr.designpatterns.behavioural.observer.Implementation.StatisticalConditionsDisplay;
import com.avr.designpatterns.behavioural.observer.Implementation.WeatherStation;

public class ObserverDemo {

    public static void main(String[] args) {
        // Create the WeatherStation (Observable)
        WeatherStation weatherStation = new WeatherStation();

        // Create Observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticalConditionsDisplay statisticalDisplay = new StatisticalConditionsDisplay();

        // Register Observers
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticalDisplay);

        // Simulate new weather measurements
        weatherStation.setMeasures(25, 65, 1013);
        weatherStation.setMeasures(30, 70, 1010);
        weatherStation.setMeasures(28, 60, 1015);

        // Remove an observer and simulate another update
        weatherStation.removeObserver(currentDisplay);
        weatherStation.setMeasures(26, 68, 1012);
    }
}