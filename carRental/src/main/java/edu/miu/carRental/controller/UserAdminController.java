package edu.miu.carRental.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.miu.carRental.domain.Car;
import edu.miu.carRental.domain.Role;
import edu.miu.carRental.domain.User;
import edu.miu.carRental.service.UserService;

@Controller
@CrossOrigin(allowedHeaders = "*")

public class UserAdminController {

    @Autowired
    private UserService userService;
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/nusers")
    public ModelAndView getAllCars() {
    	ModelAndView modelAndView = new ModelAndView();
        List<User> users =userService.findAll();
        modelAndView.addObject("users",users);
        modelAndView.addObject("userSize",users.size());
        modelAndView.setViewName("user/list");
        return modelAndView;
        
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = {"/admin/nusers/new"})
    public String displayNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }
   
   
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = {"/admin/nusers/new"})
    public String addNewUser(@Valid @ModelAttribute("user")User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user);
            System.out.println(bindingResult.getAllErrors());
            return "user/add";
        }
        Role role1=new Role();
        role1.setRoleName("ADMIN");
        Role role2=new Role();
        role2.setRoleName("EMPLOYEE");
        List<Role> r=new ArrayList<>();
        r.add(role2);
        r.add(role1);
        user.setRoles(r);
        userService.save(user);
        return "redirect:/admin/nusers";
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = {"/admin/nusers/edit/{userId}"})
	public String editUser(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/edit";
        }
        return "user/list";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = {"/admin/nusers/edit"})
    public String editUser(@Valid @ModelAttribute("user") User user,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "user/edit";
        }
        user = userService.update(user, user.getUserId());
        return "redirect:admin/nusers";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = {"/admin/nusers/delete/{userId}"})
	public String deleteUser(@PathVariable Long userId, Model model) {
        userService.delete(userId);
        return "redirect:/admin/nusers";
    }
    
}