package com.gojiralabs.gojira.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BreadthFirstIterator<T> implements Iterator<TreeNode<T>> {
	private final Deque<TreeNode<T>> queue = new ArrayDeque<>();

	public BreadthFirstIterator(TreeNode<T> root) {
		queue.push(root);
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public TreeNode<T> next() {
		TreeNode<T> node = queue.pop();
		queue.addAll(node.getChildren());
		return node;
	}
}
