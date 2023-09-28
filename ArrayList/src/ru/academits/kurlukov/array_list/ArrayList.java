package ru.academits.kurlukov.array_list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        items = new Object[capacity];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public void add(T element) {
        if (size == items.length) {
            increaseCapacity();
        }

        items[size] = element;
        size++;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невозможно выполнить операцию. Предоставленный индекс " + index +
                    " находится за пределами списка. Индекс должен быть в диапазоне [0, " + (size - 1) + "]");
        }
    }

    @Override
    public void add(int index, T element) {
        validateIndex(index);

        if (size == items.length) {
            ensureCapacity(size + 1);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        ++size;
    }

    @Override
    public boolean addAll(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (T element : collection) {
            add(element);
        }

        return size > initialSize;
    }

    @Override
    public boolean addAll(int index, Collection<T> collection) {
        validateIndex(index);

        if (collection == null || collection.isEmpty()) {
            return false;
        }

        int newSize = size + collection.size();

        if (newSize > items.length) {
            ensureCapacity(newSize);
        }

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        int i = index;

        for (Object element : collection) {
            items[i] = element;
            i++;
        }

        size += collection.size();
        return true;
    }

    @Override
    public void remove(int index) {
        validateIndex(index);

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        --size;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(items[i])) {
                if (i < size - 1) {
                    System.arraycopy(items, i + 1, items, i, size - i - 1);
                }

                items[size - 1] = null;
                --size;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<T> collection) {
        boolean modified = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                modified = true;
                i--;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) items[index];
    }

    @Override
    public void set(int index, T element) {
        validateIndex(index);
        items[index] = element;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(items[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<T> collection) {
        for (T element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<T> collection) {
        boolean modified = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                modified = true;
                i--;
            }
        }

        return modified;
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = items.length;

        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            items = Arrays.copyOf(items, newCapacity);
        }
    }

    @Override
    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.setLength((stringBuilder.length() - 2));
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}