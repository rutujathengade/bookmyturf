package BookMyTurf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}