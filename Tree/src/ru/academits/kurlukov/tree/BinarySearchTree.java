package ru.academits.kurlukov.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        private E data;
        private Node left;
        private Node right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
    }

    private void validateNotNull(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Значение не может быть null");
        }
    }

    public void insert(E value) {
        validateNotNull(value);

        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node currentNode = root;
        Node parent = null;

        while (currentNode != null) {
            parent = currentNode;
            int compareResult = value.compareTo(currentNode.data);

            if (compareResult < 0) {
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                currentNode = currentNode.right;
            } else {
                return;
            }
        }

        if (value.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
    }

    public boolean search(E value) {
        validateNotNull(value);

        Node currentNode = root;

        while (currentNode != null) {
            int compareResult = value.compareTo(currentNode.data);

            if (compareResult < 0) {
                currentNode = currentNode.left;
            } else if (compareResult > 0) {
                currentNode = currentNode.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public void remove(E value) {
        root = removeNode(root, value);
        size--;
    }

    private Node removeNode(Node currentNode, E value) {
        validateNotNull(value);

        if (currentNode == null) {
            return null;
        }

        int compareResult = value.compareTo(currentNode.data);

        if (compareResult < 0) {
            currentNode.left = removeNode(currentNode.left, value);
            return currentNode;
        }

        if (compareResult > 0) {
            currentNode.right = removeNode(currentNode.right, value);
            return currentNode;
        }

        if (currentNode.left == null && currentNode.right == null) {
            return null;
        }

        if (currentNode.left == null) {
            return currentNode.right;
        }

        if (currentNode.right == null) {
            return currentNode.left;
        }

        E minValue = findMinValue(currentNode.right);
        currentNode.data = minValue;
        currentNode.right = removeNode(currentNode.right, minValue);
        return currentNode;
    }

    private E findMinValue(Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode.data;
    }

    public int size() {
        return size;
    }

    public void traverseBreadthFirst(Consumer<E> processNode) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            processNode.accept(currentNode.data);

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public void traverseInDepthNonRecursive() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            System.out.println(currentNode.data);

            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    public void traverseInDepthRecursive() {
        traverseInDepthRecursive(root);
    }

    private void traverseInDepthRecursive(Node currentNode) {
        if (currentNode == null) {
            return;
        }

        System.out.println(currentNode.data);

        traverseInDepthRecursive(currentNode.left);
        traverseInDepthRecursive(currentNode.right);
    }
}