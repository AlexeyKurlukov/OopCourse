package ru.academits.kurlukov.hash_table_main;

import ru.academits.kurlukov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4);
        System.out.println("Исходная хэш-таблица: " + hashTable);

        System.out.println("Размер хэш-таблицы: " + hashTable.size());

        int element1 = 1;
        int element2 = 33;
        System.out.println("Хэш-таблица содержит элемент " + element1 + ": " + hashTable.contains(1));
        System.out.println("Хэш-таблица содержит элемент " + element2 + ": " + hashTable.contains(33));

        System.out.println("Удаление из хэш-таблицы элемента " + element1 + ": " + hashTable.remove(element1));
        System.out.println("Удаление из хэш-таблицы элемента " + element2 + ": " + hashTable.remove(element2));

        System.out.println("Размер хэш-таблицы после удаления элементов: " + hashTable.size());

        System.out.println("Хэш-таблица содержит элемент " + element1 + ": " + hashTable.contains(element1));

        Object[] array = hashTable.toArray();

        for (Object element : array) {
            System.out.println("Элемент массива: " + element);
        }

        System.out.println("Хэш-таблица: " + hashTable);

        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(3);
        System.out.println("Хэш-таблица содержит все элементы из списка " + list1 + ": " + hashTable.containsAll(list1));

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(6);
        System.out.println("Элементы из списка " + list2 + " добавлены в хэш-таблицу: " + hashTable.addAll(list2));
        System.out.println("Хэш-таблица: " + hashTable);

        System.out.println("Элементы из списка " + list2 + " удалены из хэш-таблицы: " + hashTable.removeAll(list2));
        System.out.println("Хэш-таблица: " + hashTable);

        List<Integer> list4 = new ArrayList<>();
        list4.add(5);
        list4.add(6);
        System.out.println("Все элементы, кроме элементов из списка " + list4 + ", были успешно удалены из хэш-таблицы: " + hashTable.retainAll(list4));

        System.out.println("Размер хэш-таблицы: " + hashTable.size());
        System.out.println("Хэш-таблица: " + hashTable);

        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4);

        for (Integer element : hashTable) {
            System.out.println("Элемент в хэш-таблице: " + element);
        }

        hashTable.clear();
        System.out.println("Хэш-таблица после применения метода clear: " + hashTable);
    }
}
