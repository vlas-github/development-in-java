package testing;

import boxesException.BoxesExteprion;
import boxesException.ContainmentMoreWeightExteption;
import boxesException.InBoxNoPlaceExteption;
import boxesException.ItemIsInBox;
import boxesLogic.Box;
import boxesLogic.Item;

public class Main {

	private static Box generateBox() throws ItemIsInBox, InBoxNoPlaceExteption, ContainmentMoreWeightExteption {
		
		Box firstBox = new Box(1, "First Box", 100, 99);
		firstBox.put(new Item(1, "First Item", 20));
		firstBox.put(new Item(2, "Second Item", 30));
		firstBox.put(new Item(3, "Third Item", 3));
		
		Box secondBox = new Box(2, "Second Box", 200, 190);
		secondBox.put(new Item(4, "Fourth Item", 50));
		secondBox.put(new Item(5, "Fifth Item", 30));
		secondBox.put(new Item(6, "Sixth Item", 100));
		
		Box thirdBox = new Box(3, "Third Box", 800, 790);
		thirdBox.put(firstBox);
		thirdBox.put(secondBox);
		thirdBox.put(new Item(7, "Seventh Item", 100));
		thirdBox.put(new Item(8, "Eight Item", 100));
		thirdBox.put(new Item(9, "Ninth Item", 50));
		thirdBox.put(new Item(10, "Tenth Item", 30));
		
		return thirdBox;
	}
	
	public static void main(String [] arg) {
		
		Box box = null;
		
		System.out.println(">Initialization box...");
		
		try {
			
			box = generateBox();
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
		
		System.out.println(">This box...");
		System.out.println(box);
		
		System.out.println(">Getting items out of the box and adding items in a box that is already in it...");
		
		try {
			
			Item item = box.popRandom();
			box.put(item);
			box.put(item);
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
		
		System.out.println(">This box now...");
		System.out.println(box);
		
		System.out.println(">Boxed large item...");
		
		try {
			
			box.put(new Item(1, "Large Item", box.getWeight() * 2));
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
		
		System.out.println(">This box now...");
		System.out.println(box);
		
		System.out.println(">Retrieving an item from an empty box...");
		
		try {
			
			Box emptyBox = new Box(1, "Empty box", 100, 10);
			emptyBox.popRandom();
			
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
		
		
		System.out.println(">Creation a box with containment more weight...");
		
		try {
			
			new Box(1, "Incorrect box", 100, 1000);
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
		
		System.out.println(">Adding one item into two diferent boxes...");
		
		try {
			
			Box firstBox = new Box(1, "Incorrect box", 100, 99);
			Box secondBox = new Box(1, "Incorrect box", 100, 99);
			
			Item item = new Item(1, "Some Item", 10);
			
			firstBox.put(item);
			secondBox.put(item);
		} catch (BoxesExteprion ex) {
			
			System.out.println(ex.getMessage());
		}
	}
}
