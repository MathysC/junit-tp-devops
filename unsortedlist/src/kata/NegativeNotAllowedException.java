package kata;

public class NegativeNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegativeNotAllowedException(String message) {
		super(message);
	}

}
