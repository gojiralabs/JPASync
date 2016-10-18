package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ShortArraysTest {
	@Test
	public void testIndexOf1() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.indexOf(array, (short) 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.indexOf(array, (short) 4), is(-1));
	}

	@Test
	public void testContains1() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.contains(array, (short) 2), is(true));
	}

	@Test
	public void testContains2() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.contains(array, (short) 4), is(false));
	}

	@Test
	public void testAddShortArrayShort() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.add(array, (short) 4), is(new short[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddShortArrayShortInt1() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.add(array, (short) 4, 1), is(new short[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddShortArrayShortInt2() {
		short[] array = new short[] { 0, 1, 2, 3 };
		ShortArrays.add(array, (short) 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddShortArrayShortInt3() {
		short[] array = new short[] { 0, 1, 2, 3 };
		ShortArrays.add(array, (short) 4, -1);
	}

	@Test
	public void testAddShortArrayShortArrayInt1() {
		short[] array1 = new short[] { 0, 1, 2, 3 };
		short[] array2 = new short[] { 4, 5, 6, 7 };
		assertThat(ShortArrays.add(array1, array2, 4), is(new short[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new short[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new short[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddShortArrayShortArrayInt2() {
		short[] array1 = new short[] { 0, 1, 2, 3 };
		short[] array2 = new short[] { 4, 5, 6, 7 };
		assertThat(ShortArrays.add(array1, array2, 1), is(new short[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new short[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new short[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveShortArrayInt1() {
		short[] array = new short[] { 0, 1, 2, 3 };
		assertThat(ShortArrays.remove(array, 1), is(new short[] { 0, 2, 3 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveShortArrayInt2() {
		short[] array = new short[] { 0, 1, 2, 3 };
		ShortArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveShortArrayInt3() {
		short[] array = new short[] { 0, 1, 2, 3 };
		ShortArrays.remove(array, -1);
	}

	@Test
	public void testRemoveShortArrayIntInt1() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		assertThat(ShortArrays.remove(array, 1, 3), is(new short[] { 0, 4 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveShortArrayIntInt2() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		ShortArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveShortArrayIntInt3() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		ShortArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveShortArrayIntInt4() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		ShortArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		assertThat(ShortArrays.box(array), is(new Short[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Short[] array = new Short[] { 0, 1, 2, 3, 4 };
		assertThat(ShortArrays.unbox(array), is(new short[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Short[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testDeepCopy() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		short[] actual = ShortArrays.deepCopy(array);
		assertThat(actual, is(new short[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		short[] array = new short[] { 0, 1, 2, 3, 4 };
		assertThat(ShortArrays.reverse(array), is(new short[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new short[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		short[] array1 = new short[] { 0, 1, 2, 3 };
		short[] array2 = new short[] { 4, 5, 6, 7 };
		assertThat(ShortArrays.concat(array1, array2), is(new short[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new short[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new short[] { 4, 5, 6, 7 }));
	}
}
