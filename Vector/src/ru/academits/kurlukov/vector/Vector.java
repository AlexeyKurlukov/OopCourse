package ru.academits.kurlukov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля. Передано size = " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Невозможно создать вектор с нулевой размерностью");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля. Передано size = " + size);
        }

        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс компоненты вектора вне допустимого диапазона. Передано index = " + index +
                    ". Индекс должен быть в диапазоне (0;" + components.length + ")");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс компоненты вектора вне допустимого диапазона. Передано index = " + index +
                    ". Индекс должен быть в диапазоне (0;" + components.length + ")");
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
        stringBuilder.append('{');
        int length = components.length;

        if (length > 0) {
            stringBuilder.append(components[0]);

            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ");
                stringBuilder.append(components[i]);
            }
        }

        stringBuilder.append('}');
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
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public void add(Vector vector) {
        int size = Math.max(components.length, vector.components.length);
        double[] resultComponents = new double[size];

        for (int i = 0; i < size; i++) {
            double vector1Value = (i < components.length) ? components[i] : 0;
            double vector2Value = (i < vector.components.length) ? vector.components[i] : 0;
            resultComponents[i] = vector1Value + vector2Value;
        }

        components = resultComponents;
    }

    public void subtract(Vector vector) {
        int size = Math.max(components.length, vector.components.length);
        double[] resultComponents = new double[size];

        for (int i = 0; i < size; i++) {
            double vector1Value = (i < components.length) ? components[i] : 0;
            double vector2Value = (i < vector.components.length) ? vector.components[i] : 0;
            resultComponents[i] = vector1Value - vector2Value;
        }

        components = resultComponents;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);
        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);
        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double product = 0;

        for (int i = 0; i < minSize; i++) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}