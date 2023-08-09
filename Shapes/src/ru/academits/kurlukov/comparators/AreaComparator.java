package ru.academits.kurlukov.comparators;

import ru.academits.kurlukov.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double area1 = shape1.getArea();
        double area2 = shape2.getArea();

        if (area1 < area2) {
            return -1;
        }

        if (area2 > area1) {
            return 1;
        }

        return 0;
    }
}