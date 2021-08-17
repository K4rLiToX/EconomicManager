package Excepciones;

@SuppressWarnings("serial")
public class EmptyInfoException extends Exception {
	public EmptyInfoException() {
		super();
	}
	
	public EmptyInfoException(String msg) {
		super(msg);
	}
}
