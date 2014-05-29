package entity;


import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import localisation.EngLocal;
import foodExceptions.IncorrectWeightExceptions;


@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class Food {
	
	
	private int id;
	private double nutritionalValue;
	private double weight;
	
	
	public Food() { 	}
	
	
	public Food(int id, double nutritionalValue, double weight) {

		setId(id);
		setNutritionalValue(nutritionalValue);
		setWeight(weight);
	}
	
	
	@XmlElement(name = "id")
	public int getId() {
		
		return id;
	}
	public void setId(int id) {
		
		this.id = id;
	}
	
	
	@XmlElement(name = "nutritional_value")
	public double getNutritionalValue() {
		
		return nutritionalValue;
	}
	public void setNutritionalValue(double nutritionalValue) {
		
		if (nutritionalValue < 0 || nutritionalValue > 1)
			throw new IncorrectWeightExceptions(EngLocal.EXTEPTION_INCORRECT_NUTRITIONAL_VALUE);
		
		this.nutritionalValue = nutritionalValue;
	}
	
	
	@XmlElement(name = "weight")
	public double getWeight() {
		
		return weight;
	}
	public void setWeight(double weight) {
		
		if (nutritionalValue < 0 || nutritionalValue > 1)
			throw new IncorrectWeightExceptions(EngLocal.EXTEPTION_INCORRECT_WEIGHT);
		
		this.weight = weight;
	}
	
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		DecimalFormat df = new DecimalFormat("#0.000");
		
		sb.append(this.getClass().getSimpleName());
		sb.append(" {");
		sb.append(this.getId() + "; ");
		sb.append(df.format(this.getNutritionalValue()) + "; ");
		sb.append(df.format(this.getWeight()));
		sb.append("}");
		
		sb.toString().replace('.', ',');
		
		return sb.toString();
	}
}
