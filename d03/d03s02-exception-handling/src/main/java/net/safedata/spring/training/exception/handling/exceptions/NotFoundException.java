package net.safedata.spring.training.exception.handling.exceptions;

/**
 * A runtime exception thrown when an entity was not found
 *
 * @author bogdan.solga
 */
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public NotFoundException(final String message) {
        super(message);
    }
}
