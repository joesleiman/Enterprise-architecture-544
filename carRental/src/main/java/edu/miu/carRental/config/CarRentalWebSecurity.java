package edu.miu.carRental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import edu.miu.carRental.repository.UserRepository;
import edu.miu.carRental.serviceImp.CarRentalUserDetailsService;
import edu.miu.carRental.serviceImp.CustomAuthenticationProvider;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@CrossOrigin(allowedHeaders = "*")
public class CarRentalWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private CarRentalUserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    //web.ignoring().antMatchers("/employee/nbookings");
	    //web.ignoring().antMatchers("/employee/nbookings/*");
	    //web.ignoring().antMatchers("/employee/edit_booking");
	    //web.ignoring().antMatchers("/employee/bookings/search/*");
	    /*web.ignoring().antMatchers("/booking");
	    web.ignoring().antMatchers("/booking/*");
	    web.ignoring().antMatchers("/search_booking/*");
	    web.ignoring().antMatchers("/cancel_booking/*");
	    web.ignoring().antMatchers("/check_availiable_cars");*/
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(authenticationProvider)
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder()) ;
	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
		.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "*/public/*").permitAll()
			.antMatchers("/employee/**").hasRole("EMPLOYEE")
			.antMatchers("/admin/**","/employee/**").hasRole("ADMIN")
			.anyRequest().authenticated()//such request must be authenticated
			//.anyRequest().permitAll()	
		.and()
			.httpBasic();
	}

	
	public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
