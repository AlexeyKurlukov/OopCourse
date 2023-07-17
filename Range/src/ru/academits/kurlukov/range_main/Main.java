package ru.academits.kurlukov.range_main;

import ru.academits.kurlukov.range.Range;


public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(0, 12);
        System.out.println("Длина диапазона равна: " + range1.getLength());

        range1.setTo(15);
        range1.setFrom(-1);

        double to = range1.getTo();
        System.out.println(to);

        double from = range1.getFrom();
        System.out.println(from);

        System.out.println("Длина диапазона равна: " + range1.getLength());

        double number = 5;

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону");
        }

        Range range2 = new Range(4, 9);
        Range range3 = new Range(7, 8);

        Range intersection = range2.getIntersection(range3);

        if (intersection == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println("Пересечение двух интервалов: от " + intersection.getFrom() + " до " + intersection.getTo());
        }

        Range[] union = range2.getUnion(range3);

        for (Range range : union) {
            System.out.println("Объединение двух интервалов: от " + range.getFrom() + " до " + range.getTo());
        }

        Range[] difference = range2.getDifference(range3);

        for (Range range : difference) {
            System.out.println("Разность двух интервалов: от " + range.getFrom() + " до " + range.getTo());
        }
    }
}