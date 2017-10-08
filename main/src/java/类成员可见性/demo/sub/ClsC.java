package 类成员可见性.demo.sub;

import 类成员可见性.demo.ClsA;

public class ClsC {

	public static void main(String[] args) {

		ClsA clsA = new ClsA();
		// clsA.mA1();// The method mA1() from the type ClsA is not visible

	}

}
