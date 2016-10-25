package com.gojiralabs.gojira.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class TreeIterator<T> implements Iterator<TreeNode<T>> {
	private final Deque<TreeNode<T>> queue = new ArrayDeque<>();

	public TreeIterator(TreeNode<T> root) {
		queue.push(root);
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override

	public TreeNode<T> next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		TreeNode<T> node = queue.pop();
		queueChildren(node, queue);
		return node;
	}

	protected abstract void queueChildren(TreeNode<T> node, Deque<TreeNode<T>> queue);
}
