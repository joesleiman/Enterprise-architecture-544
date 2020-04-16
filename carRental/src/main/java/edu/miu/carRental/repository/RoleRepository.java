package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.carRental.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
