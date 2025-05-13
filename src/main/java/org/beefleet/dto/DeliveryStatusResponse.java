package org.beefleet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryStatusResponse {
    private Long deliveryId;
    private String vendorName;
    private String customerName;
    private String pickupLocation;
    private String deliveryLocation;
    private String status;
    private LocalDateTime scheduledAt;
    private LocalDateTime estimatedDeliveryTime;
}
