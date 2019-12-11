package otc.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing user")
public class NoUserException extends RuntimeException {
    public NoUserException(final String message) {
        super(message);
    }
}
