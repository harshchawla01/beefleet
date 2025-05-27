package org.beefleet.service.impl;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.dto.DeliveryStatusResponse;
import org.beefleet.dto.RetryResponse;
import org.beefleet.exception.DeliveryNotFoundException;
import org.beefleet.exception.UnauthorizedAccessException;
import org.beefleet.model.Delivery;
import org.beefleet.model.DeliveryStatus;
import org.beefleet.repository.DeliveryRepository;
import org.beefleet.repository.VendorRepository;
import org.beefleet.service.VendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AmazonVendorDeliveryService implements VendorDeliveryService {

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
        delivery.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(1)); // 24 hours delivery
        delivery.setStatus(DeliveryStatus.FAILED);
        delivery.setMessage("Will deliver within a day");

        // Save delivery
        delivery = deliveryRepository.save(delivery);

        // Create response
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
    public DeliveryStatusResponse getDeliveryStatus(Long deliveryId, String username) {
        // First check if delivery exists
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DeliveryNotFoundException("No delivery found with given ID"));

        // Then check authorization - if username doesn't match userId, throw unauthorized
        if (!delivery.getVendorName().equals(username)) {
            throw new UnauthorizedAccessException("You are not authorized to view this delivery.");
        }

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
    public RetryResponse retryFailedDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DeliveryNotFoundException("No delivery found with given ID"));

        if (delivery.getStatus() != DeliveryStatus.FAILED) {
            throw new RuntimeException("Only failed deliveries can be retried");
        }

        if (delivery.getRetryCount() >= 3) {
            delivery.setStatus(DeliveryStatus.CANCELLED);
            delivery.setMessage("Maximum retry attempts reached. Delivery cancelled.");
        } else {
            delivery.setRetryCount(delivery.getRetryCount() + 1);
//            delivery.setStatus(DeliveryStatus.RETRY_INITIATED);
            delivery.setStatus(DeliveryStatus.FAILED);
            delivery.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(1));
            delivery.setMessage("Retry successfully triggered.");
        }

        delivery = deliveryRepository.save(delivery);

        RetryResponse response = new RetryResponse();
        response.setDeliveryId(delivery.getDeliveryId());
        response.setStatus(delivery.getStatus().toString());
        response.setVendorName(delivery.getVendorName());
        response.setMessage(delivery.getMessage());

        return response;
    }
}