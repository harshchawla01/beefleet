//package org.beefleet.service.impl;
//
//import org.beefleet.dto.DeliveryRequest;
//import org.beefleet.dto.DeliveryResponse;
//import org.beefleet.dto.DeliveryStatusResponse;
//import org.beefleet.service.VendorDeliveryService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FlipkartVendorDeliveryService implements VendorDeliveryService {
//    @Override
//    public DeliveryResponse scheduleDelivery(DeliveryRequest req) {
//        return null;
//    }
//
//    @Override
//    public DeliveryStatusResponse getDeliveryStatus(Long deliveryId) {
//        return null;
//    }
//
//    @Override
//    public DeliveryResponse retryFailedDelivery(Long deliveryId) {
//        return null;
//    }
//}

package org.beefleet.service.impl;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.dto.DeliveryStatusResponse;
import org.beefleet.model.Delivery;
import org.beefleet.model.DeliveryStatus;
import org.beefleet.repository.DeliveryRepository;
import org.beefleet.repository.VendorRepository;
import org.beefleet.service.VendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FlipkartVendorDeliveryService implements VendorDeliveryService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponse scheduleDelivery(DeliveryRequest req) {
        Delivery delivery = new Delivery();

        delivery.setVendorName(req.getVendorName());
        delivery.setCustomerName(req.getCustomerName());
        delivery.setPickupLocation(req.getPickupLocation());
        delivery.setDeliveryLocation(req.getDeliveryLocation());
        delivery.setPackageWeightKg(req.getPackageWeightKg());
        delivery.setScheduledAt(LocalDateTime.now());
        delivery.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(2)); // 48 hours delivery
        delivery.setStatus(DeliveryStatus.SCHEDULED);
        delivery.setMessage("Delivery scheduled successfully. Will be delivered in 48 hours.");

        delivery = deliveryRepository.save(delivery);

        DeliveryResponse response = new DeliveryResponse();
        response.setDeliveryId(delivery.getDeliveryId());
        response.setVendorName(delivery.getVendorName());
        response.setStatus(delivery.getStatus().toString());
        response.setScheduledAt(delivery.getScheduledAt());
        response.setEstimatedDeliveryTime(delivery.getEstimatedDeliveryTime());
        response.setMessage(delivery.getMessage());

        return response;
    }

    @Override
    public DeliveryStatusResponse getDeliveryStatus(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + deliveryId));

        DeliveryStatusResponse response = new DeliveryStatusResponse();
        response.setDeliveryId(delivery.getDeliveryId());
        response.setVendorName(delivery.getVendorName());
        response.setCustomerName(delivery.getCustomerName());
        response.setPickupLocation(delivery.getPickupLocation());
        response.setDeliveryLocation(delivery.getDeliveryLocation());
        response.setStatus(delivery.getStatus().toString());
        response.setScheduledAt(delivery.getScheduledAt());
        response.setEstimatedDeliveryTime(delivery.getEstimatedDeliveryTime());

        return response;
    }

    @Override
    public DeliveryResponse retryFailedDelivery(Long deliveryId) {
        return null;
    }
}
