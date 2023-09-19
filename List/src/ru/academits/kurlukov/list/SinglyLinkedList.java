package ru.academits.kurlukov.list;

import java.util.NoSuchElementException;

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
        } else {
            Node<E> previousNode = getNodeAtIndex(index - 1);
            Node<E> currentNode = previousNode.getNext();
            previousNode.setNext(currentNode.getNext());
            count--;
            return currentNode.getData();
        }
    }

    public void insertFirst(E data) {
        head = new Node<>(data, head);
        count++;
    }

    public void insertAtIndex(int index, E data) {
        validateIndex(index);

        if (index == 0) {
            insertFirst(data);
            return;
        }

        Node<E> previousNode = getNodeAtIndex(index - 1);
        previousNode.setNext(new Node<>(data, previousNode.getNext()));
        count++;
    }

    public boolean removeByData(E data) {
        Node<E> currentNode = head;
        Node<E> previousNode = null;

        while (currentNode != null) {
            if (data == null && currentNode.getData() == null) {
                break;
            } else if (data != null && data.equals(currentNode.getData())) {
                break;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (currentNode == null) {
            return false;
        }

        if (previousNode == null) {
            head = currentNode.getNext();
        } else {
            previousNode.setNext(currentNode.getNext());
        }

        count--;
        return true;
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
        SinglyLinkedList<E> newList = new SinglyLinkedList<>();
        Node<E> currentNode = head;
        Node<E> tail = null;

        if (currentNode != null) {
            newList.head = new Node<>(currentNode.getData());
            tail = newList.head;
            currentNode = currentNode.getNext();
        }

        while (currentNode != null) {
            Node<E> newNode = new Node<>(currentNode.getData());
            tail.setNext(newNode);
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

        while (currentNode.getNext() != null) {
            stringBuilder.append(currentNode.getData()).append(", ");
            currentNode = currentNode.getNext();
        }

        stringBuilder.append(currentNode.getData()).append(']');
        return stringBuilder.toString();
    }
}