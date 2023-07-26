package ru.academits.kurlukov.shapes;

public interface Shape {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    String toString();

    int hashCode();

    boolean equals(Object object);
}