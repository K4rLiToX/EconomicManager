package Excepciones;

@SuppressWarnings("serial")
public class NonExistingUserException extends Exception {
	
	public NonExistingUserException() {
		super();
	}
	
	public NonExistingUserException(String msg) {
		super(msg);
	}
}
