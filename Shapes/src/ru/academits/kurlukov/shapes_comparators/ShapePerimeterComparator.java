package ru.academits.kurlukov.shapes_comparators;

import ru.academits.kurlukov.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        double perimeter1 = shape1.getPerimeter();
        double perimeter2 = shape2.getPerimeter();
        return Double.compare(perimeter1, perimeter2);
    }
}