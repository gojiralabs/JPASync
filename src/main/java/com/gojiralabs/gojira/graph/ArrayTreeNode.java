package com.gojiralabs.gojira.graph;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArrayTreeNode<T> implements TreeNode<T> {

	private final List<TreeNode<T>> children = new ArrayList<>();
	private TreeNode<T> parent;
	private T content;

	public ArrayTreeNode(TreeNode<T> parent, T content) {
		this.parent = parent;
		this.content = content;
	}

	public ArrayTreeNode(@Nullable T content) {
		this(null, content);
	}

	@Override
	public void setParent(@Nullable TreeNode<T> parent) {
		this.parent = parent;
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	@Override
	public TreeNode<T> addChild(@Nonnull TreeNode<T> child) {
		child.setParent(this);
		return child;
	}

	@Override
	public TreeNode<T> addChild(@Nullable T content) {
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
	public List<TreeNode<T>> getChildren() {
		return children;
	}

	@Override
	public TreeNode<T> getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		return getChildren().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof ArrayTreeNode && children.equals(((TreeNode<?>) obj).getChildren());
	}

	@Override
	public String toString() {
		return String.valueOf(content) + ":" + children;
	}
}
