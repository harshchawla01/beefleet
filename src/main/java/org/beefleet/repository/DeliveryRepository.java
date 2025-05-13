package org.beefleet.repository;

import org.beefleet.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByCustomerName(String customerName);
}
