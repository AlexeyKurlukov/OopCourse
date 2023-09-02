package ru.academits.kurlukov.lambda_main;

import ru.academits.kurlukov.lambda_person.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 23),
                new Person("Petr", 177),
                new Person("Alexandra", 33),
                new Person("Anna", 22),
                new Person("Petr", 37),
                new Person("Ivan", 23),
                new Person("Ivan", 29),
                new Person("Ivan", 19)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .toList();

        System.out.println(uniqueNames);

        String allNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(allNamesString);

        double personsUnder18AverageAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("Средний возраст людей младше 18: " + personsUnder18AverageAge);

        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Средний возраст по именам: " + averageAgeByName);

        List<String> personsBetween20And45 = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .toList();

        System.out.println("Люди, возраст которых от 20 до 45, в порядке убывания возраста: " + personsBetween20And45);
    }
}