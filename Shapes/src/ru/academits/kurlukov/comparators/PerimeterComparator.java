package ru.academits.kurlukov.comparators;

import ru.academits.kurlukov.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double perimeter1 = shape1.getPerimeter();
        double perimeter2 = shape2.getPerimeter();

        if (perimeter1 < perimeter2) {
            return -1;
        }

        if (perimeter1 > perimeter2) {
            return 1;
        }

        return 0;
    }
}