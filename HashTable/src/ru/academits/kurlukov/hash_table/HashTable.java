package ru.academits.kurlukov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] buckets;
    private int size;
    private int modificationsCount;

    public HashTable() {
        int capacity = 10;
        // noinspection unchecked
        buckets = new ArrayList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Начальная вместимость не может быть меньше или равна 0. Передана вместимость: " + capacity);
        }

        // noinspection unchecked
        buckets = new ArrayList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode()) % buckets.length;
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
            ArrayList<E> bucket = buckets[0];
            return bucket.contains(null);
        }

        ArrayList<E> bucket = buckets[getIndex(object)];
        return bucket.contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int bucketIndex = 0;
            private int elementIndex = 0;
            private int expectedModificationsCount = modificationsCount;

            @Override
            public boolean hasNext() {
                while (bucketIndex < buckets.length && elementIndex >= buckets[bucketIndex].size()) {
                    bucketIndex++;
                    elementIndex = 0;
                }

                return bucketIndex < buckets.length;
            }

            private void checkModification() {
                if (modificationsCount != expectedModificationsCount) {
                    throw new ConcurrentModificationException("Список был изменен");
                }
            }

            @Override
            public E next() {
                checkModification();

                if (!hasNext()) {
                    throw new NoSuchElementException("Нет больше элементов в списке");
                }

                int currentIndex = elementIndex;
                elementIndex++;
                return buckets[bucketIndex].get(currentIndex);
            }

            @Override
            public void remove() {
                checkModification();

                if (elementIndex == 0) {
                    throw new IllegalStateException("Метод remove() может быть вызван только один раз после каждого вызова next()");
                }

                buckets[bucketIndex].remove(elementIndex - 1);
                elementIndex--;
                size--;
                expectedModificationsCount = modificationsCount;
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
            array = Arrays.copyOf(array, size);
        } else if (array.length > size) {
            Arrays.fill(array, size, array.length, null);
        }

        int i = 0;

        for (E element : this) {
            // noinspection unchecked
            array[i] = (T) element;
            i++;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            ArrayList<E> bucket = buckets[0];
            bucket.add(null);
            size++;
            modificationsCount++;
            return true;
        }

        ArrayList<E> bucket = buckets[getIndex(element)];

        if (bucket.add(element)) {
            size++;
            modificationsCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object object) {
        if (object == null) {
            ArrayList<E> bucket = buckets[0];

            if (bucket.remove(null)) {
                size--;
                modificationsCount++;
                return true;
            }
        } else {
            ArrayList<E> bucket = buckets[getIndex(object)];

            if (bucket.remove(object)) {
                size--;
                modificationsCount++;
                return true;
            }
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

        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkCollectionIsNull(collection);

        boolean isModified = false;

        for (E element : collection) {
            if (add(element)) {
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        if (collection.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (Object o : collection) {
            if (remove(o)) {
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        checkCollectionIsNull(collection);

        boolean isModified = false;

        for (ArrayList<E> bucket : buckets) {
            Iterator<E> iterator = bucket.iterator();

            while (iterator.hasNext()) {
                E element = iterator.next();

                if (!collection.contains(element)) {
                    iterator.remove();
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
        for (ArrayList<E> bucket : buckets) {
            bucket.clear();
        }

        size = 0;
        modificationsCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ArrayList<E> bucket : buckets) {
            stringBuilder.append(bucket.toString()).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}