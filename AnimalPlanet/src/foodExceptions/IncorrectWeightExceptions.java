package foodExceptions;


import localisation.EngLocal;


public class IncorrectWeightExceptions extends AnimalIllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = EngLocal.DEFAULT_MESSAGE_INCORRECT_WEIGHT;
	
	
	public IncorrectWeightExceptions() { 
		
		this(defaultMessage);
	}
	
	
	public IncorrectWeightExceptions(String message) {
		
		super(message);
	}
}
