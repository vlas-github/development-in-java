package boxesException;

public class InBoxNoPlaceExteption extends BoxesExteprion {

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "In the box there is no place";
	
	public InBoxNoPlaceExteption() {
		super(defaultMessage);
	}
	
	public InBoxNoPlaceExteption(String message) {
		super(message);
	}
}
