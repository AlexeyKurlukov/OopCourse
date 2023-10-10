package ru.academits.kurlukov.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int size;
    private int modificationCount;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Начальная вместимость не может быть отрицательной");
        }

        items = (T[]) new Object[capacity];
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
        return (indexOf(object) != -1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex;
        private int expectedModificationCount;
        private boolean isRemoved;

        public ArrayListIterator() {
            expectedModificationCount = modificationCount;
            isRemoved = false;
        }

        @Override
        public boolean hasNext() {
            checkModification();
            return currentIndex < size;
        }

        @Override
        public T next() {
            checkModification();

            if (!hasNext()) {
                throw new NoSuchElementException("Нет больше элементов в списке");
            }

            isRemoved = false;
            return items[currentIndex++];
        }

        private void checkModification() {
            if (modificationCount != expectedModificationCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
        }

        @Override
        public void remove() {
            checkModification();

            if (isRemoved) {
                throw new IllegalStateException("Метод remove() может быть вызван только один раз после каждого вызова next()");
            }

            if (currentIndex == 0 || currentIndex > size) {
                throw new IndexOutOfBoundsException("Некорректное состояние итератора. Индекс currentIndex находится за пределами допустимого диапазона [0, " + (size - 1) + "]");
            }

            ArrayList.this.remove(currentIndex - 1);
            currentIndex--;
            expectedModificationCount++;
            isRemoved = true;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            return (T1[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        add(size, item);
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index;

        if (object == null) {
            index = indexOf(null);
        } else {
            index = indexOf(object);
        }

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
        modificationCount++;
        return items[index];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Предоставленный индекс " + index +
                    " находится за пределами списка. Индекс должен быть в диапазоне [0, " + (size - 1) + "]");
        }
    }

    private void validateInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Предоставленный индекс " + index +
                    " находится за пределами списка. Индекс для вставки должен быть в диапазоне [0, " + size + "]");
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void checkNullCollection(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Передана коллекция со значением null");
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkNullCollection(collection);

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        int oldSize = size;
        addAll(size, collection);
        return size > oldSize;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkNullCollection(collection);
        validateInsertIndex(index);

        int newSize = size + collection.size();
        ensureCapacity(newSize);
        System.arraycopy(items, index, items, index + collection.size(), size - index);
        int i = index;

        for (T item : collection) {
            items[i] = item;
            i++;
        }

        size += collection.size();
        modificationCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkNullCollection(collection);
        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        if (isModified) {
            modificationCount++;
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        checkNullCollection(collection);
        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        if (isModified) {
            modificationCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        Arrays.fill(items, 0, size, null);
        size = 0;
        modificationCount++;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public T set(int index, T item) {
        validateIndex(index);
        modificationCount++;
        return items[index] = item;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
        } else {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    @Override
    public void add(int index, T item) {
        validateInsertIndex(index);

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modificationCount++;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null) {
                if (items[i] == null) {
                    return i;
                }
            } else {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null) {
                if (items[i] == null) {
                    return i;
                }
            } else {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
