package edu.miu.carRental.preconfig;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import edu.miu.carRental.domain.Role;
import edu.miu.carRental.domain.User;
import edu.miu.carRental.repository.RoleRepository;
import edu.miu.carRental.repository.UserRepository;
import edu.miu.carRental.service.UserService;

@Component
public class OnApplicationStartUp {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		fillRoleTable();
		createAdminUser();
	}

	private void createAdminUser() {
		List<User> users = userRepo.findAll();
		Long count = users.stream()
						  .filter(userElm -> userElm.getUsername().equalsIgnoreCase("admin"))
						  .count();
		if (count > 0) return;
		User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(LocalDate.parse("1990-03-22"));
		user.setUsername("admin");
		user.setPassword("admin");
		userService.addAdminUser(user);
	}

	private void fillRoleTable() {
		List<Role> roles = roleRepo.findAll();
		if (roles == null || roles.isEmpty()) {
			roleRepo.save(new Role("ROLE_USER"));
			roleRepo.save(new Role("ROLE_ADMIN"));
		}
	}

}