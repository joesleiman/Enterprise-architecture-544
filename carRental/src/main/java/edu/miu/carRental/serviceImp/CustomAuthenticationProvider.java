//package edu.miu.carRental.serviceImp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//	@Autowired
//	private  CarRentalUserDetailsService userDetailsService;
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		UserDetails user = userDetailsService.loadUserByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("user not found ");
//		}
//		if (!encoder().matches(password, user.getPassword())) {
//			throw new UsernameNotFoundException("password wrong  ");
//
//		}
//		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//
//		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//	}
//
//	private BCryptPasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
