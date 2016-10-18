package com.gojiralabs.gojira.graph;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

public class ArrayTreeNode<T> implements TreeNode<T> {
	@Nonnull
	private final List<TreeNode<T>> children = new ArrayList<>();
	private TreeNode<T> parent;
	private T content;

	public ArrayTreeNode(TreeNode<T> parent, T content) {
		this.parent = parent;
		this.content = content;
	}

	public ArrayTreeNode(T content) {
		this(null, content);
	}

	@Override
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	@Override
	@Nonnull
	public TreeNode<T> addChild(@Nonnull TreeNode<T> child) {
		child.setParent(this);
		return child;
	}

	@Override
	@Nonnull
	public TreeNode<T> addChild(T content) {
		return addChild(new ArrayTreeNode<>(content));
	}

	@Override
	public boolean removeChild(@Nonnull TreeNode<T> child) {
		return children.remove(requireNonNull(child));
	}

	@Override
	public TreeNode<T> removeChild(int childIndex) {
		return children.remove(childIndex);
	}

	@Override
	public void addChildrenNodes(@Nonnull Collection<? extends TreeNode<T>> children) {
		children.forEach(this::addChild);
	}

	@Override
	public void addChildrenContent(@Nonnull Collection<T> children) {
		children.forEach(this::addChild);
	}

	@Override
	public T getContent() {
		return content;
	}

	@Override
	@Nonnull
	public List<TreeNode<T>> getChildren() {
		return children;
	}

	@Override
	public TreeNode<T> getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj.getClass().equals(getClass()) && Objects.equals(content, ((TreeNode<T>) obj).getContent());
	}

	@Override
	public String toString() {
		return String.valueOf(content) + ":" + children;
	}
}
