package files_statistics.short_statistics;

import data_handler.DataHandler;
import files_statistics.Statistics;

import java.math.BigInteger;

public class ShortStatistics implements Statistics {
    private final DataHandler<BigInteger> intDataHandler;
    private final DataHandler<Double> doubleDataHandler;
    private final DataHandler<String> stringDataHandler;

    private int totalElementsCount;

    public ShortStatistics(DataHandler<BigInteger> intDataHandler, DataHandler<Double> doubleDataHandler, DataHandler<String> stringDataHandler) {
        this.intDataHandler = intDataHandler;
        this.doubleDataHandler = doubleDataHandler;
        this.stringDataHandler = stringDataHandler;
    }

    private void calculate() {
        totalElementsCount = intDataHandler.getData().size() + doubleDataHandler.getData().size() + stringDataHandler.getData().size();
    }

    private void show() {
        System.out.println("Общее количество элементов: " + totalElementsCount);
    }

    @Override
    public void calculateAndShow() {
        int intElements = intDataHandler.getData().size();
        int doubleElements = doubleDataHandler.getData().size();
        int stringElements = stringDataHandler.getData().size();

        if (intElements + doubleElements + stringElements > 0) {
            calculate();
            show();
        } else {
            System.out.println("Нет данных для вычисления статистики");
        }
    }
}