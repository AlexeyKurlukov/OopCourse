package ru.academits.kurlukov.temperature.model.strategy;

public interface TemperatureScaleStrategy {
    double toCelsius(double temperature);

    double fromCelsius(double temperature);
}
