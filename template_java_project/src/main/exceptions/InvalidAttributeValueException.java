package main.exceptions;

public class InvalidAttributeValueException  extends InvalidAttributeException {
    private static final long serialVersionUID = 4198364556857234416L;

    public InvalidAttributeValueException() { super(); }

    public InvalidAttributeValueException(String message) { super(message); }

    public InvalidAttributeValueException(Throwable cause) { super(cause); }

    public InvalidAttributeValueException(String message, Throwable cause) { super(message, cause); }
}

