package main.exceptions;

public class InvalidAttributeException  extends RuntimeException {
    private static final long serialVersionUID = -4633935125430064089L;

    public InvalidAttributeException() { super(); }

    public InvalidAttributeException(String message) { super(message); }

    public InvalidAttributeException(Throwable cause) { super(cause); }

    public InvalidAttributeException(String message, Throwable cause) { super(message, cause); }
}
