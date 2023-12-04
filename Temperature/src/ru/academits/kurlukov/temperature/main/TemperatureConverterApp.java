package ru.academits.kurlukov.temperature.main;

import ru.academits.kurlukov.temperature.model.TemperatureConverterModel;
import ru.academits.kurlukov.temperature.view.TemperatureConverterView;

public class TemperatureConverterApp {
    public static void main(String[] args) {
        TemperatureConverterModel model = new TemperatureConverterModel();
        TemperatureConverterView view = new TemperatureConverterView(model);
        TemperatureConverterView.createAndShowGUI();
    }
}