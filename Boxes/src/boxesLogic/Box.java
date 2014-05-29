package boxesLogic;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import boxesException.BoxIsEmptyExteption;
import boxesException.ContainmentMoreWeightExteption;
import boxesException.InBoxNoPlaceExteption;
import boxesException.ItemIsInBox;


public class Box extends Item {
	
	private static Random randomGenerator = new Random(Calendar.getInstance().getTimeInMillis());

	private List<Item> items;	
	private double containment;	
	private double remainingSpace;
	
	public Box(int id, String name, double weight, double containment) throws ContainmentMoreWeightExteption {
		
		super(id, name, weight);
		
		if (containment > weight)
			throw new ContainmentMoreWeightExteption();
		
		this.containment 	= containment;	
		remainingSpace 		= containment;
		items 				= new LinkedList<Item>();
	}

	public double getRemainingSpace() {
		return remainingSpace;
	}

	public double getContainment() {
		return containment;
	}
	public void setContainment(double containment) throws ContainmentMoreWeightExteption {
		
		if (containment > getWeight())
			throw new ContainmentMoreWeightExteption();
		
		this.containment = containment;
	}

	public boolean contains(Item item) {
		
		boolean itemIsFound = false;
		
		for(Item i : items) {
			
			if (item.equals(i))
				itemIsFound =  true;
			
			if (i instanceof Box && ((Box)i).contains(item))
				itemIsFound = true;
		}
		
		return itemIsFound;
	}
	
	public void put(Item item) throws ItemIsInBox, InBoxNoPlaceExteption {
		
		if (remainingSpace < item.getWeight())
			throw new InBoxNoPlaceExteption();
		
		if (contains(item) || item.isPutted())
			throw new ItemIsInBox();
		
		item.setPutted(true);
		remainingSpace -= item.getWeight();
		items.add(item);
	}
	
	public Item popRandom() throws BoxIsEmptyExteption {
		
		if (items.isEmpty())
			throw new BoxIsEmptyExteption();
		
		int indexRandomItem = randomGenerator.nextInt(items.size());
		
		Item result = items.get(indexRandomItem);
		result.setPutted(false);
		
		remainingSpace += result.getWeight();
		items.remove(result);
		
		return result;
	}
	
	public void clear() {
		
		items.clear();
	}
	
	private String helpToString(int leavelTab) {
				
		StringBuffer tab = new StringBuffer();
		
		for(int i = 0; i < leavelTab; i++)
			tab.append("\t");
		
		StringBuffer message = new StringBuffer();
		
		message.append(tab.toString() + "Box [id=" + getId() + ", name=" + getName() + 
				", weight=" + getWeight() + ", containment=" + containment + ", remaining space="+ remainingSpace + "]");
		message.append("\n");
		
		tab.append("\t");
		
		for (Item item : items) {
			
			if (item instanceof Box) {
				
				message.append(((Box) item).helpToString(leavelTab + 1));
			} else {
				message.append(tab.toString() + item.toString());
				message.append("\n");
			}
		}

		return message.toString();
	}

	@Override
	public String toString() {
		
		return helpToString(0);
	}
}
