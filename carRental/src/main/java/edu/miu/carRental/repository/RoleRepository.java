package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
