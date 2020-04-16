package edu.miu.carRental.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.carRental.domain.User;
import edu.miu.carRental.exception.AuthenticationException;
import edu.miu.carRental.security.dto.req.JwtTokenRequest;
import edu.miu.carRental.security.dto.res.JwtTokenResponse;
import edu.miu.carRental.security.util.JwtTokenUtil;
import edu.miu.carRental.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		} catch (Exception e) {

			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}

	@Autowired
	public UserController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}

	@GetMapping(value = "admin/users")
	public List<User> list() {
		return userService.findAll();
	}

	@PostMapping(value = "admin/users/add")
	public User addNewUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}

	@GetMapping(value = "admin/users/get/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.findById(userId);
	}

	@PutMapping(value = "admin/users/update/{userId}")
	public User updateUser(@Valid @RequestBody User editedUser, @PathVariable Long userId) {
		return userService.update(editedUser, userId);
	}

	@DeleteMapping(value = "admin/users/delete/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
	}
}