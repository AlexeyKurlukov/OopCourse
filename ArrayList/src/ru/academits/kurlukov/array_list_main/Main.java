package ru.academits.kurlukov.array_list_main;

import ru.academits.kurlukov.array_list.ArrayList;
import ru.academits.kurlukov.array_list.List;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("element1");
        arrayList.add("element2");
        arrayList.add("element3");
        arrayList.add("element4");
        arrayList.add("element1");

        System.out.println("Содержимое списка: " + arrayList);

        System.out.println("Список пустой: " + arrayList.isEmpty());

        int index1 = 2;
        arrayList.add(index1, "element5");
        System.out.println("После замены элемента по индексу " + index1 + ": " + arrayList);

        System.out.println("Индекс элемента 'element55' в списке: " + arrayList.indexOf("element55"));

        System.out.println("Индекс последнего вхождения элемента 'element1' в списке: " + arrayList.lastIndexOf("element1"));

        System.out.println("Элемент 'element55' удалён из списка: " + arrayList.remove("element55"));

        System.out.println("Список содержит элемент 'element5': " + arrayList.contains("element5"));

        int index2 = 2;
        arrayList.remove(index2);
        System.out.println("После удаления элемента по индексу " + index2 + ": " + arrayList);

        int index3 = 2;
        System.out.println("Элемент по индексу " + index3 + ": " + arrayList.get(index3));

        System.out.println("Размер списка: " + arrayList.getSize());

        int minCapacity = 10;
        arrayList.ensureCapacity(minCapacity);
        System.out.println("Содержимое списка после обеспечения ему минимальной вместимости " + minCapacity + ": " + arrayList);

        arrayList.trimToSize();
        System.out.println("Содержимое списка после уменьшения его вместимости до текущего размера: " + arrayList);

        int index4 = 3;
        arrayList.set(index4, "newElement");
        System.out.println("После замены элемента по индексу " + index4 + ": " + arrayList);

        arrayList.clear();
        System.out.println("Содержимое списка после очистки: " + arrayList);

        Collection<String> collection1 = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println("Все элементы из коллекции " + collection1 + " добавлены в список: " + arrayList.addAll(collection1));
        System.out.println("Содержимое списка: " + arrayList);

        int index5 = 0;
        System.out.println("Все элементы из коллекции " + collection1 + " добавлены в список по индексу " + index5 + ": " + arrayList.addAll(index5, collection1));
        System.out.println("Содержимое списка: " + arrayList);

        Collection<String> collection2 = Arrays.asList("a", "b", "c", "d", "e");
        arrayList.addAll(collection2);
        System.out.println("Содержимое списка: " + arrayList);

        System.out.println("Все элементы, кроме элементов из коллекции " + collection2 + ", были успешно удалены из списка: " + arrayList.retainAll(collection2));
        System.out.println("Содержимое списка: " + arrayList);

        System.out.println("Элементы из коллекции " + collection2 + " содержатся в списке: " + arrayList.containsAll(collection2));

        System.out.println("Элементы из коллекции " + collection2 + " были найдены в списке и успешно удалены: " + arrayList.removeAll(collection2));
        System.out.println("Содержимое списка: " + arrayList);

        ArrayList<String> newArrayList = new ArrayList<>();
        newArrayList.add("а");
        newArrayList.add("б");
        newArrayList.add("в");

        Iterator<String> iterator = newArrayList.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Элемент списка: " + element);
        }

        iterator = newArrayList.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();

            if (element.equals("б")) {
                iterator.remove();
            }
        }

        System.out.println("Содержимое списка " + newArrayList);
    }
}