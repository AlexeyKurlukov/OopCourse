package ru.academits.kurlukov.shapes_main;

import ru.academits.kurlukov.comparators.AreaComparator;
import ru.academits.kurlukov.comparators.PerimeterComparator;
import ru.academits.kurlukov.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator().reversed());
        return shapes[0];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator().reversed());
        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(1),
                new Triangle(0, -13231, 0, 1111111, 0, 55550),
                new Rectangle(3, 3),
                new Circle(30),
                new Square(100),
                new Triangle(55, 22, 999, 0, 0, 0),
                new Circle(5),
                new Square(22),
                new Rectangle(5, 25),
                new Rectangle(5, -43242)
        };

        Shape maxAreaShape = getMaxAreaShape(shapes);

        if (maxAreaShape == null) {
            System.out.println("Массив фигур пустой");
        } else {
            System.out.println("Фигура с максимальной площадью: " + maxAreaShape.getClass().getSimpleName() + ", площадь = " + maxAreaShape.getArea());
        }

        Shape secondMaxPerimeterShape = getSecondMaxPerimeterShape(shapes);

        if (secondMaxPerimeterShape == null) {
            System.out.println("В массиве недостаточно объектов, чтобы найти фигуру со вторым по величине периметром");
        } else {
            System.out.println("Фигура со вторым по величине периметром: " + secondMaxPerimeterShape.getClass().getSimpleName()
                    + ", периметр = " + secondMaxPerimeterShape.getPerimeter());
        }
    }
}