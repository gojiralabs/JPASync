package com.gojiralabs.gojira.test;

public class Dummy extends ParentDummy {
	private String stringProperty;
	private int intProperty;
	private boolean booleanProperty;
	private Dummy dummyProperty;
	private String readOnlyStringProperty;

	public String getStringProperty() {
		return stringProperty;
	}

	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}

	public int getIntProperty() {
		return intProperty;
	}

	public void setIntProperty(int intProperty) {
		this.intProperty = intProperty;
	}

	public boolean isBooleanProperty() {
		return booleanProperty;
	}

	public void setBooleanProperty(boolean booleanProperty) {
		this.booleanProperty = booleanProperty;
	}

	public Dummy getDummyProperty() {
		return dummyProperty;
	}

	public void setDummyProperty(Dummy dummyProperty) {
		this.dummyProperty = dummyProperty;
	}

	public String getReadOnlyStringProperty() {
		return readOnlyStringProperty;
	}

	public void setReadOnlyStringProperty(String readOnlyStringProperty) {
		this.readOnlyStringProperty = readOnlyStringProperty;
	}
}

class ParentDummy {
	private String parentStringProperty;
	private int parentIntProperty;
	private boolean parentBooleanProperty;
	private Dummy parentDummyProperty;
	private String parentReadOnlyStringProperty;

	public String getParentStringProperty() {
		return parentStringProperty;
	}

	public void setParentStringProperty(String parentStringProperty) {
		this.parentStringProperty = parentStringProperty;
	}

	public int getParentIntProperty() {
		return parentIntProperty;
	}

	public void setParentIntProperty(int parentIntProperty) {
		this.parentIntProperty = parentIntProperty;
	}

	public Dummy getParentDummyProperty() {
		return parentDummyProperty;
	}

	public boolean isParentBooleanProperty() {
		return parentBooleanProperty;
	}

	public void setParentBooleanProperty(boolean parentBooleanProperty) {
		this.parentBooleanProperty = parentBooleanProperty;
	}

	public void setParentDummyProperty(Dummy parentDummyProperty) {
		this.parentDummyProperty = parentDummyProperty;
	}

	public String getParentReadOnlyStringProperty() {
		return parentReadOnlyStringProperty;
	}
}
