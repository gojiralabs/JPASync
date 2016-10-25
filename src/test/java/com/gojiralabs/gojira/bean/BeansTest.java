package com.gojiralabs.gojira.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gojiralabs.gojira.test.Dummy;

public class BeansTest {

	@Test
	public void testGet1() {
		Dummy dummy = new Dummy();
		int intValue = 10;
		String stringValue = "test";
		boolean booleanValue = true;
		dummy.setIntProperty(intValue);
		dummy.setStringProperty(stringValue);
		dummy.setBooleanProperty(booleanValue);
		assertThat(Beans.get(dummy, "intProperty"), is(intValue));
		assertThat(Beans.get(dummy, "stringProperty"), is(stringValue));
		assertThat(Beans.get(dummy, "booleanProperty"), is(booleanValue));
	}

	@Test
	public void testGet2() {
		Dummy dummy = new Dummy();
		int intValue = 10;
		String stringValue = "test";
		boolean booleanValue = true;
		dummy.setParentIntProperty(intValue);
		dummy.setParentStringProperty(stringValue);
		dummy.setParentBooleanProperty(booleanValue);
		assertThat(Beans.get(dummy, "parentIntProperty"), is(intValue));
		assertThat(Beans.get(dummy, "parentStringProperty"), is(stringValue));
		assertThat(Beans.get(dummy, "parentBooleanProperty"), is(booleanValue));
	}

	@Test(expected = BeanException.class)
	public void testGet3() {
		Dummy dummy = new Dummy();
		Beans.get(dummy, "nonExistentProperty");
	}

	@Test(expected = BeanException.class)
	public void testGet4() {
		Dummy dummy = new Dummy();
		Beans.get(dummy, "intproperty");
	}

	@Test
	public void testSet1() {
		Dummy dummy = new Dummy();
		int intValue = 10;
		String stringValue = "test";
		boolean booleanValue = true;
		Beans.set(dummy, "intProperty", intValue);
		Beans.set(dummy, "stringProperty", stringValue);
		Beans.set(dummy, "booleanProperty", booleanValue);
		assertThat(dummy.getIntProperty(), is(intValue));
		assertThat(dummy.getStringProperty(), is(stringValue));
		assertThat(dummy.isBooleanProperty(), is(booleanValue));
	}

	@Test
	public void testSet2() {
		Dummy dummy = new Dummy();
		int intValue = 10;
		boolean booleanValue = true;
		String stringValue = "test";
		Beans.set(dummy, "parentIntProperty", intValue);
		Beans.set(dummy, "parentStringProperty", stringValue);
		Beans.set(dummy, "parentBooleanProperty", booleanValue);
		assertThat(dummy.getParentIntProperty(), is(intValue));
		assertThat(dummy.getParentStringProperty(), is(stringValue));
		assertThat(dummy.isParentBooleanProperty(), is(booleanValue));
	}

	@Test(expected = BeanException.class)
	public void testSet3() {
		Dummy dummy = new Dummy();
		Beans.set(dummy, "nonExistentProperty", null);
	}

	@Test(expected = BeanException.class)
	public void testSet4() {
		Dummy dummy = new Dummy();
		Beans.set(dummy, "intproperty", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSet5() {
		Dummy dummy = new Dummy();
		Beans.set(dummy, "intProperty", "StringValue");
	}

	@Test
	public void testGetNested() {
		Dummy dummy = new Dummy();
		String stringValue = "test";
		dummy.setDummyProperty(new Dummy());
		dummy.getDummyProperty().setDummyProperty(new Dummy());
		dummy.getDummyProperty().getDummyProperty().setStringProperty(stringValue);
		assertThat(Beans.getNested(dummy, "dummyProperty.dummyProperty.stringProperty"), is(stringValue));
	}

	@Test
	public void testSetNested() {

	}

	@Test
	public void testGetBean() {

	}
}
