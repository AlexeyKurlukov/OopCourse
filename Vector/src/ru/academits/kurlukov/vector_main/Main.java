package ru.academits.kurlukov.vector_main;

import ru.academits.kurlukov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] vector1Components = {1, 2, 3};
        Vector vector1 = new Vector(vector1Components);
        System.out.println("vector1: " + vector1);

        double[] vector2Components = {4, 5};
        Vector vector2 = new Vector(vector2Components);
        System.out.println("vector2: " + vector2);

        vector1.add(vector2);
        System.out.println("Результат прибавления к вектору vector1 вектора vector2: " + vector1);

        double[] vector3Components = {5, 6, 7};
        Vector vector3 = new Vector(vector3Components);
        System.out.println("vector3: " + vector3);

        double[] vector4Components = {4, 5, 6, 4, 5, 6};
        Vector vector4 = new Vector(vector4Components);
        System.out.println("vector4: " + vector4);

        vector3.subtract(vector4);
        System.out.println("Результат вычитания вектора vector4 из вектора vector3: " + vector3);

        double scalar = 2;
        vector1.multiply(scalar);
        System.out.println("Результат умножения вектора vector1 на " + scalar + ": " + vector1);

        vector1.reverse();
        System.out.println("Результат разворота вектора vector1: " + vector1);

        double[] vector5Components = {-10, 24};
        Vector vector5 = new Vector(vector5Components);
        System.out.println("vector5: " + vector5);
        System.out.println("Длина вектора vector5: " + vector5.getLength());

        System.out.println("Изменение компоненты с заданным индексом в векторе vector1 на заданное значение:");
        vector1.setComponent(0, 55);
        System.out.println("vector1: " + vector1);

        System.out.println("Получение компоненты с заданным индексом в векторе vector1: " + vector1.getComponent(2));

        double[] vector6Components = {1, 2};
        Vector vector6 = new Vector(vector6Components);
        System.out.println("vector6: " + vector6);

        double[] vector7Components = {1, 3};
        Vector vector7 = new Vector(vector7Components);
        System.out.println("vector7: " + vector7);

        System.out.println("Проверка на равенство векторов vector6 и vector7: " + vector6.equals(vector7));

        Vector sum = Vector.getSum(vector6, vector7);
        System.out.println("Результат суммы векторов vector6 и vector7: " + sum);

        Vector difference = Vector.getDifference(vector6, vector7);
        System.out.println("Результат вычитания векторов vector6 и vector7: " + difference);

        double scalarProduct = Vector.getScalarProduct(vector6, vector7);
        System.out.println("Результат скалярного произведения векторов vector6 и vector7: " + scalarProduct);
    }
}