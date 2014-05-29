package ru.classes;

public class MyClass {

	private int a;
	private double b;
	
	public MyClass(int a, double b) {
		
		this.a = a;
		this.b = b;
	}
	public MyClass() {
		
		this(10, 20.0);
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
}
