package ua.procamp.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    Node<T> root;

    @Override
    public boolean insert(T element) {
        if(root == null){
            root = new Node<>(element);
            return true;
        }
        Node<T> temp = root;
        while (true){
            if(temp.item.compareTo(element) == 0){
                return false;
            }else {
                if (temp.item.compareTo(element) > 0){
                    if(temp.right == null){
                        temp.right = new Node<>(element);
                        return true;
                    }
                    temp = temp.right;
                }else{
                    if(temp.left == null){
                        temp.left = new Node<>(element);
                        return true;
                    }
                    temp = temp.left;
                }
            }
        }
    }


    @Override
    public boolean search(T element) {
        if(root == null){
            return false;
        }
        Node<T> temp = root;
        while (temp != null){
            if(temp.item.compareTo(element) == 0){
                return true;
            }else {
                if (temp.item.compareTo(element) > 0){
                    temp = temp.right;
                }else{
                    temp = temp.left;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return counter(root);

    }
    private int counter(Node<T> node){
        if(node == null){
            return 0;
        }
        return 1 + counter(node.left) + counter(node.right);
    }

    @Override
    public int height() {
        if(root == null){
            return 0;
        }
        return Math.max(heightCounter(root.right),heightCounter(root.left));
    }

    private int heightCounter(Node<T> node){
        if(node == null){
            return 0;
        }
        int leftHeight = heightCounter(node.left);
        int rightHeight = heightCounter(node.right);
        return 1 + Math.max(leftHeight,rightHeight);
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        toList().stream()
                .sorted()
                .forEach(consumer);
    }

    private List<T> toList(){
        List<T>  list = new ArrayList<>();
        addToList(root,list);
        return list;
    }
    private void addToList(Node<T> node, List<T> list){
        if(node != null){
            list.add(node.item);
            addToList(node.left,list);
            addToList(node.right,list);
        }
    }

    private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;

        Node(E element){
            this.item = element;
        }
    }
}
