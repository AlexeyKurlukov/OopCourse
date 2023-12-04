package ru.academits.kurlukov.temperature.model.strategy;

public class KelvinStrategy implements TemperatureScaleStrategy {
    @Override
    public double toCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double fromCelsius(double temperature) {
        return temperature + 273.15;
    }
}
