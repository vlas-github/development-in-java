package boxesException;

public class BoxIsEmptyExteption extends BoxesExteprion {

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Box is empty";
	
	public BoxIsEmptyExteption() {
		super(defaultMessage);
	}
	
	public BoxIsEmptyExteption(String message) {
		super(message);
	}
}
