package org.beefleet.dto;

import lombok.Data;

@Data
public class DeliveryStatusErrorResponse {
    private String errorCode = "ACCESS_DENIED";
    private String message;
}
