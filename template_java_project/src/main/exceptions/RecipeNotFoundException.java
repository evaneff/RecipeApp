package main.exceptions;

public class RecipeNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 6113401487316922395L;

    public RecipeNotFoundException() { super(); }

    public RecipeNotFoundException(String message) { super(message); }

    public RecipeNotFoundException(Throwable cause) { super(cause); }

    public RecipeNotFoundException(String message, Throwable cause) { super(message, cause); }

}
