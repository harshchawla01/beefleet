package org.beefleet.controller;

import org.beefleet.dto.RetryResponse;
import org.beefleet.exception.VendorNotFoundException;
import org.beefleet.repository.DeliveryRepository;
import org.beefleet.service.VendorDeliveryService;
import org.beefleet.service.impl.AmazonVendorDeliveryService;
import org.beefleet.service.impl.FlipkartVendorDeliveryService;
import org.beefleet.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {
    @Autowired
    private AmazonVendorDeliveryService amazonVendorDeliveryService;

    @Autowired
    private FlipkartVendorDeliveryService flipkartVendorDeliveryService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("/retry-failed-delivery/{id}")
    public ResponseEntity<RetryResponse> retryFailedDelivery(@PathVariable("id") Long deliveryId) {
        String vendorName = deliveryRepository.findById(deliveryId).get().getVendorName();
        if(vendorName.equals("amazon")) {
            VendorDeliveryService amazonService = amazonVendorDeliveryService;
            RetryResponse response = amazonService.retryFailedDelivery(deliveryId);
            return ResponseEntity.ok(response);
        } else if(vendorName.equals("flipkart")) {
            VendorDeliveryService flipkartService = flipkartVendorDeliveryService;
            RetryResponse response = flipkartService.retryFailedDelivery(deliveryId);
            return ResponseEntity.ok(response);
        } else {
            throw new VendorNotFoundException("Vendor '" + vendorName + "' is not registered.");
        }
    }
}
