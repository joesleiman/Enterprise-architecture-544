package edu.miu.carRental.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.User;
import edu.miu.carRental.repository.UserRepository;
import edu.miu.carRental.security.model.JwtUserDetails;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}
		return new JwtUserDetails(user.getUserId(), user.getUsername(), user.getPassword(),
				user.getRoles().iterator().next().getName());
	}

}
