package edu.miu.carRental.controller;

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
import edu.miu.carRental.domain.User;
import edu.miu.carRental.service.CustomerService;
import edu.miu.carRental.serviceImp.CarServiceImp;

@Controller
@CrossOrigin(allowedHeaders = "*")
public class CarAdminController {

    @Autowired
    private CarServiceImp carService;
    
    
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/employee/ncars")
    public ModelAndView getAllCars() {
    	ModelAndView modelAndView = new ModelAndView();
        List<Car> cars = carService.findAll();
        modelAndView.addObject("cars",cars);
        modelAndView.addObject("carSize",cars.size());
        modelAndView.setViewName("car/list");
        return modelAndView;
        
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping(value = {"/employee/ncars/new"})
    public String displayNewCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "car/new";
    }
   
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PostMapping(value = {"/employee/ncars/new"})
    public String addNewCar(@Valid @ModelAttribute("car")Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("car", car);
            return "car/new";
        }
        carService.save(car);
        return "redirect:/employee/ncars";
    }
    
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping(value = {"/employee/ncars/edit/{carId}"})
	public String editCar(@PathVariable Long carId, Model model) {
        Car car = carService.findById(carId);
        if (car != null) {
            model.addAttribute("car", car);
            return "car/edit";
        }
        return "car/list";
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@PostMapping(value = {"/employee/ncars/edit"})
    public String editCar(@Valid @ModelAttribute("car") Car car,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "car/edit";
        }
        car = carService.updateCar(car.getCarId(), car);
        return "redirect:/employee/ncars";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = {"/employee/ncars/delete/{carId}"})
	public String deleteCar(@PathVariable Long carId, Model model) {
        carService.delete(carId);
        return "redirect:/employee/ncars";
    }
   
}

