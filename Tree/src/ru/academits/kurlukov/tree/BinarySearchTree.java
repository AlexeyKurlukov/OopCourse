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
        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(data1, data2);
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
        Node<E> parentNode;

        while (true) {
            int compareResult;

            if (data != null && currentNode.getData() != null) {
                compareResult = compare(data, currentNode.getData());
            } else if (data == null && currentNode.getData() == null) {
                compareResult = 0;
            } else {
                compareResult = (data == null) ? -1 : 1;
            }

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

        if (isFound) {
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

            removeNodeWithTwoChildren(currentNode);
            size--;
            return true;
        }

        return false;
    }

    private void removeNodeWithTwoChildren(Node<E> node) {
        Node<E> minRightNode = findMinNode(node.getRight());
        Node<E> minRightNodeParent = findNodeParent(minRightNode, node.getRight());

        if (minRightNode.getRight() != null) {
            if (minRightNodeParent != node && minRightNodeParent != null) {
                minRightNodeParent.setLeft(minRightNode.getRight());
            } else {
                node.setRight(minRightNode.getRight());
            }

            minRightNode.setRight(null);
        } else {
            if (minRightNodeParent != node && minRightNodeParent != null) {
                minRightNodeParent.setLeft(null);
            } else {
                node.setRight(null);
            }
        }

        minRightNode.setLeft(node.getLeft());
        minRightNode.setRight(node.getRight());

        if (node == root) {
            root = minRightNode;
        } else {
            Node<E> parentNode = findNodeParent(node, root);

            if (parentNode.getLeft() == node) {
                parentNode.setLeft(minRightNode);
            } else {
                parentNode.setRight(minRightNode);
            }
        }
    }

    private Node<E> findNodeParent(Node<E> node, Node<E> root) {
        if (root == null || node == null || root == node) {
            return null;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> currentNode = queue.poll();

            if ((currentNode.getLeft() == node) || (currentNode.getRight() == node)) {
                return currentNode;
            }

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }

        return null;
    }

    private Node<E> findMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
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