package boxesException;

public abstract class BoxesExteprion extends Exception{

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Boxes Exteption";
	
	public BoxesExteprion() {
		super(defaultMessage);
	}
	
	public BoxesExteprion(String message) {
		super(message);
	}
}
