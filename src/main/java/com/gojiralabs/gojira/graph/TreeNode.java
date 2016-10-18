package com.gojiralabs.gojira.graph;

import static com.gojiralabs.gojira.collections.Iterators.reversedIterable;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;

public interface TreeNode<T> extends Iterable<TreeNode<T>> {

	void setParent(TreeNode<T> parent);

	@Nonnull
	TreeNode<T> addChild(@Nonnull TreeNode<T> child);

	@Nonnull
	TreeNode<T> addChild(T content);

	void addChildrenNodes(@Nonnull Collection<? extends TreeNode<T>> children);

	void addChildrenContent(@Nonnull Collection<T> children);

	boolean removeChild(@Nonnull TreeNode<T> child);

	TreeNode<T> removeChild(int childIndex);

	T getContent();

	TreeNode<T> getParent();

	@Nonnull
	List<TreeNode<T>> getChildren();

	@SuppressWarnings("null")
	@Nonnull
	default Stream<TreeNode<T>> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	default TreeNode<T> searchNode(T nodeContent) {
		for (TreeNode<T> node : this) {
			if (Objects.equals(node.getContent(), nodeContent)) {
				return node;
			}
		}
		return null;
	}

	@Nonnull
	default Iterator<TreeNode<T>> depthFirstIterator() {
		return new TreeIterator<T>(this) {
			@Override
			protected void queueChildren(TreeNode<T> node, Deque<TreeNode<T>> queue) {
				reversedIterable(node.getChildren()).forEach(queue::push);
			}
		};
	}

	@Nonnull
	default Iterator<TreeNode<T>> breadthFirstIterator() {
		return new TreeIterator<T>(this) {
			@Override
			protected void queueChildren(TreeNode<T> node, Deque<TreeNode<T>> queue) {
				queue.addAll(node.getChildren());
			}
		};
	}

	@Override
	@Nonnull
	default Iterator<TreeNode<T>> iterator() {
		return breadthFirstIterator();
	}

	default boolean isRoot() {
		return getParent() == null;
	}

	default boolean isLeaf() {
		return getChildren().isEmpty();
	}

	default int getLevel() {
		if (isRoot()) {
			return 0;
		}
		return getParent().getLevel() + 1;
	}
}
