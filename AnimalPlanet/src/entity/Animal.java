package entity;


import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;

import localisation.EngLocal;
import foodExceptions.AnimalAlreadyDiedException;
import foodExceptions.AnimalIllegalArgumentException;


@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class Animal extends Food {

	
	private boolean isAlive;
	
	
	public Animal() {
		
		super();
	}
	
	
	public Animal(int id, boolean isAlive, double nutritionalValue, double weight) {
		
		super(id, nutritionalValue, weight);
		setAlive(isAlive);
	}
	
	
	@XmlElement(name = "is_alive")
	public boolean isAlive() {
		
		return isAlive;
	}	
	public void setAlive(boolean isAlive) {
		
		this.isAlive = isAlive;
	}
	
	
	public void die() throws AnimalIllegalArgumentException {
		
		if (!isAlive)
			throw new AnimalAlreadyDiedException();
			
		isAlive = false;
	}
	
	
	public void eat(Food eat) throws AnimalIllegalArgumentException {
		
		if (!this.isAlive())
			throw new AnimalAlreadyDiedException(EngLocal.EXTEPTION_THIS_ANIMAL_HAS_DIED);
	}
	
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		DecimalFormat df = new DecimalFormat("#0.000");
		
		sb.append(this.getClass().getSimpleName());
		sb.append(" {");
		sb.append(this.getId() + "; ");
		sb.append((this.isAlive() ? "1" : "0") + "; ");
		sb.append(df.format(this.getNutritionalValue()) + "; ");
		sb.append(df.format(this.getWeight()));
		sb.append("}");
		
		sb.toString().replace('.', ',');
		
		return sb.toString();
	}
}
