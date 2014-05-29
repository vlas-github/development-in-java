package foodExceptions;


public abstract class AnimalIllegalArgumentException extends IllegalArgumentException {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String message = "";	
	
	
	public AnimalIllegalArgumentException(String message) {
		
		this.message = message;
	}

	
	public String toString() {
		
		return this.getClass().getSimpleName() + "(" + message + ")";
	}
}
