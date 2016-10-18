package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ByteArraysTest {

	@Test
	public void testIndexOf1() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.indexOf(array, (byte) 2), is(2));
	}

	@Test
	public void testIndexOf2() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.indexOf(array, (byte) 4), is(-1));
	}

	@Test
	public void testContains1() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.contains(array, (byte) 2), is(true));
	}

	@Test
	public void testContains2() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.contains(array, (byte) 4), is(false));
	}

	@Test
	public void testAddByteArrayByte() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.add(array, (byte) 4), is(new byte[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3 }));
	}

	@Test
	public void testAddByteArrayByteInt1() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.add(array, (byte) 4, 1), is(new byte[] { 0, 4, 1, 2, 3 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddByteArrayByteInt2() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		ByteArrays.add(array, (byte) 4, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddByteArrayByteInt3() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		ByteArrays.add(array, (byte) 4, -1);
	}

	@Test
	public void testAddByteArrayByteArrayInt1() {
		byte[] array1 = new byte[] { 0, 1, 2, 3 };
		byte[] array2 = new byte[] { 4, 5, 6, 7 };
		assertThat(ByteArrays.add(array1, array2, 4), is(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new byte[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new byte[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testAddByteArrayByteArrayInt2() {
		byte[] array1 = new byte[] { 0, 1, 2, 3 };
		byte[] array2 = new byte[] { 4, 5, 6, 7 };
		assertThat(ByteArrays.add(array1, array2, 1), is(new byte[] { 0, 4, 5, 6, 7, 1, 2, 3 }));
		assertThat(array1, is(new byte[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new byte[] { 4, 5, 6, 7 }));
	}

	@Test
	public void testRemoveByteArrayInt1() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		assertThat(ByteArrays.remove(array, 1), is(new byte[] { 0, 2, 3 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveByteArrayInt2() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		ByteArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveByteArrayInt3() {
		byte[] array = new byte[] { 0, 1, 2, 3 };
		ByteArrays.remove(array, -1);
	}

	@Test
	public void testRemoveByteArrayIntInt1() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		assertThat(ByteArrays.remove(array, 1, 3), is(new byte[] { 0, 4 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3, 4 }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveByteArrayIntInt2() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		ByteArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveByteArrayIntInt3() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		ByteArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveByteArrayIntInt4() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		ByteArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		assertThat(ByteArrays.box(array), is(new Byte[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testUnbox() {
		Byte[] array = new Byte[] { 0, 1, 2, 3, 4 };
		assertThat(ByteArrays.unbox(array), is(new byte[] { 0, 1, 2, 3, 4 }));
		assertThat(array, is(new Byte[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testDeepCopy() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		byte[] actual = ByteArrays.deepCopy(array);
		assertThat(actual, is(new byte[] { 0, 1, 2, 3, 4 }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		byte[] array = new byte[] { 0, 1, 2, 3, 4 };
		assertThat(ByteArrays.reverse(array), is(new byte[] { 4, 3, 2, 1, 0 }));
		assertThat(array, is(new byte[] { 0, 1, 2, 3, 4 }));
	}

	@Test
	public void testConcat() {
		byte[] array1 = new byte[] { 0, 1, 2, 3 };
		byte[] array2 = new byte[] { 4, 5, 6, 7 };
		assertThat(ByteArrays.concat(array1, array2), is(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
		assertThat(array1, is(new byte[] { 0, 1, 2, 3 }));
		assertThat(array2, is(new byte[] { 4, 5, 6, 7 }));
	}
}
