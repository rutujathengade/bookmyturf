package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}