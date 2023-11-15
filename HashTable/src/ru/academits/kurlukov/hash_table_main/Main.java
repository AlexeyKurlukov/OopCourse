package ru.academits.kurlukov.hash_table_main;

import ru.academits.kurlukov.hash_table.HashTable;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        System.out.println("Исходная хэш-таблица: " + hashTable);
        System.out.println("Размер хэш-таблицы: " + hashTable.size());

        hashTable.add(null);
        hashTable.add(0);
        hashTable.add(0);
        hashTable.add(555);
        hashTable.add(11);
        hashTable.add(222);
        hashTable.add(44444);
        hashTable.add(-10);
        hashTable.add(-333);
        hashTable.add(-5555);
        hashTable.add(12);
        hashTable.add(null);
        hashTable.add(113);
        hashTable.add(88);
        hashTable.add(88);
        System.out.println("Хэш-таблица после добавления элементов: " + hashTable);
        System.out.println("Размер хэш-таблицы: " + hashTable.size());

        int element1 = 88;
        System.out.println("Хэш-таблица содержит элемент " + element1 + ": " + hashTable.contains(element1));

        Object element2 = null;
        System.out.println("Из хэш-таблицы был удалён элемент " + element2 + ": " + hashTable.remove(null));
        System.out.println(hashTable);

        System.out.println("Размер хэш-таблицы: " + hashTable.size());
        System.out.println();

        System.out.println("Преобразование хэш-таблицы в массив: " + Arrays.toString(hashTable.toArray()));
        System.out.println();

        List<Integer> list;
        list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("Исходный список: " + list);

        System.out.println("Элементы из списка " + list + " добавлены в хэш-таблицу: " + hashTable.addAll(list));
        System.out.println(hashTable);
        System.out.println();

        System.out.println("Хэш-таблица содержит все элементы из списка " + list + ": " + hashTable.containsAll(list));
        System.out.println(hashTable);
        System.out.println();

        System.out.println("Все элементы из списка " + list + " удалены из хэш-таблицы: " + hashTable.removeAll(list));
        System.out.println(hashTable);
        System.out.println("Размер хэш-таблицы: " + hashTable.size());
        System.out.println();

        System.out.println("Все элементы, кроме элементов из коллекции " + list + ", были удалены из хэш-таблицы: " + hashTable.retainAll(list));
        System.out.println(hashTable);
        System.out.println();

        hashTable.clear();
        System.out.println("Хэш-таблица после применения метода clear " + hashTable);
        System.out.println();

        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);

        for (Integer element : hashTable) {
            System.out.println("Элемент в хэш-таблице: " + element);
        }
    }
}