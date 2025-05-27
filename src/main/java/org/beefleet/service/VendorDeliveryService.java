package org.beefleet.service;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.dto.DeliveryStatusResponse;
import org.beefleet.dto.RetryResponse;
import org.beefleet.model.Delivery;
import org.beefleet.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface VendorDeliveryService {
    public DeliveryResponse scheduleDelivery(DeliveryRequest req);
    public DeliveryStatusResponse getDeliveryStatus(Long deliveryId, String userName);
    //retry rules
    public RetryResponse retryFailedDelivery(Long deliveryId);
}
