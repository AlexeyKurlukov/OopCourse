package ru.academits.kurlukov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private Node<E> head;
    private int count;

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Невозможно выполнить операцию. Предоставленный индекс " + index +
                    " находится за пределами списка. Индекс должен быть в диапазоне [0, " + (count - 1) + "]");
        }
    }

    private Node<E> getNodeAtIndex(int index) {
        Node<E> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    public E getAtIndex(int index) {
        validateIndex(index);
        return getNodeAtIndex(index).getData();
    }

    public E setAtIndex(int index, E data) {
        validateIndex(index);
        Node<E> node = getNodeAtIndex(index);
        E oldData = node.getData();
        node.setData(data);
        return oldData;
    }

    public E removeAtIndex(int index) {
        validateIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<E> previousNode = getNodeAtIndex(index - 1);
        Node<E> currentNode = previousNode.getNext();
        previousNode.setNext(currentNode.getNext());
        count--;
        return currentNode.getData();

    }

    public void insertFirst(E data) {
        head = new Node<>(data, head);
        count++;
    }

    public void insertAtIndex(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Невозможно выполнить операцию. Предоставленный индекс " + index +
                    " находится за пределами списка. Индекс должен быть в диапазоне [0, " + count + "]");
        }

        if (index == 0) {
            insertFirst(data);
            return;
        }

        Node<E> previousNode = getNodeAtIndex(index - 1);
        Node<E> newNode = new Node<>(data);
        newNode.setNext(previousNode.getNext());
        previousNode.setNext(newNode);
        count++;
    }

    public boolean removeByData(E data) {
        Node<E> currentNode = head;
        Node<E> previousNode = null;

        while (currentNode != null) {
            if (Objects.equals(data, currentNode.getData())) {
                if (previousNode == null) {
                    head = head.getNext();
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

        E removedData = head.getData();
        head = head.getNext();
        count--;
        return removedData;
    }

    public void reverse() {
        if (count <= 1) {
            return;
        }

        Node<E> previousNode = null;
        Node<E> currentNode = head;

        while (currentNode != null) {
            Node<E> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public SinglyLinkedList<E> copy() {
        if (head == null) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> newList = new SinglyLinkedList<>();
        Node<E> currentNode = head;
        Node<E> newListTail = new Node<>(currentNode.getData());
        newList.head = newListTail;
        currentNode = currentNode.getNext();

        while (currentNode != null) {
            Node<E> newNode = new Node<>(currentNode.getData());
            newListTail.setNext(newNode);
            newListTail = newNode;
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
            stringBuilder.append(currentNode.getData()).append(", ");
            currentNode = currentNode.getNext();
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}