package otc.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "No bookings for user")
public class NoBookingsException extends Throwable {
    public NoBookingsException(final String message) {
        super(message);
    }
}
