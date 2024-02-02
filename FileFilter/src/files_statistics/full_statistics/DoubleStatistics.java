package files_statistics.full_statistics;

import data_handler.DataHandler;
import files_statistics.Statistics;

import java.util.List;

public class DoubleStatistics implements Statistics {
    private final DataHandler<Double> doubleDataHandler;

    private List<Double> doubles;
    private double sumDoubles;
    private double minDouble;
    private double maxDouble;
    private double avgDouble;

    public DoubleStatistics(DataHandler<Double> doubleDataHandler) {
        this.doubleDataHandler = doubleDataHandler;
    }

    private void calculate() {
        doubles = doubleDataHandler.getData();

        for (double doubleValue : doubles) {
            sumDoubles += doubleValue;

            if (doubleValue < minDouble) {
                minDouble = doubleValue;
            }

            if (doubleValue > maxDouble) {
                maxDouble = doubleValue;
            }
        }

        avgDouble = sumDoubles / doubles.size();
    }

    private void show() {
        System.out.println("Статистика для дробных чисел:");
        System.out.println("Количество элементов: " + doubles.size());
        System.out.println("Сумма значений: " + sumDoubles);
        System.out.println("Минимальное значение: " + minDouble);
        System.out.println("Максимальное значение: " + maxDouble);
        System.out.println("Среднее значение: " + avgDouble);
        System.out.println();
    }

    @Override
    public void calculateAndShow() {
        doubles = doubleDataHandler.getData();

        if (!doubles.isEmpty()) {
            calculate();
            show();
        } else {
            System.out.println("В файлах нет значений типа Double\n");
        }
    }
}
