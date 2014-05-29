package entity;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Plants")
public class Plants extends Food {

	
	public Plants() {
		
		super();
	}
	
	
	public Plants(int id, double nutritionalValue, double weight) {
		
		super(id, nutritionalValue, weight);
	}
}
