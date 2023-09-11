package ru.academits.kurlukov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private Node<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private Node<E> getNodeAtIndex(int index) {
        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    public E getAtIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Невозможно получить значение элемента по указанному индексу. Переданный индекс "
                    + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + (count - 1) + "]");
        }

        Node<E> node = getNodeAtIndex(index);
        return node.getData();
    }

    public E setAtIndex(int index, E data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Невозможно изменить значение элемента по указанному индексу. Переданный индекс "
                    + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + (count - 1) + "]");
        }

        Node<E> node = getNodeAtIndex(index);
        E oldValue = node.getData();
        node.setData(data);
        return oldValue;
    }

    public E removeAtIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Невозможно удалить элемент по указанному индексу. Переданный индекс "
                    + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + (count - 1) + "]");
        }

        Node<E> currentNode = head;
        Node<E> previousNode = null;

        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (previousNode == null) {
            head = currentNode.getNext();
        } else {
            previousNode.setNext(currentNode.getNext());
        }

        count--;
        return currentNode.getData();
    }

    public void insertFirst(E data) {
        head = new Node<>(data, head);
        count++;
    }

    public void insertAtIndex(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Невозможно вставить элемент по указанному индексу. Переданный индекс "
                    + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + (count - 1) + "]");
        }

        if (index == 0) {
            insertFirst(data);
        } else {
            Node<E> newNode = new Node<>(data);
            Node<E> currentNode = head;
            Node<E> previousNode = null;

            for (int i = 0; i < index; i++) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }

            previousNode.setNext(newNode);
            newNode.setNext(currentNode);
            count++;
        }
    }

    public boolean removeByValue(E data) {
        Node<E> currentNode = head;
        Node<E> previousNode = null;

        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                if (previousNode == null) {
                    head = currentNode.getNext();
                } else {
                    previousNode.setNext(currentNode.getNext());
                }

                count--;
                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return false;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        Node<E> removedNode = head;
        head = head.getNext();
        count--;
        return removedNode.getData();
    }

    public void reverse() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node<E> previousNode = null;
        Node<E> currentNode = head;
        Node<E> nextNode;

        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> newList = new SinglyLinkedList<>();
        Node<E> currentNode = head;
        Node<E> tail = null;

        while (currentNode != null) {
            Node<E> newNode = new Node<>(currentNode.getData());

            if (tail == null) {
                newList.head = newNode;
            } else {
                tail.setNext(newNode);
            }

            tail = newNode;
            currentNode = currentNode.getNext();
        }

        newList.count = count;
        return newList;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Node<E> currentNode = head;

        while (currentNode != null) {
            if (currentNode.getNext() == null) {
                stringBuilder.append(currentNode.getData());
            } else {
                stringBuilder.append(currentNode.getData()).append(", ");
            }

            currentNode = currentNode.getNext();
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}