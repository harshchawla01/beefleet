package org.beefleet.exception;

public class VendorNotFoundException extends RuntimeException {
  public VendorNotFoundException(String message) {
    super(message);
  }
}
