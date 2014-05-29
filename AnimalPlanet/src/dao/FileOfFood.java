package dao;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import entity.Food;
import entity.Herbivorou;
import entity.Plants;
import entity.Predator;


@XmlRootElement(name = "File_of_Food")
public class FileOfFood {

	
	final static Charset ENCODING = StandardCharsets.UTF_8;

	
	private Collection<Food> foods;
	

	@XmlElementWrapper(name = "Foods")
	@XmlElements({
		@XmlElement(name = "Herbivorou", type = Herbivorou.class),
		@XmlElement(name = "Predator", type = Predator.class),
		@XmlElement(name = "Plants", type = Plants.class)
		
	})
	public Collection<Food> getFoods() {
		return foods;
	}
	public void setFoods(Collection<Food> foods) {
		this.foods = foods;
	}
}
