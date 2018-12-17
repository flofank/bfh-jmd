package ch.bfh.fankf4.jmd.appservices.booking;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
