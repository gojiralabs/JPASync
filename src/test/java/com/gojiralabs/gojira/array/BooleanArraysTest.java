package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BooleanArraysTest {

	@Test
	public void testIndexOf1() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.indexOf(array, true), is(0));
	}

	@Test
	public void testIndexOf2() {
		boolean[] array = new boolean[] { true, true, true, true };
		assertThat(BooleanArrays.indexOf(array, false), is(-1));
	}

	@Test
	public void testContains1() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.contains(array, true), is(true));
	}

	@Test
	public void testContains2() {
		boolean[] array = new boolean[] { true, true, true, true };
		assertThat(BooleanArrays.contains(array, false), is(false));
	}

	@Test
	public void testAddBooleanArrayBoolean() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.add(array, true), is(new boolean[] { true, false, true, false, true }));
		assertThat(array, is(new boolean[] { true, false, true, false }));
	}

	@Test
	public void testAddBooleanArrayBooleanInt1() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.add(array, false, 1), is(new boolean[] { true, false, false, true, false }));
		assertThat(array, is(new boolean[] { true, false, true, false }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddBooleanArrayBooleanInt2() {
		boolean[] array = new boolean[] { true, false, true, false };
		BooleanArrays.add(array, true, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddBooleanArrayBooleanInt3() {
		boolean[] array = new boolean[] { true, false, true, false };
		BooleanArrays.add(array, true, -1);
	}

	@Test
	public void testAddBooleanArrayBooleanArrayInt1() {
		boolean[] array1 = new boolean[] { true, false, true, false };
		boolean[] array2 = new boolean[] { false, true, false, true };
		assertThat(BooleanArrays.add(array1, array2, 4), is(new boolean[] { true, false, true, false, false, true, false, true }));
		assertThat(array1, is(new boolean[] { true, false, true, false }));
		assertThat(array2, is(new boolean[] { false, true, false, true }));
	}

	@Test
	public void testAddBooleanArrayBooleanArrayInt2() {
		boolean[] array1 = new boolean[] { true, false, true, false };
		boolean[] array2 = new boolean[] { false, true, false, true };
		assertThat(BooleanArrays.add(array1, array2, 1), is(new boolean[] { true, false, true, false, true, false, true, false }));
		assertThat(array1, is(new boolean[] { true, false, true, false }));
		assertThat(array2, is(new boolean[] { false, true, false, true }));
	}

	@Test
	public void testRemoveBooleanArrayInt1() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.remove(array, 1), is(new boolean[] { true, true, false }));
		assertThat(array, is(new boolean[] { true, false, true, false }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveBooleanArrayInt2() {
		boolean[] array = new boolean[] { true, false, true, false };
		BooleanArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveBooleanArrayInt3() {
		boolean[] array = new boolean[] { true, false, true, false };
		BooleanArrays.remove(array, -1);
	}

	@Test
	public void testRemoveBooleanArrayIntInt1() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		assertThat(BooleanArrays.remove(array, 1, 3), is(new boolean[] { true, true }));
		assertThat(array, is(new boolean[] { true, false, true, false, true }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveBooleanArrayIntInt2() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		BooleanArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveBooleanArrayIntInt3() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		BooleanArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveBooleanArrayIntInt4() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		BooleanArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		assertThat(BooleanArrays.box(array), is(new Boolean[] { true, false, true, false, true }));
		assertThat(array, is(new boolean[] { true, false, true, false, true }));
	}

	@Test
	public void testUnbox() {
		Boolean[] array = new Boolean[] { true, false, true, false, true };
		assertThat(BooleanArrays.unbox(array), is(new boolean[] { true, false, true, false, true }));
		assertThat(array, is(new Boolean[] { true, false, true, false, true }));
	}

	@Test
	public void testDeepCopy() {
		boolean[] array = new boolean[] { true, false, true, false, true };
		boolean[] actual = BooleanArrays.deepCopy(array);
		assertThat(actual, is(new boolean[] { true, false, true, false, true }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		boolean[] array = new boolean[] { true, false, true, false };
		assertThat(BooleanArrays.reverse(array), is(new boolean[] { false, true, false, true }));
		assertThat(array, is(new boolean[] { true, false, true, false }));
	}

	@Test
	public void testConcat() {
		boolean[] array1 = new boolean[] { true, false, true, false };
		boolean[] array2 = new boolean[] { false, true, false, true };
		assertThat(BooleanArrays.concat(array1, array2), is(new boolean[] { true, false, true, false, false, true, false, true }));
		assertThat(array1, is(new boolean[] { true, false, true, false }));
		assertThat(array2, is(new boolean[] { false, true, false, true }));
	}
}
