package ru.academits.kurlukov.ArrayListHome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File("file.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("В файле содержатся следующие строки: " + lines);

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        Integer[] someNumbers = {7, 2, 2, 1, 3, 5, 6, 6, 6};
        numbers.addAll(Arrays.asList(someNumbers));
        System.out.println("Список до удаления чётных чисел: " + numbers);

        numbers.removeIf(number -> number % 2 == 0);
        System.out.println("Список после удаления чётных чисел: " + numbers);

        ArrayList<Integer> originalList = new ArrayList<>(Arrays.asList(someNumbers));
        System.out.println("Список с повторяющимися числами: " + originalList);

        LinkedHashSet<Integer> uniqueSet = new LinkedHashSet<>(originalList);

        ArrayList<Integer> newList = new ArrayList<>(uniqueSet);
        System.out.println("Список без повторяющихся чисел: " + newList);
    }
}