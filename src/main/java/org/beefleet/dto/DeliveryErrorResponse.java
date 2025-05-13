package org.beefleet.dto;

import lombok.Data;

@Data
public class DeliveryErrorResponse {
    private String errorCode = "VENDOR_NOT_FOUND";
    private String message;
}
