package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FloatArraysTest {
	@Test
	public void testIndexOf1() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.indexOf(array, 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.indexOf(array, 4), is(-1));
	}

	@Test
	public void testContains1() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.contains(array, 2), is(true));
	}

	@Test
	public void testContains2() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.contains(array, 4), is(false));
	}

	@Test
	public void testAddFloatArrayFloat() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.add(array, 4), is(new float[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new float[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddFloatArrayFloatInt1() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.add(array, 4, 1), is(new float[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new float[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddFloatArrayFloatInt2() {
		float[] array = new float[] { 0, 1, 2, 3 };
		FloatArrays.add(array, 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddFloatArrayFloatInt3() {
		float[] array = new float[] { 0, 1, 2, 3 };
		FloatArrays.add(array, 4, -1);
	}

	@Test
	public void testAddFloatArrayFloatArrayInt1() {
		float[] array1 = new float[] { 0, 1, 2, 3 };
		float[] array2 = new float[] { 4, 5, 6, 7 };
		assertThat(FloatArrays.add(array1, array2, 4), is(new float[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new float[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new float[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddFloatArrayFloatArrayInt2() {
		float[] array1 = new float[] { 0, 1, 2, 3 };
		float[] array2 = new float[] { 4, 5, 6, 7 };
		assertThat(FloatArrays.add(array1, array2, 1), is(new float[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new float[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new float[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveFloatArrayInt1() {
		float[] array = new float[] { 0, 1, 2, 3 };
		assertThat(FloatArrays.remove(array, 1), is(new float[] { 0, 2, 3 }));
		assertThat(array, is(new float[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveFloatArrayInt2() {
		float[] array = new float[] { 0, 1, 2, 3 };
		FloatArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveFloatArrayInt3() {
		float[] array = new float[] { 0, 1, 2, 3 };
		FloatArrays.remove(array, -1);
	}

	@Test
	public void testRemoveFloatArrayIntInt1() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		assertThat(FloatArrays.remove(array, 1, 3), is(new float[] { 0, 4 }));
		assertThat(array, is(new float[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveFloatArrayIntInt2() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		FloatArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveFloatArrayIntInt3() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		FloatArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveFloatArrayIntInt4() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		FloatArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		assertThat(FloatArrays.box(array), is(new Float[] { 0f, 1f, 2f, 3f, 4f }));
		assertThat(array, is(new float[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Float[] array = new Float[] { 0f, 1f, 2f, 3f, 4f };
		assertThat(FloatArrays.unbox(array), is(new float[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Float[] { 0f, 1f, 2f, 3f, 4f }));
	}

	@Test
	public void testDeepCopy() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		float[] actual = FloatArrays.deepCopy(array);
		assertThat(actual, is(new float[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		float[] array = new float[] { 0, 1, 2, 3, 4 };
		assertThat(FloatArrays.reverse(array), is(new float[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new float[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		float[] array1 = new float[] { 0, 1, 2, 3 };
		float[] array2 = new float[] { 4, 5, 6, 7 };
		assertThat(FloatArrays.concat(array1, array2), is(new float[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new float[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new float[] { 4, 5, 6, 7 }));
	}

}
