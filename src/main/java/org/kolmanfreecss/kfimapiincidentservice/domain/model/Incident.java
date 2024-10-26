package org.kolmanfreecss.kfimapiincidentservice.domain.model;

/**
 * Incident
 * Used to define the Incident object.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
public class Incident {
    
    public enum Status {
        REPORTED,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }
    
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
    
}
