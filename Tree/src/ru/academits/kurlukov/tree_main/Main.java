package ru.academits.kurlukov.tree_main;

import ru.academits.kurlukov.tree.BinarySearchTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(9);
        binarySearchTree.insert(10);
        binarySearchTree.insert(8);
        binarySearchTree.insert(11);
        binarySearchTree.insert(5);
        binarySearchTree.insert(15);
        binarySearchTree.insert(7);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(2);
        binarySearchTree.insert(1);

        int element = 1;
        System.out.println("В дереве есть элемент со значением " + element + ": " + binarySearchTree.search(element));
        System.out.println("Размер дерева: " + binarySearchTree.size());

        binarySearchTree.remove(element);
        System.out.println("В дереве есть элемент со значением " + element + ": " + binarySearchTree.search(element));

        System.out.println("Размер дерева: " + binarySearchTree.size());
        System.out.println();

        System.out.println("Обход в ширину:");
        Consumer<Integer> printConsumer = System.out::println;
        binarySearchTree.traverseBreadthFirst(printConsumer);
        System.out.println();

        System.out.println("Обход в глубину рекурсивным способом:");
        binarySearchTree.traverseInDepthRecursive();
        System.out.println();

        System.out.println("Обход в глубину нерекурсивным способом:");
        binarySearchTree.traverseInDepthNonRecursive();
    }
}