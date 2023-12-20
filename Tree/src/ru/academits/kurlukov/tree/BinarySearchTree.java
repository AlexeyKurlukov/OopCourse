package ru.academits.kurlukov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private final Comparator<E> comparator;
    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        // noinspection unchecked
        return ((Comparable<? super E>) data1).compareTo(data2);
    }

    public void insert(E data) {
        if (root == null) {
            root = new Node<>(data);
            size++;
            return;
        }

        Node<E> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(data));
                    size++;
                    return;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(data));
                    size++;
                    return;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean contains(E data) {
        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult < 0) {
                currentNode = currentNode.getLeft();
            } else if (compareResult > 0) {
                currentNode = currentNode.getRight();
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        Node<E> currentNode = root;
        Node<E> parentNode = null;
        boolean isLeftChild = false;
        boolean isFound = false;

        while (currentNode != null) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult == 0) {
                isFound = true;
                break;
            }

            parentNode = currentNode;

            if (compareResult < 0) {
                currentNode = currentNode.getLeft();
                isLeftChild = true;
            } else {
                currentNode = currentNode.getRight();
                isLeftChild = false;
            }
        }

        if (!isFound) {
            return false;
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }

            size--;
            return true;
        }

        if (currentNode.getRight() == null) {
            if (parentNode == null) {
                root = currentNode.getLeft();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }

            size--;
            return true;
        }

        if (currentNode.getLeft() == null) {
            if (parentNode == null) {
                root = currentNode.getRight();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }

            size--;
            return true;
        }

        removeNodeWithTwoChildren(currentNode, parentNode);
        size--;
        return true;
    }

    private void removeNodeWithTwoChildren(Node<E> node, Node<E> parent) {
        Node<E> minRightNode = node.getRight();
        Node<E> minRightNodeParent = node;

        while (minRightNode.getLeft() != null) {
            minRightNodeParent = minRightNode;
            minRightNode = minRightNode.getLeft();
        }

        if (minRightNodeParent != node) {
            minRightNodeParent.setLeft(minRightNode.getRight());
        } else {
            node.setRight(minRightNode.getRight());
        }

        minRightNode.setLeft(node.getLeft());
        minRightNode.setRight(node.getRight());

        if (node == root) {
            root = minRightNode;
        } else if (parent.getLeft() == node) {
            parent.setLeft(minRightNode);
        } else {
            parent.setRight(minRightNode);
        }
    }

    public int size() {
        return size;
    }

    public void traverseInBreadth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<E> currentNode = queue.poll();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traverseInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<Node<E>> deque = new LinkedList<>();
        deque.push(root);

        while (!deque.isEmpty()) {
            Node<E> currentNode = deque.pop();

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                deque.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                deque.push(currentNode.getLeft());
            }
        }
    }

    public void traverseInDepthRecursive(Consumer<E> consumer) {
        traverseInDepthRecursive(root, consumer);
    }

    private void traverseInDepthRecursive(Node<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        traverseInDepthRecursive(currentNode.getLeft(), consumer);
        traverseInDepthRecursive(currentNode.getRight(), consumer);
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Бинарное дерево пусто";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Бинарное дерево поиска: ");
        traverseInBreadth(node -> stringBuilder.append(node).append(' '));
        return stringBuilder.toString();
    }
}