package ru.academits.kurlukov.lambda_main;

import ru.academits.kurlukov.lambda_person.Person;

import java.util.*;
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
                .map(Person::name)
                .distinct()
                .sorted()
                .toList();

        System.out.println(uniqueNames);

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        OptionalDouble personsUnder18AverageAge = persons.stream()
                .filter(p -> p.age() < 18)
                .mapToInt(Person::age)
                .average();

        if (personsUnder18AverageAge.isEmpty()) {
            System.out.println("Нет людей младше 18 лет.");
        } else {
            System.out.println("Средний возраст людей младше 18: " + personsUnder18AverageAge.getAsDouble());
        }

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        System.out.println("Средний возраст по именам: " + averageAgesByNames);

        List<String> personsBetween20And45 = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .toList();

        System.out.println("Люди, возраст которых от 20 до 45, в порядке убывания возраста: " + personsBetween20And45);
    }
}