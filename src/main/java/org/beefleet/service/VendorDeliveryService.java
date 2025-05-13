package org.beefleet.service;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.dto.DeliveryStatusResponse;
import org.springframework.stereotype.Service;

public interface VendorDeliveryService {
    // max delivery time
    // handle failures
    public DeliveryResponse scheduleDelivery(DeliveryRequest req);
    public DeliveryStatusResponse getDeliveryStatus(Long deliveryId);
    //retry rules
    public DeliveryResponse retryFailedDelivery(Long deliveryId);
}
