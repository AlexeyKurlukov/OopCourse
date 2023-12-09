package ru.academits.kurlukov.tree_main;

import ru.academits.kurlukov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(9);
        binarySearchTree.insert(13);
        binarySearchTree.insert(null);
        binarySearchTree.insert(6);
        binarySearchTree.insert(14);
        binarySearchTree.insert(8);
        binarySearchTree.insert(15);

        System.out.println(binarySearchTree);
        System.out.println("Размер дерева: " + binarySearchTree.size());

        Object element = null;
        System.out.println("В дереве есть элемент со значением " + element + ": " + binarySearchTree.contains(null));

        System.out.println("Элемент со значением " + element + " удалён из дерева: " + binarySearchTree.remove(null));
        System.out.println(binarySearchTree);
        System.out.println("Размер дерева: " + binarySearchTree.size());
        System.out.println();

        System.out.println("Обход в ширину:");
        binarySearchTree.traverseInBreadth(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println();

        System.out.println("Обход в глубину рекурсивным способом:");
        binarySearchTree.traverseInDepthRecursive(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println();

        System.out.println("Обход в глубину нерекурсивным способом:");
        binarySearchTree.traverseInDepth(data -> System.out.print(data + " "));
    }
}