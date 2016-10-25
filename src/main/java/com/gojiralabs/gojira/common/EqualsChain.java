package com.gojiralabs.gojira.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EqualsChain {

	private final List<ComparingPair> pairs = new ArrayList<>();

	public static EqualsChain start() {
		return new EqualsChain();
	}

	public EqualsChain comparing(Object left, Object right) {
		pairs.add(new ComparingPair(left, right));
		return this;
	}

	public boolean isEqual() {
		return !pairs.stream().anyMatch(p -> !p.isEqual());
	}

	private static class ComparingPair {
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
