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

        int index1 = 3;
        System.out.println("Значение элемента списка по индексу " + index1 + ": " + list.getAtIndex(index1));

        int index2 = 3;
        int data1 = 5;
        System.out.println("Старое значение элемента списка по индексу " + index2 + ": " + list.setAtIndex(index2, data1));
        System.out.println(list);

        int index3 = 1;
        System.out.println("Значение удаленного элемента по индексу " + index3 + ": " + list.removeAtIndex(index3));
        System.out.println(list);

        int index4 = 2;
        int data2 = 88;
        list.insertAtIndex(index4, data2);
        System.out.println("Список после вставки элемента со значением " + data2 + " по индексу " + index4 + ": " + list);

        int data3 = 88;
        System.out.println("Узел со значением " + data3 + " был найден в списке и удален: " + list.removeByData(data3));
        System.out.println(list);

        System.out.println("Значение первого, удалённого элемента списка " + list.removeFirst());
        System.out.println(list);

        list.reverse();
        System.out.println("Развёрнутый список: " + list);

        SinglyLinkedList<Integer> newList = list.copy();
        System.out.println("Копия списка: " + newList);
    }
}