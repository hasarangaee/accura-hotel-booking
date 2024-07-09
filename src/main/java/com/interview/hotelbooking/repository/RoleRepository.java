package com.interview.hotelbooking.repository;

import com.interview.hotelbooking.enums.ERole;
import com.interview.hotelbooking.model.masters.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
