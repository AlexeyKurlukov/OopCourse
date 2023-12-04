package ru.academits.kurlukov.temperature.model.strategy;

public class FahrenheitStrategy implements TemperatureScaleStrategy {
    @Override
    public double toCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double fromCelsius(double temperature) {
        return temperature * 9 / 5 + 32;
    }
}
