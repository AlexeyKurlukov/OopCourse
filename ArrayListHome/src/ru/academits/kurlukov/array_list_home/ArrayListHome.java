package ru.academits.kurlukov.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.FileNotFoundException;

public class ArrayListHome {
    public static List<String> readLinesFromFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void removeEvenNumbers(List<Integer> numbers) {
        Iterator<Integer> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            Integer number = iterator.next();

            if (number % 2 == 0) {
                iterator.remove();
            }
        }
    }

    public static ArrayList<Integer> getUniqueElements(ArrayList<Integer> numbers) {
        ArrayList<Integer> uniqueElementsList = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!uniqueElementsList.contains(number)) {
                uniqueElementsList.add(number);
            }
        }

        return uniqueElementsList;
    }

    public static void main(String[] args) {
        try {
            List<String> lines = readLinesFromFile("file.txt");
            System.out.println("В файле содержатся следующие строки: " + lines);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        Integer[] numbersArray = {7, 2, 2, 1, 3, 5, 6, 6, 6};
        numbers.addAll(Arrays.asList(numbersArray));
        System.out.println("Список до удаления чётных чисел: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("Список после удаления чётных чисел: " + numbers);

        ArrayList<Integer> originalList = new ArrayList<>(Arrays.asList(numbersArray));
        System.out.println("Список с повторяющимися числами: " + originalList);

        ArrayList<Integer> uniqueElementsList = getUniqueElements(originalList);
        System.out.println("Список без повторяющихся чисел: " + uniqueElementsList);
    }
}