package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}