package com.gojiralabs.gojira.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Iterators {

	private Iterators() {
		// private constructor to avoid instantiation
	}

	public static <T> Iterator<T> reversedIterator(List<T> list) {
		return reversedIterable(list).iterator();
	}

	public static <T> Iterator<T> reversedIterator(ListIterator<T> listIterator) {
		return reversedIterable(listIterator).iterator();
	}

	public static <T> Iterable<T> reversedIterable(List<T> list) {
		return reversedIterable(list.listIterator(list.size()));
	}

	public static <T> Iterable<T> reversedIterable(ListIterator<T> listIterator) {
		return new ReversedIterable<>(listIterator);
	}

	private static class ReversedIterable<E> implements Iterable<E> {

		private ListIterator<E> listIterator;

		public ReversedIterable(ListIterator<E> listIterator) {
			this.listIterator = listIterator;
		}

		@Override

		public Iterator<E> iterator() {
			return new ReversedIterator<>(listIterator);
		}
	}

	private static class ReversedIterator<E> implements Iterator<E> {

		private ListIterator<E> listIterator;

		public ReversedIterator(ListIterator<E> listIterator) {
			this.listIterator = listIterator;
		}

		@Override
		public boolean hasNext() {
			return listIterator.hasPrevious();
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return listIterator.previous();
		}

		@Override
		public void remove() {
			listIterator.remove();
		}
	}
}
