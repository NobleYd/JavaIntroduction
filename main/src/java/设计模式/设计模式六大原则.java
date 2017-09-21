package 设计模式;

/***
 * 下面是简单介绍，最下面有详细介绍。
 * 
 * 1 开闭原则(Open Close Principle) 对扩展开放，对修改关闭。
 * 
 * 2 里氏代换原则(Liskov Substitution Principle - LSP) 任何基类出现的地方，子类一定可以出现。
 * 
 * 3 依赖倒置原则(Dependence Inversion Principle)
 * 
 * 4 接口隔离原则(Interface Segregation Principle)
 * 
 * 5 迪米特原则(Demeter Principle)
 * 
 * 6 合成复用原则(Composite Reuse Principle)
 * 
 * @author nobleyd
 */
public class 设计模式六大原则 {
	/***
	 * 1 开闭原则(Open Close Principle) 对扩展开放，对修改关闭。
	 */
	public void 开闭原则(){
		
	}
	
	/***
	 * 2 里氏代换原则(Liskov Substitution Principle - LSP) 任何基类出现的地方，子类一定可以出现。
	 */
	public void 里氏代换原则(){
		/***
		 * 通俗的讲：子类可以扩展父类的功能，但不能改变父类原有的功能。
		 * 
		 * 含义：
		 * 
		 * 1 子类可以实现父类的抽象方法，但不可以覆盖父类的非抽象方法。
		 * 
		 * 2 子类可以增加自己的方法。
		 * 
		 * 3 当子类重载父类方法时候，方法的参数要比父类更宽松。
		 * 
		 * 4 当子类实现父类抽象方法的时候，方法的返回值要比父类更严格。
		 */
	}
	/***
	 * 3 依赖倒置原则(Dependence Inversion Principle)
	 */
	public void 依赖倒置原则(){
		/***
		 * 针对接口编程。依赖于抽象而不依赖于具体。
		 * 
		 * 这样可以减少类间的耦合性。
		 * 
		 */
	}
	/***
	 * 4 接口隔离原则(Interface Segregation Principle)
	 */
	public void 接口隔离原则(){
		/***
		 * 使用多个隔离的接口 比 使用一个接口 要好。
		 */
	}
	/***
	 * 5 迪米特原则(Demeter Principle)
	 */
	public void 迪米特原则(){
		
		/***
		 * 最小知道原则。public方法尽量少。
		 * 
		 */
		
	}
	/***
	 * 6 合成复用原则(Composite Reuse Principle)
	 */
	public void 合成复用原则(){
		/***
		 * 尽量使用聚合而不是继承。
		 * 
		 * 1 Is-a（继承）， Has-a。
		 * 
		 */
	}
	
}
