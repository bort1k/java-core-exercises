package ua.procamp;

import ua.procamp.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {

	private Node<T> head;
	private int size;

	@Override
	public void push(T element) {
		if(isEmpty()) {
			head = new Node<>(element);
		}else {
			head = new Node<>(element,head);
		}
		size++;
	}

	@Override
	public T pop() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		T item = head.item;
		head = head.next;
		size--;
		return item;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size ==0;
	}

	private static class Node<E> {
		E item;
		Node<E> next;

		Node(E element, Node<E> next) {
			this(element);
			this.next = next;
		}

		Node(E element){
			this.item = element;
		}
	}
}
