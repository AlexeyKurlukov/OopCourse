package ru.academits.kurlukov.temperature.model.strategy;

public class CelsiusStrategy implements TemperatureScaleStrategy {
    @Override
    public double toCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double fromCelsius(double temperature) {
        return temperature;
    }
}
