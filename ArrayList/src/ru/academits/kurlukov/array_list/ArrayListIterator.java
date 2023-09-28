package ru.academits.kurlukov.array_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListIterator<T> implements Iterator<T> {
    private final ArrayList<T> list;
    private int position;

    public ArrayListIterator(ArrayList<T> list) {
        this.list = list;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < list.getSize();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Нет больше элементов в списке");
        }

        T element = list.get(position);
        position++;
        return element;
    }

    @Override
    public void remove() {
        if (position == 0) {
            throw new IllegalStateException("Следующий метод не был вызван или метод remove уже был вызван после последнего вызова метода next");
        }

        list.remove(position - 1);
        position--;
    }
}