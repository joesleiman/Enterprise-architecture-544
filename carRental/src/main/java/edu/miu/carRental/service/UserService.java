package edu.miu.carRental.service;


import java.util.List;

import edu.miu.carRental.domain.User;

public interface UserService {
	
	public List<User> findAll();

	public User findById(Long id);
	
	public User save(User user);

	public User update(User user, Long id);
	
	public void delete(Long id);

	
}
