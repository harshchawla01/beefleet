package org.beefleet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {
    @PostMapping("/retry-failed-delivery/{id}")
    public ResponseEntity<?> retryFailedDelivery(String deliveryId){
        return null;
    }
}
