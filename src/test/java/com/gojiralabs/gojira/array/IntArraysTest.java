package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntArraysTest {
	@Test
	public void testIndexOf1() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.indexOf(array, 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.indexOf(array, 4), is(-1));
	}

	@Test
	public void testContains1() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.contains(array, 2), is(true));
	}

	@Test
	public void testContains2() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.contains(array, 4), is(false));
	}

	@Test
	public void testAddIntegerArrayInteger() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.add(array, 4), is(new int[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddIntegerArrayIntegerInt1() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.add(array, 4, 1), is(new int[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddIntegerArrayIntegerInt2() {
		int[] array = new int[] { 0, 1, 2, 3 };
		IntArrays.add(array, 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddIntegerArrayIntegerInt3() {
		int[] array = new int[] { 0, 1, 2, 3 };
		IntArrays.add(array, 4, -1);
	}

	@Test
	public void testAddIntegerArrayIntegerArrayInt1() {
		int[] array1 = new int[] { 0, 1, 2, 3 };
		int[] array2 = new int[] { 4, 5, 6, 7 };
		assertThat(IntArrays.add(array1, array2, 4), is(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new int[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new int[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddIntegerArrayIntegerArrayInt2() {
		int[] array1 = new int[] { 0, 1, 2, 3 };
		int[] array2 = new int[] { 4, 5, 6, 7 };
		assertThat(IntArrays.add(array1, array2, 1), is(new int[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new int[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new int[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveIntegerArrayInt1() {
		int[] array = new int[] { 0, 1, 2, 3 };
		assertThat(IntArrays.remove(array, 1), is(new int[] { 0, 2, 3 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveIntegerArrayInt2() {
		int[] array = new int[] { 0, 1, 2, 3 };
		IntArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveIntegerArrayInt3() {
		int[] array = new int[] { 0, 1, 2, 3 };
		IntArrays.remove(array, -1);
	}

	@Test
	public void testRemoveIntegerArrayIntInt1() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		assertThat(IntArrays.remove(array, 1, 3), is(new int[] { 0, 4 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveIntegerArrayIntInt2() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		IntArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveIntegerArrayIntInt3() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		IntArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveIntegerArrayIntInt4() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		IntArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		assertThat(IntArrays.box(array), is(new Integer[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Integer[] array = new Integer[] { 0, 1, 2, 3, 4 };
		assertThat(IntArrays.unbox(array), is(new int[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Integer[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testDeepCopy() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		int[] actual = IntArrays.deepCopy(array);
		assertThat(actual, is(new int[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		int[] array = new int[] { 0, 1, 2, 3, 4 };
		assertThat(IntArrays.reverse(array), is(new int[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new int[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		int[] array1 = new int[] { 0, 1, 2, 3 };
		int[] array2 = new int[] { 4, 5, 6, 7 };
		assertThat(IntArrays.concat(array1, array2), is(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new int[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new int[] { 4, 5, 6, 7 }));
	}

}
