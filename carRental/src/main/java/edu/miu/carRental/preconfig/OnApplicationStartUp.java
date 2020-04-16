package edu.miu.carRental.preconfig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepo;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Start Of onApplicationEvent");
		fillRoleTable();
//		createAdminUser();
		logger.info("End Of onApplicationEvent");

	}

	/**
	 * @throws StudentAlreadyExistException
	 * 
	 */
	private void createAdminUser() {

		User user = new User();
		user.setUsername("mosad");
		user.setPassword("admin123");
		userService.addAdminUser(user);
	}

	/**
	 * 
	 */
	private void fillRoleTable() {
		logger.info("Start Of fillRoleTable");

		List<Role> roles = roleRepo.findAll();

		if (roles == null || roles.isEmpty()) {
			logger.debug("No Roles In a table ");
			roleRepo.save(new Role("ROLE_USER"));
			logger.debug("Role .. ROLE_USER inserted");
			roleRepo.save(new Role("ROLE_ADMIN"));
			logger.debug("Role .. ROLE_ADMIN inserted");

		}
		logger.info("End Of fillRoleTable");

	}

}