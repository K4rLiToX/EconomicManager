package Excepciones;

@SuppressWarnings("serial")
public class AlreadyInUseException extends Exception {

	public AlreadyInUseException() {
		super();
	}
	
	public AlreadyInUseException(String msg) {
		super(msg);
	}
}
