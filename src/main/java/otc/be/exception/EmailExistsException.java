package otc.be.exception;

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }
}
