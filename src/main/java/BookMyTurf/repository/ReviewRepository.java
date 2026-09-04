package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}