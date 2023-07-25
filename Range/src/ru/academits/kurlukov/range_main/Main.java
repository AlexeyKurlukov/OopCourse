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

        Range range2 = new Range(4, 6);
        Range range3 = new Range(7, 10);

        Range intersection = range2.getIntersection(range3);
        System.out.print("Пересение двух интервалов: ");

        if (intersection == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.print("[");
            intersection.printRange();
            System.out.print("]");
            System.out.println();
        }

        Range[] union = range2.getUnion(range3);
        System.out.print("Объединение двух интервалов: ");
        System.out.print("[");

        for (int i = 0; i < union.length; i++) {
            union[i].printRange();

            if (i != union.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("]");
        System.out.println();

        Range[] difference = range2.getDifference(range3);
        System.out.print("Разность двух интервалов: ");

        if (difference.length == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");

            for (int i = 0; i < difference.length; i++) {
                difference[i].printRange();

                if (i != difference.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");
        }
    }
}