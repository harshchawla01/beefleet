package org.beefleet.dto;

import lombok.Data;

@Data
public class DeliveryRequest {
    private String vendorName;
    private String customerName;
    private String pickupLocation;
    private String deliveryLocation;
    private double packageWeightKg;
}
