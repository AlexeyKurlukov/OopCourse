package ru.academits.kurlukov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final Object DELETED = new Object();

    private E[] table;
    private int size;
    private int modificationsCount;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Начальная вместимость не может быть отрицательной. Передана вместимость: " + capacity);
        }

        table = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        if (object == null) {
            for (Object value : table) {
                if (value == null) {
                    return true;
                }
            }
        } else {
            int hash = object.hashCode();
            int index = Math.abs(hash) % table.length;
            return table[index] != null && table[index].equals(object);
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentIndex;
        private int elementsVisited;
        private int expectedModificationsCount;

        public HashTableIterator() {
            expectedModificationsCount = modificationsCount;
        }

        private void checkModification() {
            if (modificationsCount != expectedModificationsCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
        }

        @Override
        public boolean hasNext() {
            checkModification();
            return elementsVisited < size;
        }

        @Override
        public E next() {
            checkModification();

            while (table[currentIndex] == null || table[currentIndex] == DELETED) {
                currentIndex++;
            }

            E element = table[currentIndex];
            currentIndex++;
            elementsVisited++;
            return element;
        }
    }


    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        for (Object element : table) {
            if (element != null && element != DELETED) {
                array[index] = element;
                index++;
            }
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {

            return (T[]) Arrays.copyOf(table, size, array.getClass());
        }

        System.arraycopy(table, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (table[index] == null) {
            table[index] = element;
            size++;
            modificationsCount++;
            return true;
        }

        return false;
    }

    private int getIndex(E element) {
        return Math.abs(element.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object object) {
        if (object == null) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    table[i] = (E) DELETED;
                    size--;
                    modificationsCount++;
                    return true;
                }
            }
        } else {
            int hash = object.hashCode();
            int index = Math.abs(hash) % table.length;

            if (table[index] != null && table[index].equals(object)) {
                table[index] = (E) DELETED;
                size--;
                modificationsCount++;
                return true;
            }
        }

        return false;
    }

    private void checkCollectionIsNull(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Коллекция не может быть null");
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > table.length) {
            table = Arrays.copyOf(table, minCapacity);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkCollectionIsNull(collection);
        boolean isModified = false;

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collection.size());

        for (E element : collection) {
            if (add(element)) {
                isModified = true;
            }
        }

        if (isModified) {
            modificationsCount++;
        }

        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkCollectionIsNull(collection);
        boolean isModified = false;

        if (collection.isEmpty()) {
            return false;
        }

        for (Object element : collection) {
            if (remove(element)) {
                isModified = true;
            }
        }

        if (isModified) {
            modificationsCount++;
        }

        return isModified;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean retainAll(Collection<?> collection) {
        checkCollectionIsNull(collection);
        Objects.requireNonNull(collection);
        boolean isModified = false;
        Object[] newArray = new Object[table.length];
        int newIndex = 0;

        for (Object element : table) {
            if (element != null && element != DELETED && collection.contains(element)) {
                newArray[newIndex] = element;
                newIndex++;
            }
        }

        if (newIndex < size) {
            table = (E[]) newArray;
            size = newIndex;
            isModified = true;
        }

        if (isModified) {
            modificationsCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(table, null);
        size = 0;
        modificationsCount++;
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(table);
        hash = prime * hash + size;
        hash = prime * hash + modificationsCount;

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        HashTable<?> hashTable = (HashTable<?>) object;

        if (size() != hashTable.size()) {
            return false;
        }

        for (Object item : this) {
            if (!hashTable.contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        boolean first = true;

        for (Object value : table) {
            if (value != null && value != DELETED) {
                if (!first) {
                    stringBuilder.append(", ");
                }

                stringBuilder.append(value);
                first = false;
            }
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}