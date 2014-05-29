package boxesLogic;

public class Item {
	
	private int id;
	private String name;
	private double weight;
	private boolean isPutted;
	
	public Item(int id, String name, double weight) {

		this.id 		= id;
		this.name 		= name;
		this.weight 	= weight;
		this.isPutted	= false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public boolean isPutted() {
		return isPutted;
	}
	protected void setPutted(boolean isPutted) {
		this.isPutted = isPutted;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", weight=" + weight + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Item other = (Item) obj;
		
		if (id != other.id)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		
		return true;
	}
}
