package org.beefleet.dto;

import lombok.Data;

@Data
public class DeliveryRequest {
    private String vendorName;
    private String customerName;
    private String pickupLocation;
    private String deliveryLocation;
    private double packageWeightKg;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public double getPackageWeightKg() {
        return packageWeightKg;
    }

    public void setPackageWeightKg(double packageWeightKg) {
        this.packageWeightKg = packageWeightKg;
    }
}
