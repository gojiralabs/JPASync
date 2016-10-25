package com.gojiralabs.gojira.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CharArraysTest {

	@Test
	public void testIndexOf1() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.indexOf(array, 'c'), is(2));
	}

	@Test
	public void testIndexOf2() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.indexOf(array, 'e'), is(-1));
	}

	@Test
	public void testContains1() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.contains(array, 'c'), is(true));
	}

	@Test
	public void testContains2() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.contains(array, 'e'), is(false));
	}

	@Test
	public void testAddCharacterArrayCharacter() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.add(array, 'e'), is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd' }));
	}

	@Test
	public void testAddCharacterArrayCharacterInt1() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.add(array, 'e', 1), is(new char[] { 'a', 'e', 'b', 'c', 'd' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd' }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddCharacterArrayCharacterInt2() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		CharArrays.add(array, 'e', 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testAddCharacterArrayCharacterInt3() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		CharArrays.add(array, 'e', -1);
	}

	@Test
	public void testAddCharacterArrayCharacterArrayInt1() {
		char[] array1 = new char[] { 'a', 'b', 'c', 'd' };
		char[] array2 = new char[] { 'e', 'f', 'g', 'h' };
		assertThat(CharArrays.add(array1, array2, 4), is(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' }));
		assertThat(array1, is(new char[] { 'a', 'b', 'c', 'd' }));
		assertThat(array2, is(new char[] { 'e', 'f', 'g', 'h' }));
	}

	@Test
	public void testAddCharacterArrayCharacterArrayInt2() {
		char[] array1 = new char[] { 'a', 'b', 'c', 'd' };
		char[] array2 = new char[] { 'e', 'f', 'g', 'h' };
		assertThat(CharArrays.add(array1, array2, 1), is(new char[] { 'a', 'e', 'f', 'g', 'h', 'b', 'c', 'd' }));
		assertThat(array1, is(new char[] { 'a', 'b', 'c', 'd' }));
		assertThat(array2, is(new char[] { 'e', 'f', 'g', 'h' }));
	}

	@Test
	public void testRemoveCharacterArrayInt1() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		assertThat(CharArrays.remove(array, 1), is(new char[] { 'a', 'c', 'd' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd' }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveCharacterArrayInt2() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		CharArrays.remove(array, 5);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveCharacterArrayInt3() {
		char[] array = new char[] { 'a', 'b', 'c', 'd' };
		CharArrays.remove(array, -'b');
	}

	@Test
	public void testRemoveCharacterArrayIntInt1() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		assertThat(CharArrays.remove(array, 1, 3), is(new char[] { 'a', 'e' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveCharacterArrayIntInt2() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		CharArrays.remove(array, -1, 3);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testRemoveCharacterArrayIntInt3() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		CharArrays.remove(array, 1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveCharacterArrayIntInt4() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		CharArrays.remove(array, 2, 1);
	}

	@Test
	public void testBox() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		assertThat(CharArrays.box(array), is(new Character[] { 'a', 'b', 'c', 'd', 'e' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
	}

	@Test
	public void testUnbox() {
		Character[] array = new Character[] { 'a', 'b', 'c', 'd', 'e' };
		assertThat(CharArrays.unbox(array), is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
		assertThat(array, is(new Character[] { 'a', 'b', 'c', 'd', 'e' }));
	}

	@Test
	public void testDeepCopy() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		char[] actual = CharArrays.deepCopy(array);
		assertThat(actual, is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
		assertNotSame(array, actual);
	}

	@Test
	public void testReverse() {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		assertThat(CharArrays.reverse(array), is(new char[] { 'e', 'd', 'c', 'b', 'a' }));
		assertThat(array, is(new char[] { 'a', 'b', 'c', 'd', 'e' }));
	}

	@Test
	public void testConcat() {
		char[] array1 = new char[] { 'a', 'b', 'c', 'd' };
		char[] array2 = new char[] { 'e', 'f', 'g', 'h' };
		assertThat(CharArrays.concat(array1, array2), is(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' }));
		assertThat(array1, is(new char[] { 'a', 'b', 'c', 'd' }));
		assertThat(array2, is(new char[] { 'e', 'f', 'g', 'h' }));
	}
}
