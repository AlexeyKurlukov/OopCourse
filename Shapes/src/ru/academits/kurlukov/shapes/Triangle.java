package ru.academits.kurlukov.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    private final double side1Length;
    private final double side2Length;
    private final double side3Length;
    private static final double epsilon = 1.0e-10;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.side1Length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        this.side2Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        this.side3Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    private boolean arePointsCollinear() {
        return Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= epsilon;
    }

    @Override
    public double getArea() {
        if (arePointsCollinear()) {
            return 0;
        }

        double semiPerimeter = (side1Length + side2Length + side3Length) / 2.0;
        return Math.sqrt(semiPerimeter * (semiPerimeter - side1Length) *
                (semiPerimeter - side2Length) * (semiPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        if (arePointsCollinear()) {
            return 0;
        }

        return side1Length + side2Length + side3Length;
    }

    @Override
    public String toString() {
        return "Triangle [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", x3=" + x3 + ", y3=" + y3 + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) object;
        return Double.compare(x1, triangle.x1) == 0 && Double.compare(y1, triangle.y1) == 0 && Double.compare(x2, triangle.x2) == 0
                && Double.compare(y2, triangle.y2) == 0 && Double.compare(x3, triangle.x3) == 0 && Double.compare(y3, triangle.y3) == 0;
    }
}