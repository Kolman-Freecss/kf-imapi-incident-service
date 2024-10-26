package org.kolmanfreecss.kfimapiincidentservice.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Incident
 * Used to define the Incident object.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(nullable = false)
    String title;
    
    String description;
    
    @Enumerated(EnumType.STRING)
    Status status;
    
    @Enumerated(EnumType.STRING)
    Priority priority;
    
    @Column(name = "report_date")
    Date reportDate;
    
    @Column(name = "resolution_date")
    Date resolutionDate;
    
}
