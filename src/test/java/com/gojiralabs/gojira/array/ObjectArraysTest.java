package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ObjectArraysTest {

	@Test
	public void testIndexOf1() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.indexOf(array, "c"), is(2));
	}

	@Test
	public void testIndexOf2() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.indexOf(array, "e"), is(-1));
	}

	@Test
	public void testContains1() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.contains(array, "c"), is(true));
	}

	@Test
	public void testContains2() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.contains(array, "e"), is(false));
	}

	@Test
	public void testAddTArrayT() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.add(array, "e"), is(new String[] { "a", "b", "c", "d", "e" }));
	}

	@Test
	public void testAddTArrayTInt1() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.add(array, "e", 2), is(new String[] { "a", "b", "e", "c", "d" }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddTArrayTInt2() {
		String[] array = new String[] { "a", "b", "c", "d" };
		ObjectArrays.add(array, "e", -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddTArrayTInt3() {
		String[] array = new String[] { "a", "b", "c", "d" };
		ObjectArrays.add(array, "e", 5);
	}

	@Test
	public void testAddTArrayTArrayInt1() {
		String[] array1 = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "e", "f", "g", "h" };
		assertThat(ObjectArrays.add(array1, array2, 2), is(new String[] { "a", "b", "e", "f", "g", "h", "c", "d" }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddTArrayTArrayInt2() {
		String[] array1 = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "e", "f", "g", "h" };
		ObjectArrays.add(array1, array2, -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddTArrayTArrayInt3() {
		String[] array1 = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "e", "f", "g", "h" };
		ObjectArrays.add(array1, array2, 5);
	}

	@Test
	public void testRemoveTArrayInt1() {
		String[] array = new String[] { "a", "b", "c", "d" };
		assertThat(ObjectArrays.remove(array, 2), is(new String[] { "a", "b", "d" }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveTArrayInt2() {
		String[] array = new String[] { "a", "b", "c", "d" };
		ObjectArrays.remove(array, -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveTArrayInt3() {
		String[] array = new String[] { "a", "b", "c", "d" };
		ObjectArrays.remove(array, 5);
	}

	@Test
	public void testRemoveTArrayIntInt1() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		assertThat(ObjectArrays.remove(array, 1, 3), is(new String[] { "a", "e" }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveTArrayIntInt2() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		ObjectArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveTArrayIntInt3() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		ObjectArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTArrayIntInt4() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		ObjectArrays.remove(array, 2, 1);
	}

	@Test
	public void testDeepCopy() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		String[] actual = ObjectArrays.deepCopy(array);
		assertThat(actual, is(new String[] { "a", "b", "c", "d", "e" }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		String[] array = new String[] { "a", "b", "c", "d", "e" };
		assertThat(ObjectArrays.reverse(array), is(new String[] { "e", "d", "c", "b", "a" }));
		assertThat(array, is(new String[] { "a", "b", "c", "d", "e" }));
	}

	@Test
	public void testConcat() {
		String[] array1 = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "e", "f", "g", "h" };
		assertThat(ObjectArrays.concat(array1, array2), is(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }));
	}
}
