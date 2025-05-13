package org.beefleet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryResponse {
    private Long deliveryId;
    private String vendorName;
    private String status;
    private LocalDateTime scheduledAt;
    private LocalDateTime estimatedDeliveryTime;
    private String message;
}
