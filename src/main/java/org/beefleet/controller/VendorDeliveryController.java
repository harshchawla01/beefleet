package org.beefleet.controller;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.service.VendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorDeliveryController {

    private VendorDeliveryService vendorDeliveryService;

    @PostMapping("/delivery")
    public ResponseEntity<?> scheduleDelivery(@RequestBody DeliveryRequest req){
        return null;
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<?> getDeliveryStatus(Long deliveryId){
        return null;
    }

    @PostMapping("/retry-failed-delivery/{id}")
    public ResponseEntity<?> retryFailedDelivery(String deliveryId){
        return null;
    }

}
