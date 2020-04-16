package edu.miu.carRental.preconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import edu.miu.carRental.domain.Role;
import edu.miu.carRental.domain.User;
import edu.miu.carRental.repository.RoleRepository;
import edu.miu.carRental.service.UserService;

@Component
public class OnApplicationStartUp {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepo;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		fillRoleTable();
		createAdminUser();
	}

	private void createAdminUser() {
		List<User> users = userService.findAll();
		Long count = users.stream()
						  .filter(userElm -> userElm.getUsername().equalsIgnoreCase("admin"))
						  .count();
		if (count > 0) return;
		User user = new User();
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