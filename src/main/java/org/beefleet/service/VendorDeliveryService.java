package org.beefleet.service;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.springframework.stereotype.Service;

@Service
public interface VendorDeliveryService {
    // max delivery time
    // handle failures
    public DeliveryResponse scheduleDelivery(DeliveryRequest req);
    //retry rules
    public DeliveryResponse retryFailedDelivery(Long deliveryId);
}
