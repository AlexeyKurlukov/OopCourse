package ru.academits.kurlukov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compareData(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        } else {
            // noinspection unchecked
            return ((Comparable<? super E>) data1).compareTo(data2);
        }
    }

    public void insert(E data) {
        if (root == null) {
            root = new Node<>(data);
            size++;
            return;
        }

        Node<E> currentNode = root;
        Node<E> parentNode;

        while (true) {
            if (data == null) {
                if (currentNode.getNullBranch() == null) {
                    currentNode.setNullBranch(new Node<>(null));
                    size++;
                    return;
                }
            } else {
                int compareResult = compareData(data, currentNode.getData());
                parentNode = currentNode;

                if (compareResult < 0) {
                    currentNode = currentNode.getLeft();

                    if (currentNode == null) {
                        parentNode.setLeft(new Node<>(data));
                        size++;
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();

                    if (currentNode == null) {
                        parentNode.setRight(new Node<>(data));
                        size++;
                        return;
                    }
                }
            }
        }
    }

    public boolean contains(E data) {
        Node<E> currentNode = root;

        while (currentNode != null) {
            if (data == null) {
                if (currentNode.getData() == null) {
                    return true;
                }

                currentNode = currentNode.getNullBranch();
            } else {
                int compareResult = compareData(data, currentNode.getData());

                if (compareResult < 0) {
                    currentNode = currentNode.getLeft();
                } else if (compareResult > 0) {
                    currentNode = currentNode.getRight();
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean remove(E data) {
        if (data == null) {
            if (root.getNullBranch() != null) {
                root.setNullBranch(null);
                size--;
                return true;
            }

            return false;
        }

        if (root == null) {
            return false;
        }

        Node<E> currentNode = root;
        Node<E> parentNode = null;
        boolean isLeftChild = false;

        while (currentNode != null) {
            int compareResult = compareData(data, currentNode.getData());

            if (compareResult == 0) {
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

                removeWithTwoChildren(currentNode);
                size--;
                return true;
            }

            if (compareResult < 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
                isLeftChild = true;
            } else {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
                isLeftChild = false;
            }
        }

        return false;
    }

    public void removeWithTwoChildren(Node<E> node) {
        Node<E> minRightNode = findMinNode(node.getRight());

        if (minRightNode == null) {
            node.setData(node.getRight().getData());
            node.setRight(node.getRight().getRight());
        } else {
            node.setData(minRightNode.getData());
            node.setRight(removeMinNode(node.getRight()));
        }
    }

    private Node<E> findMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() == null) {
            return node;
        }

        return findMinNode(node.getLeft());
    }

    private Node<E> removeMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() != null) {
            node.setLeft(removeMinNode(node.getLeft()));
            return node;
        }

        return node.getRight();
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

        return "BinarySearchTree {" +
                "root = " + root +
                '}';
    }
}