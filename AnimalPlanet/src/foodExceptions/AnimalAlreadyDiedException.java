package foodExceptions;


import localisation.EngLocal;


public class AnimalAlreadyDiedException extends AnimalIllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = EngLocal.DEFAULT_MESSAGE_ANIMAL_ALREADY_DIED;
	
	
	public AnimalAlreadyDiedException() {
		
		this(defaultMessage);
	}
	
	
	public AnimalAlreadyDiedException(String message) {
		
		super(message);
	}
}
