package edu.miu.carRental.service;

import java.util.List;

import edu.miu.carRental.domain.Role;

public interface RoleService {
	public List<Role> findAll();

	public Role findById(Long id);
	
	public Role save(Role role);

	public Role update(Role role, Long id);
	
	public void delete(Long id);
}
