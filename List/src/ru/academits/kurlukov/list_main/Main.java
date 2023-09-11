package ru.academits.kurlukov.list_main;

import ru.academits.kurlukov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(55);
        list.insertFirst(56);
        list.insertFirst(57);
        list.insertFirst(58);
        list.insertFirst(59);
        list.insertFirst(60);
        System.out.println("Исходный список: " + list);

        System.out.println("Количество элементов списка: " + list.getCount());

        System.out.println("Значение первого элемента списка: " + list.getFirst());

        System.out.println("Значение элемента списка по заданному индексу: " + list.getAtIndex(5));

        System.out.println("Старое значение элемента списка по заданному индексу: " + list.setAtIndex(5, 5));
        System.out.println(list);

        System.out.println("Значение удаленного элемента по заданному индексу: " + list.removeAtIndex(2));
        System.out.println(list);

        list.insertAtIndex(5, 88);
        System.out.println("Список после вставки элемента по заданному индексу " + list);

        System.out.println("Узел с указанным значением был найден в списке и удалён: " + list.removeByValue(88));
        System.out.println(list);

        System.out.println("Значение первого, удалённого элемента списка " + list.removeFirst());
        System.out.println(list);

        list.reverse();
        System.out.println("Развёрнутый список: " + list);

        SinglyLinkedList<Integer> newList = list.copy();
        System.out.println("Копия списка: " + newList);
    }
}