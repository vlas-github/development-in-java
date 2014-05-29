package boxesException;

public class ItemIsInBox extends BoxesExteprion {

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Item has been placed in a box";
	
	public ItemIsInBox() {
		super(defaultMessage);
	}
	
	public ItemIsInBox(String message) {
		super(message);
	}
}
