package ru.academits.kurlukov.temperature.model;

import ru.academits.kurlukov.temperature.model.strategy.CelsiusStrategy;
import ru.academits.kurlukov.temperature.model.strategy.FahrenheitStrategy;
import ru.academits.kurlukov.temperature.model.strategy.KelvinStrategy;
import ru.academits.kurlukov.temperature.model.strategy.TemperatureScaleStrategy;

import java.util.HashMap;
import java.util.Map;

public class TemperatureConverterModel {
    private final Map<String, TemperatureScaleStrategy> strategies;

    public TemperatureConverterModel() {
        strategies = new HashMap<>();
        strategies.put("Цельсий", new CelsiusStrategy());
        strategies.put("Фаренгейт", new FahrenheitStrategy());
        strategies.put("Кельвин", new KelvinStrategy());
    }

    public double convertTemperature(double temperature, String fromScale, String toScale) {
        TemperatureScaleStrategy fromStrategy = strategies.get(fromScale);
        TemperatureScaleStrategy toStrategy = strategies.get(toScale);
        double celsiusTemp = fromStrategy.toCelsius(temperature);
        return toStrategy.fromCelsius(celsiusTemp);
    }
}