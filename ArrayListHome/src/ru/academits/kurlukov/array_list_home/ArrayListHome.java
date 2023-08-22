package ru.academits.kurlukov.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;

public class ArrayListHome {
    public static List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }

        return lines;
    }

    public static List<Integer> removeEvenNumbers(List<Integer> numbers) {
        List<Integer> oddNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        }

        return oddNumbers;
    }

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> originalList) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (Integer number : originalList) {
            if (!resultList.contains(number)) {
                resultList.add(number);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        List<String> lines = readLinesFromFile("file.txt");

        if (lines.isEmpty()) {
            System.out.println("Файл пуст или не найден");
        } else {
            System.out.println("В файле содержатся следующие строки: " + lines);
        }

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        Integer[] numbersArray = {7, 2, 2, 1, 3, 5, 6, 6, 6};
        numbers.addAll(Arrays.asList(numbersArray));
        System.out.println("Список до удаления чётных чисел: " + numbers);

        List<Integer> oddNumbers = removeEvenNumbers(numbers);
        System.out.println("Список после удаления чётных чисел: " + oddNumbers);

        ArrayList<Integer> originalList = new ArrayList<>(Arrays.asList(numbersArray));
        System.out.println("Список с повторяющимися числами: " + originalList);

        ArrayList<Integer> resultList = removeDuplicates(originalList);
        System.out.println("Список без повторяющихся чисел: " + resultList);
    }
}