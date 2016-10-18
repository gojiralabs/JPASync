package com.gojiralabs.gojira.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;

public class Iterators {

	private Iterators() {
		// private constructor to avoid instantiation
	}

	@SuppressWarnings("null")
	@Nonnull
	public static <T> Iterator<T> reversedIterator(@Nonnull List<T> list) {
		return reversedIterable(list).iterator();
	}

	@SuppressWarnings("null")
	@Nonnull
	public static <T> Iterator<T> reversedIterator(@Nonnull ListIterator<T> listIterator) {
		return reversedIterable(listIterator).iterator();
	}

	@SuppressWarnings("null")
	@Nonnull
	public static <T> Iterable<T> reversedIterable(@Nonnull List<T> list) {
		return reversedIterable(list.listIterator(list.size()));
	}

	@Nonnull
	public static <T> Iterable<T> reversedIterable(@Nonnull ListIterator<T> listIterator) {
		return new ReversedIterable<>(listIterator);
	}

	private static class ReversedIterable<E> implements Iterable<E> {
		@Nonnull
		private ListIterator<E> listIterator;

		public ReversedIterable(@Nonnull ListIterator<E> listIterator) {
			this.listIterator = listIterator;
		}

		@Override
		@Nonnull
		public Iterator<E> iterator() {
			return new ReversedIterator<>(listIterator);
		}
	}

	private static class ReversedIterator<E> implements Iterator<E> {
		@Nonnull
		private ListIterator<E> listIterator;

		public ReversedIterator(@Nonnull ListIterator<E> listIterator) {
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
