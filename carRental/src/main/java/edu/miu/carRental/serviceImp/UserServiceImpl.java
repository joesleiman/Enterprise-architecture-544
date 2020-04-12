package edu.miu.carRental.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.config.CarRentalWebSecurity;
import edu.miu.carRental.domain.User;
import edu.miu.carRental.exceptions.RecordNotFoundException;
import edu.miu.carRental.repository.UserRepository;
import edu.miu.carRental.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CarRentalWebSecurity passwordEncoderfun;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,CarRentalWebSecurity passwordEncoderfun ) {
		// TODO Auto-generated constructor stub
		this.userRepository=userRepository;
		this.passwordEncoderfun=passwordEncoderfun;
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with id : " + id+" is not available"));
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		String pass=user.getPassword();
		user.setPassword(passwordEncoderfun.passwordEncoder().encode(pass));
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("User with id : " + id+" is not available"));
		userRepository.delete(user);
	}
	@Override
	public User update(User user, Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id)
                .map(userToUpdate -> {
                	userToUpdate.setDateOfBirth(user.getDateOfBirth());
                	userToUpdate.setEmail(user.getEmail());
                	userToUpdate.setUsername(user.getUsername());
                	userToUpdate.setFirstName(user.getFirstName());
                	userToUpdate.setLastName(user.getLastName());
                	userToUpdate.setPhoneNumber(user.getPhoneNumber());
                	userToUpdate.setRoles(user.getRoles());
                	userToUpdate.setPassword(passwordEncoderfun.passwordEncoder().encode(user.getPassword()));
                    return userRepository.save(userToUpdate);
                }).orElseGet(() -> {
                    return userRepository.save(user);
                });
	}

}
