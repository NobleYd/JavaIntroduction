package 反射.bean;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

	protected List<String> stringList = new ArrayList<String>();

	public List<String> getStringList() {
		return this.stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

}