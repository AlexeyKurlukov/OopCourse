package ru.academits.kurlukov.range_main;

import ru.academits.kurlukov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(0, 12);
        System.out.println("Длина диапазона равна: " + range1.getLength());

        range1.setTo(15);
        range1.setFrom(-1);

        System.out.println("Длина диапазона равна: " + range1.getLength());

        double number = 5;

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону");
        }

        Range range2 = new Range(4, 6);
        Range range3 = new Range(4, 6);

        Range intersection = range2.getIntersection(range3);
        System.out.print("Пересечение двух интервалов: ");

        if (intersection == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(intersection);
        }

        Range[] union = range2.getUnion(range3);
        System.out.println("Объединение двух интервалов: " + Arrays.toString(union));

        Range[] difference = range2.getDifference(range3);
        System.out.println("Разность двух интервалов: " + Arrays.toString(difference));
    }
}