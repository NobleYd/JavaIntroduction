package 反射.bean;

public class ParentObject {

	public String parentPublicField;
	private String parentPrivateField;

	public static void parentStaticMethod() {
		System.out.println("ParentObject.parentStaticMethod()");
	}

	public void parentMethod() {
		System.out.println("ParentObject.parentMethod()");
	}

}
