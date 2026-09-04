package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}