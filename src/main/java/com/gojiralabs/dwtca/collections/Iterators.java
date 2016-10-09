package com.gojiralabs.dwtca.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Nonnull;

public class Iterators<T> {

	public static <T> Iterator<T> reversedIterator(@Nonnull List<T> list) {
		return reversedIterable(list).iterator();
	}

	public static <T> Iterator<T> reversedIterator(@Nonnull ListIterator<T> listIterator) {
		return reversedIterable(listIterator).iterator();
	}

	public static <T> Iterable<T> reversedIterable(@Nonnull List<T> list) {
		return reversedIterable(list.listIterator(list.size()));
	}

	public static <T> Iterable<T> reversedIterable(@Nonnull ListIterator<T> listIterator) {
		return new ReversedIterable<>(listIterator);
	}

	private static class ReversedIterable<E> implements Iterable<E> {

		private ListIterator<E> listIterator;

		public ReversedIterable(ListIterator<E> listIterator) {
			this.listIterator = listIterator;
		}

		@Override
		public Iterator<E> iterator() {
			return new ReversedIterator<E>(listIterator);
		}
	}

	private static class ReversedIterator<E> implements Iterator<E> {
		public ListIterator<E> listIterator;

		public ReversedIterator(ListIterator<E> listIterator) {
			this.listIterator = listIterator;
		}

		@Override
		public boolean hasNext() {
			return listIterator.hasPrevious();
		}

		@Override
		public E next() {
			return listIterator.previous();
		}

		@Override
		public void remove() {
			listIterator.remove();
		}
	}
}
