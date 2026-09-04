package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Turf;

public interface TurfRepository extends JpaRepository<Turf, Long> {

}