package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}