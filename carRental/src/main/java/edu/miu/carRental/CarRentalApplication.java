package edu.miu.carRental;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import edu.miu.carRental.domain.Address;
import edu.miu.carRental.domain.Car;
import edu.miu.carRental.service.AddressService;
import edu.miu.carRental.service.CarService;
import edu.miu.carRental.service.RoleService;
import edu.miu.carRental.service.UserService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CarRentalApplication implements CommandLineRunner {
	@Autowired
	CarService carService;

	@Autowired
	AddressService addressService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("edu.miu.carRental")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("CarRental API", "Implementation of CarRental API April 2020", "TOGETHER TEAM",
				"Terms of service", null, "", "www.miu.edu", Collections.emptyList());
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello carRental app: ");
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
		for (Car c : cars) {
			saveCar(c);
		}
		// --------------------------------------------
		// Create 3 Addresses
		Address address1 = new Address("1000N 4th street", "Fairfield", "Iowa", "52557", "United states");
		Address address2 = new Address("1000N 5th street", "Ottumwa", "Iowa", "52555", "United states");
		Address address3 = new Address("1000N 6th street", "Iowa", "Iowa", "52556", "United states");
		Address[] addresses = { address1, address2, address3 };
		for (Address a : addresses) {
			saveAddress(a);
		}
	}

	public void saveCar(Car car) {
		carService.save(car);
	}

	public void saveAddress(Address address) {
		addressService.save(address);
	}

}
