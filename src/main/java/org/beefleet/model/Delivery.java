package org.beefleet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    private String vendorName;
    private String customerName;
    private String pickupLocation;
    private String deliveryLocation;
    private double packageWeightKg;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    private LocalDateTime scheduledAt;
    private LocalDateTime estimatedDeliveryTime;
    private String message;
}
