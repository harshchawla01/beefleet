package org.beefleet.controller;

import org.beefleet.dto.DeliveryRequest;
import org.beefleet.dto.DeliveryResponse;
import org.beefleet.dto.DeliveryStatusResponse;
import org.beefleet.service.VendorDeliveryService;
import org.beefleet.service.impl.AmazonVendorDeliveryService;
import org.beefleet.service.impl.FlipkartVendorDeliveryService;
import org.beefleet.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
@PreAuthorize("hasRole('user')")
public class VendorDeliveryController {

    @Autowired
    private AmazonVendorDeliveryService amazonVendorDeliveryService;

    @Autowired
    private FlipkartVendorDeliveryService flipkartVendorDeliveryService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/delivery")
    public ResponseEntity<?> scheduleDelivery(@RequestHeader("Authorization") String jwt, @RequestBody DeliveryRequest req){
        String username = jwtUtil.extractUsernameFromJwtString(jwt);
        VendorDeliveryService vendorDeliveryService;

        if(username.equals("amazon")) {
            vendorDeliveryService = amazonVendorDeliveryService;
        }
        else if(username.equals("flipkart")) {
            vendorDeliveryService = flipkartVendorDeliveryService;
        }
        else {
            return new ResponseEntity<>("Invalid vendor", HttpStatus.BAD_REQUEST);
        }

        DeliveryResponse res = vendorDeliveryService.scheduleDelivery(req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<?> getDeliveryStatus(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long deliveryId){
        String username = jwtUtil.extractUsernameFromJwtString(jwt);
        VendorDeliveryService vendorDeliveryService;

        if(username.equals("amazon")) {
            vendorDeliveryService = amazonVendorDeliveryService;
        }
        else if(username.equals("flipkart")) {
            vendorDeliveryService = flipkartVendorDeliveryService;
        }
        else {
            return new ResponseEntity<>("Invalid vendor", HttpStatus.BAD_REQUEST);
        }

        DeliveryStatusResponse res = vendorDeliveryService.getDeliveryStatus(deliveryId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}