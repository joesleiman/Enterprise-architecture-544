package edu.miu.carRental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;

import edu.miu.carRental.domain.User;
import edu.miu.carRental.service.UserService;

@RestController
@CrossOrigin(allowedHeaders = "*")

public class UserController {

    
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
		// TODO Auto-generated constructor stub
    	this.userService=userService;
	}
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "admin/users")
    public List<User> list() {
        return userService.findAll();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "admin/users/add")
    public User addNewUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "admin/users/get/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "admin/users/update/{userId}")
    public User updateUser(@Valid @RequestBody User editedUser, @PathVariable Long userId) {
        return userService.update(editedUser, userId);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "admin/users/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}