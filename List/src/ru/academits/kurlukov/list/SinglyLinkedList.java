package ru.academits.kurlukov.list;

public class SinglyLinkedList {
    private Node head;
    private int length;

    public SinglyLinkedList() {
        head = null;
        length = 0;
    }

    public int getSize() {
        return length;
    }

    public int getFirstElementValue() {
        if (head != null) {
            return head.data;
        }

        return -1;
    }

    public int getElementValueAtIndex(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }

        Node current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current.data;
            }

            current = current.next;
            count++;
        }

        throw new IndexOutOfBoundsException("Невозможно получить значение элемента по указанному индексу. Переданный индекс "
                + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + length + "]");
    }

    public int setElementValueAtIndex(int index, int value) {
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }

        Node current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                int oldValue = current.data;
                current.data = value;
                return oldValue;
            }

            current = current.next;
            count++;
        }

        throw new IndexOutOfBoundsException("Невозможно изменить значение элемента по указанному индексу. Переданный индекс "
                + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + length + "]");
    }

    public int removeElementAtIndex(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException("Список пуст");
            }

            int value = head.data;
            head = head.next;
            length--;
            return value;
        }

        Node current = head;
        Node previous = null;
        int count = 0;

        while (current != null) {
            if (count == index) {
                previous.next = current.next;
                int value = current.data;
                length--;
                return value;
            }

            previous = current;
            current = current.next;
            count++;
        }

        throw new IndexOutOfBoundsException("Невозможно удалить элемент по указанному индексу. Переданный индекс "
                + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + length + "]");
    }

    public void insertElementAtStart(int value) {
        Node resultingHeadNode = new Node(value);

        if (head != null) {
            resultingHeadNode.next = head;
        }

        head = resultingHeadNode;
        length++;
    }

    public void insertElementAtIndex(int index, int value) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Невозможно вставить элемент по указанному индексу. Переданный индекс "
                    + index + " выходит за пределы списка. Индекс должен быть в диапазоне [0, " + length + "]");
        }

        Node resultingNode = new Node(value);

        if (index == 0) {
            resultingNode.next = head;
            head = resultingNode;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            resultingNode.next = current.next;
            current.next = resultingNode;
        }

        length++;
    }

    public boolean removeNodeByValue(int value) {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        if (head.data == value) {
            head = head.next;
            length--;
            return true;
        }

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.data == value) {
                previous.next = current.next;
                length--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public void removeFirstElement() {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        head = head.next;
        length--;
    }

    public void reverseList() {
        if (head == null || head.next == null) {
            return;
        }

        Node previous = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head = previous;
    }

    public void insertElementAtEnd(int value) {
        Node resultingNode = new Node(value);

        if (head == null) {
            head = resultingNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = resultingNode;
        }

        length++;
    }

    public SinglyLinkedList copyList() {
        SinglyLinkedList listCopy = new SinglyLinkedList();
        Node current = head;

        while (current != null) {
            listCopy.insertElementAtEnd(current.data);
            current = current.next;
        }

        return listCopy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Список пуст";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Node current = head;

        while (current != null) {
            stringBuilder.append(current.data).append(" ");
            current = current.next;
        }

        return stringBuilder.toString();
    }
}