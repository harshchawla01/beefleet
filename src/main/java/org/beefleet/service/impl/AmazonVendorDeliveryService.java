package org.beefleet.service.impl;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.service.VendorDeliveryService;

public class AmazonVendorDeliveryService implements VendorDeliveryService {
    @Override
    public DeliveryResponse scheduleDelivery(DeliveryRequest req) {
        String vendorName = req.getVendorName();
        return null;
    }

    @Override
    public DeliveryResponse retryFailedDelivery(Long deliveryId) {
        return null;
    }
}
