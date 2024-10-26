package org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest.common;

import lombok.extern.slf4j.Slf4j;
import org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest.model.ResponseWrapper;
import org.kolmanfreecss.kfimapiincidentservice.shared.exceptions.KfException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

/**
 * Base controller class.
 * Java implementation.
 * 
 * @author Kolman-Freecss
 * @version 1.0
 */
@Slf4j
public abstract class BaseController<T> {
    
    protected <T> ResponseEntity<ResponseWrapper<T>> handleOperation(final Supplier<T> supplier,
                                                                     final HttpStatus status,
                                                                     final String successMessage) {
        T data = null;
        try {
            data = supplier.get();
            return ResponseEntity.status(status).body(new ResponseWrapper<>(successMessage, data));
        } catch (KfException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(e.getHttpStatus()).body(new ResponseWrapper<>(e.getApiMessage(), 
                    data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>("Server error", 
                    data));
        }
        
    }
    
}
