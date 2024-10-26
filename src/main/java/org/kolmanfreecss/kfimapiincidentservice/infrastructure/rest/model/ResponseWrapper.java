package org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest.model;

/**
 * Wrapper class for a response.
 *
 * @author Kolman-Freecss
 * @version 1.0
 */
public record ResponseWrapper<T>(
        String message,
        T dtoData
) {
    public ResponseWrapper(final T dtoData, final String message) {
        this(message, dtoData);
    }
}
