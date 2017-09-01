package java对象的生命周期;

public class Demo {

	private String title;

	public Demo(String title) {
		super();
		this.title = title;
		System.out.println(this.title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
