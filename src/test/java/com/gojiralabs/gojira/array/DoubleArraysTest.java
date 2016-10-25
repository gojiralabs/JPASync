package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DoubleArraysTest {
	@Test
	public void testIndexOf1() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.indexOf(array, 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.indexOf(array, 4), is(-1));
	}

	@Test
	public void testContains1() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.contains(array, 2), is(true));
	}

	@Test
	public void testContains2() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.contains(array, 4), is(false));
	}

	@Test
	public void testAddDoubleArrayDouble() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.add(array, 4), is(new double[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new double[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddDoubleArrayDoubleInt1() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.add(array, 4, 1), is(new double[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new double[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddDoubleArrayDoubleInt2() {
		double[] array = new double[] { 0, 1, 2, 3 };
		DoubleArrays.add(array, 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddDoubleArrayDoubleInt3() {
		double[] array = new double[] { 0, 1, 2, 3 };
		DoubleArrays.add(array, 4, -1);
	}

	@Test
	public void testAddDoubleArrayDoubleArrayInt1() {
		double[] array1 = new double[] { 0, 1, 2, 3 };
		double[] array2 = new double[] { 4, 5, 6, 7 };
		assertThat(DoubleArrays.add(array1, array2, 4), is(new double[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new double[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new double[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddDoubleArrayDoubleArrayInt2() {
		double[] array1 = new double[] { 0, 1, 2, 3 };
		double[] array2 = new double[] { 4, 5, 6, 7 };
		assertThat(DoubleArrays.add(array1, array2, 1), is(new double[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new double[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new double[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveDoubleArrayInt1() {
		double[] array = new double[] { 0, 1, 2, 3 };
		assertThat(DoubleArrays.remove(array, 1), is(new double[] { 0, 2, 3 }));
		assertThat(array, is(new double[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveDoubleArrayInt2() {
		double[] array = new double[] { 0, 1, 2, 3 };
		DoubleArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveDoubleArrayInt3() {
		double[] array = new double[] { 0, 1, 2, 3 };
		DoubleArrays.remove(array, -1);
	}

	@Test
	public void testRemoveDoubleArrayIntInt1() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		assertThat(DoubleArrays.remove(array, 1, 3), is(new double[] { 0, 4 }));
		assertThat(array, is(new double[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveDoubleArrayIntInt2() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		DoubleArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveDoubleArrayIntInt3() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		DoubleArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveDoubleArrayIntInt4() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		DoubleArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		assertThat(DoubleArrays.box(array), is(new Double[] { 0d, 1d, 2d, 3d, 4d }));
		assertThat(array, is(new double[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Double[] array = new Double[] { 0d, 1d, 2d, 3d, 4d };
		assertThat(DoubleArrays.unbox(array), is(new double[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Double[] { 0d, 1d, 2d, 3d, 4d }));
	}

	@Test
	public void testDeepCopy() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		double[] actual = DoubleArrays.deepCopy(array);
		assertThat(actual, is(new double[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		double[] array = new double[] { 0, 1, 2, 3, 4 };
		assertThat(DoubleArrays.reverse(array), is(new double[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new double[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		double[] array1 = new double[] { 0, 1, 2, 3 };
		double[] array2 = new double[] { 4, 5, 6, 7 };
		assertThat(DoubleArrays.concat(array1, array2), is(new double[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new double[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new double[] { 4, 5, 6, 7 }));
	}
}
