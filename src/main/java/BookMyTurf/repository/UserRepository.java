package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}