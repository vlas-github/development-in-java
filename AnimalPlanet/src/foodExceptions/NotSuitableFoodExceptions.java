package foodExceptions;


import localisation.EngLocal;


public class NotSuitableFoodExceptions extends AnimalIllegalArgumentException {

	
	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = EngLocal.DEFAULT_MESSAGE_NOT_SUITABLE_FOOD;
	
	
	public NotSuitableFoodExceptions() {
		
		this(defaultMessage);
	}
	
	
	public NotSuitableFoodExceptions(String message) {
		
		super(message);
	}
}
