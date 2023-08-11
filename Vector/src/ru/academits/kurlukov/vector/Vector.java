package ru.academits.kurlukov.vector;

import java.util.Arrays;

public class Vector {
    private final double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }

        this.components = new double[n];
        System.arraycopy(components, 0, this.components, 0, Math.min(n, components.length));
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Индекс компоненты вектора вне допустимого диапазона");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Индекс компоненты вектора вне допустимого диапазона");
        }

        components[index] = value;
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < components.length; i++) {
            stringBuilder.append(components[i]);

            if (i != components.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        if (getSize() != vector.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); i++) {
            if (components[i] != vector.getComponent(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public void add(Vector vector) {
        int size = Math.min(getSize(), vector.getSize());

        for (int i = 0; i < size; i++) {
            components[i] += vector.getComponent(i);
        }
    }

    public void subtract(Vector vector) {
        int size = Math.min(getSize(), vector.getSize());

        for (int i = 0; i < size; i++) {
            components[i] -= vector.getComponent(i);
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        int size = Math.max(vector1.getSize(), vector2.getSize());
        double[] resultComponents = new double[size];

        for (int i = 0; i < size; i++) {
            double vector1Value = (i < vector1.getSize()) ? vector1.getComponent(i) : 0;
            double vector2Value = (i < vector2.getSize()) ? vector2.getComponent(i) : 0;
            resultComponents[i] = vector1Value + vector2Value;
        }

        return new Vector(resultComponents);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        int size = Math.max(vector1.getSize(), vector2.getSize());
        double[] resultComponents = new double[size];

        for (int i = 0; i < size; i++) {
            double vector1Value = (i < vector1.getSize()) ? vector1.getComponent(i) : 0;
            double vector2Value = (i < vector2.getSize()) ? vector2.getComponent(i) : 0;
            resultComponents[i] = vector1Value - vector2Value;
        }

        return new Vector(resultComponents);
    }

    public static double scalarProduct(Vector vector1, Vector vector2) {
        int size = Math.min(vector1.getSize(), vector2.getSize());
        double product = 0;

        for (int i = 0; i < size; i++) {
            product += vector1.getComponent(i) * vector2.getComponent(i);
        }

        return product;
    }
}