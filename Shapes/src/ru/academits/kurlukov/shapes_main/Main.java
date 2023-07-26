package ru.academits.kurlukov.shapes_main;

import ru.academits.kurlukov.shapes.*;

import java.util.Comparator;
import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::getArea).reversed());
        return shapes[0];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::getPerimeter).reversed());
        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];
        shapes[0] = new Square(1);
        shapes[1] = new Triangle(0, -13231, 0, 1111111, 0, 55550);
        shapes[2] = new Rectangle(3, 3);
        shapes[3] = new Circle(30);
        shapes[4] = new Square(100);
        shapes[5] = new Triangle(55, 22, 999, 0, 0, 0);
        shapes[6] = new Circle(5);
        shapes[7] = new Square(22);
        shapes[8] = new Rectangle(5, 25);
        shapes[9] = new Rectangle(5, -43242);

        Shape maxAreaShape = getMaxAreaShape(shapes);
        System.out.println("Фигура с максимальной площадью: " + maxAreaShape.getClass().getSimpleName() + ", площадь = " + maxAreaShape.getArea());

        Shape secondMaxPerimeterShape = getSecondMaxPerimeterShape(shapes);
        System.out.println("Фигура со вторым по величине периметром: " + secondMaxPerimeterShape.getClass().getSimpleName()
                + ", периметр = " + secondMaxPerimeterShape.getPerimeter());
    }
}