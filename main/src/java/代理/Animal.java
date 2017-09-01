package 代理;

public class Animal implements IAnimal {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String eat() {
		System.out.println("Animal.eat()");
		return "good";
	}

}
