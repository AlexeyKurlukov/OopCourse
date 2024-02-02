package files_statistics.full_statistics;

import data_handler.DataHandler;
import files_statistics.Statistics;

import java.math.BigInteger;
import java.util.List;

public class IntegerStatistics implements Statistics {
    private final DataHandler<BigInteger> integerDataHandler;

    private List<BigInteger> integers;
    private BigInteger sumIntegers;
    private BigInteger minInteger;
    private BigInteger maxInteger;
    private double avgInteger;

    public IntegerStatistics(DataHandler<BigInteger> integerDataHandler) {
        this.integerDataHandler = integerDataHandler;
    }

    public void calculate() {
        integers = integerDataHandler.getData();
        sumIntegers = BigInteger.ZERO;
        minInteger = integers.get(0);
        maxInteger = integers.get(0);

        for (BigInteger bigIntValue : integers) {
            sumIntegers = sumIntegers.add(bigIntValue);

            if (bigIntValue.compareTo(minInteger) < 0) {
                minInteger = bigIntValue;
            }

            if (bigIntValue.compareTo(maxInteger) > 0) {
                maxInteger = bigIntValue;
            }
        }

        avgInteger = sumIntegers.divide(new BigInteger(String.valueOf(integers.size()))).doubleValue();
    }

    public void show() {
        System.out.println("Статистика для целых чисел:");
        System.out.println("Количество элементов: " + integers.size());
        System.out.println("Сумма значений: " + sumIntegers);
        System.out.println("Минимальное значение: " + minInteger);
        System.out.println("Максимальное значение: " + maxInteger);
        System.out.println("Среднее значение: " + avgInteger);
        System.out.println();
    }

    @Override
    public void calculateAndShow() {
        integers = integerDataHandler.getData();

        if (!integers.isEmpty()) {
            calculate();
            show();
        } else {
            System.out.println("В файлах нет значений типа Integer\n");
        }
    }
}