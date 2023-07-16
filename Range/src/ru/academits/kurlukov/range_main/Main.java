package ru.academits.kurlukov.range_main;

import ru.academits.kurlukov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(0, 12);
        System.out.println("Длина диапазона равна: " + range.getLength());

        range.setTo(15);
        range.setFrom(-1);

        double to = range.getTo();
        System.out.println(to);

        double from = range.getFrom();
        System.out.println(from);

        System.out.println("Длина диапазона равна: " + range.getLength());

        double number = 5;

        if (range.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону");
        }
    }
}