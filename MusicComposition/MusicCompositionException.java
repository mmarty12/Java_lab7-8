package MusicComposition;

/**
 * The {@code MusicCompositionException} class represents an exception specific to the MusicComposition class operations.
 * It extends the {@code RuntimeException} class.
 */
public class MusicCompositionException extends RuntimeException {
    /**
     * Constructs a new MusicCompositionException with the specified message.
     *
     * @param message the detail message (which is saved for later usage by the getMessage() method)
     */
    public MusicCompositionException(String message) {
        super(message);
    }
}
