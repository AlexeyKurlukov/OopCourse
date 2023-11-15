package ru.academits.kurlukov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.9;

    private ArrayList<E>[] lists;
    private int size;
    private int modificationsCount;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Начальная вместимость не может быть меньше или равна 0. Передана вместимость: " + capacity);
        }

        // noinspection unchecked
        lists = new ArrayList[capacity];

        for (int i = 0; i < capacity; i++) {
            lists[i] = new ArrayList<>();
        }
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode()) % lists.length;
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
        ArrayList<E> list = lists[getIndex(object)];
        return list.contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex;
            private final int expectedModificationsCount = modificationsCount;
            private int elementsVisited;

            @Override
            public boolean hasNext() {
                int localCurrentIndex = currentIndex;
                int localElementsVisited = elementsVisited;

                while (localCurrentIndex < lists.length && localElementsVisited >= lists[localCurrentIndex].size()) {
                    localCurrentIndex++;
                    localElementsVisited = 0;
                }

                return localCurrentIndex < lists.length;
            }

            @Override
            public E next() {
                if (modificationsCount != expectedModificationsCount) {
                    throw new ConcurrentModificationException("Список был изменен");
                }

                if (!hasNext()) {
                    throw new NoSuchElementException("Нет больше элементов в списке");
                }

                while (currentIndex < lists.length && elementsVisited >= lists[currentIndex].size()) {
                    currentIndex++;
                    elementsVisited = 0;
                }

                E nextElement = lists[currentIndex].get(elementsVisited);
                elementsVisited++;
                return nextElement;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Метод remove() не поддерживается");
            }
        };
    }


    @Override
    public Object[] toArray() {
        Object[] hashTableArray = new Object[size];
        int i = 0;

        for (E element : this) {
            hashTableArray[i] = element;
            i++;
        }

        return hashTableArray;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            // noinspection unchecked
            return (T[]) Arrays.copyOf(this.lists, size, array.getClass());
        }

        int i = 0;

        for (E element : this) {
            ((Object[]) array)[i] = element;
            i++;
        }

        if (i < array.length) {
            array[i] = null;
        }

        return array;
    }


    private void rehash() {
        int newCapacity = lists.length * 2;
        // noinspection unchecked
        ArrayList<E>[] newLists = new ArrayList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newLists[i] = new ArrayList<>();
        }

        for (ArrayList<E> list : lists) {
            for (E element : list) {
                int index = (element == null) ? 0 : Math.abs(element.hashCode()) % newCapacity;
                newLists[index].add(element);
            }
        }

        lists = newLists;
    }

    @Override
    public boolean add(E element) {
        int index = (element == null) ? 0 : Math.abs(element.hashCode()) % lists.length;

        ArrayList<E> list = lists[index];
        list.add(element);
        size++;
        modificationsCount++;

        if ((double) size / lists.length > LOAD_FACTOR) {
            rehash();
        }

        return true;
    }

    @Override
    public boolean remove(Object object) {
        ArrayList<E> list = lists[getIndex(object)];

        if (list.remove(object)) {
            size--;
            modificationsCount++;
            return true;
        }

        return false;
    }

    private static void checkCollectionIsNull(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Коллекция не может быть null");
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkCollectionIsNull(collection);

        for (E element : collection) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        if (collection.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (Object element : collection) {
            while (remove(element)) {
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        boolean isModified = false;

        for (ArrayList<E> list : lists) {
            for (int i = list.size() - 1; i >= 0; i--) {
                E element = list.get(i);

                if (!collection.contains(element)) {
                    list.remove(i);
                    size--;
                    modificationsCount++;
                    isModified = true;
                }
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            list.clear();
        }

        size = 0;
        modificationsCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ArrayList<E> list : lists) {
            stringBuilder.append(list).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}