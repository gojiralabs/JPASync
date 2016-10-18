package com.gojiralabs.gojira.array;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ArrayGroupTest {

	@Test
	public void testGet1() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		assertThat(group.get(1), is(1));
	}

	@Test
	public void testGet2() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		assertThat(group.get(4), is(4));
	}

	@Test
	public void testGet3() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		Integer[] array3 = new Integer[] { 6, 7, 8 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2, array3);
		assertThat(group.get(7), is(7));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGet4() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		group.get(6);
	}

	@Test
	public void testSet1() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		group.set(1, 10);
		assertThat(group.get(1), is(10));
	}

	@Test
	public void testSet2() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		group.set(4, 10);
		assertThat(group.get(4), is(10));
	}

	@Test
	public void testSet3() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		Integer[] array3 = new Integer[] { 6, 7, 8 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2, array3);
		group.set(7, 10);
		assertThat(group.get(7), is(10));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSet4() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		group.set(6, 10);
	}

	@Test
	public void testLength() {
		Integer[] array1 = new Integer[] { 0, 1, 2 };
		Integer[] array2 = new Integer[] { 3, 4, 5 };
		ArrayGroup<Integer> group = new ArrayGroup<>(array1, array2);
		assertThat(group.length(), is(6));
	}
}
