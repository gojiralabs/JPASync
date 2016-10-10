package com.gojiralabs.gojira.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

public class EqualsChain {

	private final List<ComparingPair> pairs = new ArrayList<>();

	public static EqualsChain start() {
		return new EqualsChain();
	}

	public EqualsChain comparing(@Nullable Object left, @Nullable Object right) {
		pairs.add(new ComparingPair(left, right));
		return this;
	}

	public boolean isEqual() {
		return !pairs.stream().anyMatch(p -> !p.isEqual());
	}

	private class ComparingPair {
		private Object left;
		private Object right;

		private ComparingPair(Object left, Object right) {
			this.left = left;
			this.right = right;
		}

		private boolean isEqual() {
			return Objects.equals(left, right);
		}
	}
}