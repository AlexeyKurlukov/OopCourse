package ru.academits.kurlukov.array_list;

import java.util.Collection;
import java.util.Iterator;

public interface List<T> {
    boolean isEmpty();

    int getSize();

    void add(T element);

    void add(int index, T element);

    boolean addAll(Collection<T> collection);

    boolean addAll(int index, Collection<T> collection);

    void remove(int index);

    boolean remove(T element);

    boolean removeAll(Collection<T> collection);

    void clear();

    T get(int index);

    void set(int index, T element);

    int indexOf(T element);

    int lastIndexOf(T element);

    boolean contains(T element);

    boolean containsAll(Collection<T> collection);

    boolean retainAll(Collection<T> collection);

    void ensureCapacity(int minCapacity);

    void trimToSize();

    Iterator<T> iterator();
}