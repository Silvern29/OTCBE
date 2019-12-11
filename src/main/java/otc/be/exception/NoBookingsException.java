package otc.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No bookings for user")
public class NoBookingsException extends RuntimeException {
    public NoBookingsException(final String message) {
        super(message);
    }
}
