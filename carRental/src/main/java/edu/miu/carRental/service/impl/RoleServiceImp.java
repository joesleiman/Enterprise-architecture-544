package edu.miu.carRental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Role;
import edu.miu.carRental.exception.RecordNotFoundException;
import edu.miu.carRental.repository.RoleRepository;
import edu.miu.carRental.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Role with id : " + id + " is not available"));
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role, Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Role with id : " + id + " is not available"));
		roleRepository.delete(role);
	}

}
