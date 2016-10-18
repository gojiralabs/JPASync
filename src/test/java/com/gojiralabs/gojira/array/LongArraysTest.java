package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LongArraysTest {
	@Test
	public void testIndexOf1() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.indexOf(array, (long) 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.indexOf(array, (long) 4), is(-1));
	}

	@Test
	public void testContains1() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.contains(array, (long) 2), is(true));
	}

	@Test
	public void testContains2() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.contains(array, (long) 4), is(false));
	}

	@Test
	public void testAddLongArrayLong() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.add(array, (long) 4), is(new long[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new long[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddLongArrayLongInt1() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.add(array, (long) 4, 1), is(new long[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new long[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddLongArrayLongInt2() {
		long[] array = new long[] { 0, 1, 2, 3 };
		LongArrays.add(array, (long) 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddLongArrayLongInt3() {
		long[] array = new long[] { 0, 1, 2, 3 };
		LongArrays.add(array, (long) 4, -1);
	}

	@Test
	public void testAddLongArrayLongArrayInt1() {
		long[] array1 = new long[] { 0, 1, 2, 3 };
		long[] array2 = new long[] { 4, 5, 6, 7 };
		assertThat(LongArrays.add(array1, array2, 4), is(new long[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new long[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new long[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddLongArrayLongArrayInt2() {
		long[] array1 = new long[] { 0, 1, 2, 3 };
		long[] array2 = new long[] { 4, 5, 6, 7 };
		assertThat(LongArrays.add(array1, array2, 1), is(new long[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new long[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new long[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveLongArrayInt1() {
		long[] array = new long[] { 0, 1, 2, 3 };
		assertThat(LongArrays.remove(array, 1), is(new long[] { 0, 2, 3 }));
		assertThat(array, is(new long[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveLongArrayInt2() {
		long[] array = new long[] { 0, 1, 2, 3 };
		LongArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveLongArrayInt3() {
		long[] array = new long[] { 0, 1, 2, 3 };
		LongArrays.remove(array, -1);
	}

	@Test
	public void testRemoveLongArrayIntInt1() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		assertThat(LongArrays.remove(array, 1, 3), is(new long[] { 0, 4 }));
		assertThat(array, is(new long[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveLongArrayIntInt2() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		LongArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveLongArrayIntInt3() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		LongArrays.remove(array, 1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveLongArrayIntInt4() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		LongArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		assertThat(LongArrays.box(array), is(new Long[] { 0l, 1l, 2l, 3l, 4l }));
		assertThat(array, is(new long[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Long[] array = new Long[] { 0l, 1l, 2l, 3l, 4l };
		assertThat(LongArrays.unbox(array), is(new long[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Long[] { 0l, 1l, 2l, 3l, 4l }));
	}

	@Test
	public void testDeepCopy() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		long[] actual = LongArrays.deepCopy(array);
		assertThat(actual, is(new long[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		long[] array = new long[] { 0, 1, 2, 3, 4 };
		assertThat(LongArrays.reverse(array), is(new long[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new long[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		long[] array1 = new long[] { 0, 1, 2, 3 };
		long[] array2 = new long[] { 4, 5, 6, 7 };
		assertThat(LongArrays.concat(array1, array2), is(new long[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new long[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new long[] { 4, 5, 6, 7 }));
	}

}
