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
    }

    private static int getIndex(Object object, int capacity) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode()) % capacity;
    }

    private int getIndex(Object object) {
        return getIndex(object, lists.length);
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
        int index = getIndex(object);
        return lists[index] != null && lists[index].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModificationsCount = modificationsCount;
            private int currentListIndex;
            private int currentElementIndex;
            private int visitedElementsCount;

            @Override
            public boolean hasNext() {
                return visitedElementsCount < size;
            }

            @Override
            public E next() {
                if (modificationsCount != expectedModificationsCount) {
                    throw new ConcurrentModificationException("Список был изменен");
                }

                if (!hasNext()) {
                    throw new NoSuchElementException("Нет больше элементов в списке");
                }

                while (currentListIndex < lists.length && (lists[currentListIndex] == null || currentElementIndex >= lists[currentListIndex].size())) {
                    currentListIndex++;
                    currentElementIndex = 0;
                }

                visitedElementsCount++;
                E element = lists[currentListIndex].get(currentElementIndex);
                currentElementIndex++;
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    array[i] = element;
                    i++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        int i = 0;

        for (E element : this) {
            // noinspection unchecked
            array[i] = (T) element;
            i++;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    private void rehash(int newCapacity) {
        // noinspection unchecked
        ArrayList<E>[] newLists = new ArrayList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newLists[i] = new ArrayList<>();
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    if (element != null) {
                        int index = getIndex(element, newCapacity);
                        newLists[index].add(element);
                    } else {
                        newLists[0].add(null);
                    }
                }
            }
        }

        lists = newLists;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].isEmpty()) {
                lists[i] = null;
            }
        }
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if ((double) (size + 1) / lists.length > LOAD_FACTOR) {
            int newCapacity = lists.length * 2;
            rehash(newCapacity);
            index = getIndex(element);
        }

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);
        size++;
        modificationsCount++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        ArrayList<E> list = lists[getIndex(object)];

        if (list != null && list.remove(object)) {
            size--;
            modificationsCount++;
            return true;
        }

        return false;
    }

    private static void checkCollectionIsNull(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
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

        if (collection.isEmpty()) {
            return false;
        }

        int totalSize = size + collection.size();
        double updatedLoadFactor = (double) totalSize / lists.length;

        if (updatedLoadFactor > LOAD_FACTOR) {
            int newCapacity = Math.max((int) (totalSize / LOAD_FACTOR), lists.length);

            if (newCapacity > lists.length) {
                rehash(newCapacity);
            }
        }

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
            if (list != null) {
                int initialListSize = list.size();
                list.retainAll(collection);
                size -= initialListSize - list.size();
                isModified = isModified || (list.size() < initialListSize);
            }
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

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
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