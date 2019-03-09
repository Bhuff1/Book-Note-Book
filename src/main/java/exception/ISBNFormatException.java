package exception;

public class ISBNFormatException extends Exception{

	private static final long serialVersionUID = 1L;

	public ISBNFormatException() {
		super("Error: ISBN  must be 10 or 13 numbers long.");
	}
}

