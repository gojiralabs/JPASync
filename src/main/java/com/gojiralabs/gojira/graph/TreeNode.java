package com.gojiralabs.gojira.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Objects;

public interface TreeNode<T> extends Iterable<TreeNode<T>> {

	void setParent(@Nullable TreeNode<T> parent);

	TreeNode<T> addChild(@Nonnull TreeNode<T> child);

	TreeNode<T> addChild(@Nullable T content);

	void addChildrenNodes(@Nonnull Collection<? extends TreeNode<T>> children);

	void addChildrenContent(@Nonnull Collection<T> children);

	boolean removeChild(@Nonnull TreeNode<T> child);

	TreeNode<T> removeChild(int childIndex);

	T getContent();

	TreeNode<T> getParent();

	List<TreeNode<T>> getChildren();

	default TreeNode<T> searchNode(@Nullable T nodeContent) {
		for (TreeNode<T> node : this) {
			if (Objects.equal(node.getContent(), nodeContent)) {
				return node;
			}
		}
		return null;
	}

	default Iterator<TreeNode<T>> depthFirstIterator() {
		return new DepthFirstIterator<>(this);
	}

	default Iterator<TreeNode<T>> breadthFirstIterator() {
		return new BreadthFirstIterator<>(this);
	}

	@Override
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
