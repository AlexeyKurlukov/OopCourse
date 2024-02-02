package files_statistics.full_statistics;

import data_handler.DataHandler;
import files_statistics.Statistics;

import java.util.List;

public class StringStatistics implements Statistics {
    private final DataHandler<String> stringDataHandler;

    private List<String> strings;
    private int minLength;
    private int maxLength;

    public StringStatistics(DataHandler<String> StringDataHandler) {
        this.stringDataHandler = StringDataHandler;
    }

    private void calculate() {
        if (!stringDataHandler.getData().isEmpty()) {
            strings = stringDataHandler.getData();
            minLength = strings.get(0).length();

            for (String str : strings) {
                if (str.length() < minLength) {
                    minLength = str.length();
                }

                if (str.length() > maxLength) {
                    maxLength = str.length();
                }
            }
        }
    }

    private void show() {
        System.out.println("Статистика для строк:");
        System.out.println("Количество элементов: " + strings.size());
        System.out.println("Самая короткая строка: " + minLength);
        System.out.println("Самая длинная строка: " + maxLength);
    }

    @Override
    public void calculateAndShow() {
        strings = stringDataHandler.getData();

        if (!strings.isEmpty()) {
            calculate();
            show();
        } else {
            System.out.println("В файлах нет значений типа String\n");
        }
    }
}