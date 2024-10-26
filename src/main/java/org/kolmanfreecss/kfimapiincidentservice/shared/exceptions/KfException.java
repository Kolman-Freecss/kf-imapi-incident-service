package org.kolmanfreecss.kfimapiincidentservice.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * KfException class.
 * Used to define the methods that the KfException must implement.
 * 
 * @author Kolman-Freecss
 * @version 1.0
 */
@Getter
public class KfException extends RuntimeException {
    
    final HttpStatus httpStatus;
    
    final String apiMessage;
    
    public KfException(final String message, 
                       final Throwable cause) {
        super(message, cause);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        apiMessage = "Server error";
    }
    
    public KfException(final String message, 
                       final Throwable cause,
                       final HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
        // TODO: Implement a dict of standard messages
        apiMessage = message;
    }
    
}
