package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {

}