package ru.academits.kurlukov.list_main;

import ru.academits.kurlukov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("Размер списка: " + list.getSize());

        System.out.println("Значение первого элемента списка: " + list.getFirstElementValue());

        list.insertElementAtStart(10);
        list.insertElementAtIndex(1, 20);
        list.insertElementAtIndex(2, 30);
        list.insertElementAtIndex(3, 40);
        list.insertElementAtIndex(4, 50);
        list.insertElementAtIndex(5, 60);
        list.insertElementAtIndex(6, 70);
        System.out.println("Исходный список: " + list);

        System.out.println("Значение первого элемента списка: " + list.getFirstElementValue());

        System.out.println("Значение элемента списка по заданному индексу: " + list.getElementValueAtIndex(2));

        System.out.println("Старое значение элемента списка по заданному индексу: " + list.setElementValueAtIndex(2, 8));

        System.out.println("Значение удаленного элемента по заданному индексу: " + list.removeElementAtIndex(3));
        System.out.println(list);

        System.out.println("Узел с указанным значением был найден в списке и удален: " + list.removeNodeByValue(2));
        System.out.println(list);

        list.removeFirstElement();
        System.out.println("Список после удаления первого элемента: " + list);

        list.reverseList();
        System.out.println("Развёрнутый список: " + list);

        list.insertElementAtEnd(55);
        System.out.println("Список после вставки элемента в конец: " + list);

        SinglyLinkedList listCopy = list.copyList();
        System.out.println("Копия списка: " + listCopy);
    }
}