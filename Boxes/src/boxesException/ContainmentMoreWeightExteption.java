package boxesException;

public class ContainmentMoreWeightExteption  extends BoxesExteprion {

	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Containment can not be more than the weight";
	
	public ContainmentMoreWeightExteption() {
		super(defaultMessage);
	}
	
	public ContainmentMoreWeightExteption(String message) {
		super(message);
	}
}
