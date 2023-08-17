package ru.academits.kurlukov.shapes_comparators;

import ru.academits.kurlukov.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double area1 = shape1.getArea();
        double area2 = shape2.getArea();
        return Double.compare(area1, area2);
    }
}