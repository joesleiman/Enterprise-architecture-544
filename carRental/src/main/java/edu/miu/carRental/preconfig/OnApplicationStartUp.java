package edu.miu.carRental.preconfig;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import edu.miu.carRental.domain.Address;
import edu.miu.carRental.domain.Car;
import edu.miu.carRental.domain.Role;
import edu.miu.carRental.domain.User;
import edu.miu.carRental.repository.RoleRepository;
import edu.miu.carRental.repository.UserRepository;
import edu.miu.carRental.service.AddressService;
import edu.miu.carRental.service.CarService;
import edu.miu.carRental.service.UserService;

@Component
public class OnApplicationStartUp {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	CarService carService;

	@Autowired
	AddressService addressService;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<User> users = userRepo.findAll();
		Long count = users.stream().filter(userElm -> userElm.getUsername().equalsIgnoreCase("admin")).count();
		if (count > 0)
			return;
		fillRoleTable();
		createAdminUser();
		createUser();
		createData();
	}

	private void fillRoleTable() {
		List<Role> roles = roleRepo.findAll();
		if (roles == null || roles.isEmpty()) {
			roleRepo.save(new Role("ROLE_USER"));
			roleRepo.save(new Role("ROLE_ADMIN"));
		}
	}

	private void createAdminUser() {
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

	private void createUser() {
		User user = new User();
		user.setFirstName("employee");
		user.setLastName("employee");
		user.setEmail("employee@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(LocalDate.parse("1990-03-22"));
		user.setUsername("employee");
		user.setPassword("password");
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleRepo.findByName("ROLE_USER"));
		user.setRoles(roles);
		userService.save(user);
	}

	private void createData() {
		// Create 3 new Cars
		Car car1 = new Car();
		car1.setCarStatus("available");
		car1.setCarVinNumber("12VIN");
		car1.setCategoryName("Four Door");
		car1.setMake("Toyota");
		car1.setModel("Corolla");
		car1.setPlateNumber("12IA");
		car1.setPricePerDay(35.00);
		car1.setYear(2020);

		Car car2 = new Car();
		car2.setCarStatus("available");
		car2.setCarVinNumber("34VIN");
		car2.setCategoryName("Two Door");
		car2.setMake("BMW");
		car2.setModel("i8");
		car2.setPlateNumber("34IA");
		car2.setPricePerDay(70.00);
		car2.setYear(2020);

		Car car3 = new Car();
		car3.setCarStatus("available");
		car3.setCarVinNumber("56VIN");
		car3.setCategoryName("Two Door");
		car3.setMake("Jeep");
		car3.setModel("Wrangler");
		car3.setPlateNumber("56IA");
		car3.setPricePerDay(50.00);
		car3.setYear(2020);

		Car[] cars = { car1, car2, car3 };
		for (Car car : cars) {
			carService.save(car);
		}

		// Create 3 Addresses
		Address address1 = new Address("1000N 4th street", "Fairfield", "Iowa", "52557", "United states");
		Address address2 = new Address("1000N 5th street", "Ottumwa", "Iowa", "52555", "United states");
		Address address3 = new Address("1000N 6th street", "Iowa", "Iowa", "52556", "United states");

		Address[] addresses = { address1, address2, address3 };
		for (Address address : addresses) {
			addressService.save(address);
		}
	}

}