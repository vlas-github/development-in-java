package ru.test;

import ru.classes.MyClass;

public class Main {
	
	public static void main(String [] arg) {
		
		System.out.println("Hello!");
		
		MyClass myClass = new MyClass();
		
		System.out.println("a = " + myClass.getA() + " b = " + myClass.getB());
	}
}
