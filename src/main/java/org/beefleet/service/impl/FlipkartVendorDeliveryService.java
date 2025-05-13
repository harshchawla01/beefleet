package org.beefleet.service.impl;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.service.VendorDeliveryService;

public class FlipkartVendorDeliveryService implements VendorDeliveryService {
    @Override
    public DeliveryResponse scheduleDelivery(DeliveryRequest req) {
        return null;
    }

    @Override
    public DeliveryResponse retryFailedDelivery(Long deliveryId) {
        return null;
    }
}
