/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.modal.EmployForm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.modal.Employee;
import com.example.demo.service.EmpServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpController {
  
    @Autowired
    EmpServiceImpl empServiceImpl;
 
    @GetMapping({"/findall","/"})
    public String getAllEmployee(Model model) {
    
    //get all Employ from database
    ArrayList<Employee> empList = empServiceImpl.findAllEmployee();
    
    // add attribute for screen
    model.addAttribute("empList", empList);
    return "employList";
    }
    
    @GetMapping("/add")
    public String add(@ModelAttribute("EmployForm") EmployForm form) {
        return "employCreate";
    }
    
    @PostMapping("/doAdd")
    public String doAdd(@Valid @ModelAttribute("formEdit") EmployForm formEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
			return "employCreate";
	}
        
        
//        String name = formEdit.getName();
//        String city = formEdit.getCity();
//        String phone = formEdit.getPhone();
//        String email = formEdit.getEmail();
//        Employee emp = new Employee(name,city,phone,email);
        
         // Tạo đối tượng đích
        Employee emp = new Employee();
        
        // Sao chép các thuộc tính từ đối tượng nguồn sang đối tượng đích
        BeanUtils.copyProperties(formEdit, emp);
        empServiceImpl.addEmployee(emp);
        
        return "redirect:/findall";
//        model.addAttribute("empList", empServiceImpl.findAllEmployee());
//        return "employList";
    }
    
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam long id) {
        Employee emp = empServiceImpl.findAllEmployeeByID(id);
        model.addAttribute("formEdit", emp);
        return "employEdit";
    }
    
    @PostMapping("/doEdit")
    public String doEdit(@Valid @ModelAttribute("formEdit") EmployForm formEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//          bindingResult.addError(new FieldError("formEdit","name",null,false,null,null,"1111111111"));
            return "employEdit";
	}
        
        if ( "".equals(formEdit.getPhone()) && "".equals(formEdit.getEmail())){
            model.addAttribute("errors", "Vui long nhap Phone or Email !");
            return "employEdit";
        }
        
        //validate item
        long id = formEdit.getId();
        String name = formEdit.getName();
        String city = formEdit.getCity();
        String phone = formEdit.getPhone();
        String email = formEdit.getEmail();
        Employee emp = new Employee(id,name,city,phone,email);
        empServiceImpl.addEmployee(emp);
        
        return "redirect:/findall";
//        model.addAttribute("empList", empServiceImpl.findAllEmployee());
//        return "employList";
    }
    
    @GetMapping("/delete")
    public String doDelete(Model model,@RequestParam long id) {

        empServiceImpl.deleteById(id);
        
        return "redirect:/findall";
//        model.addAttribute("empList", empServiceImpl.findAllEmployee());
//        return "employList";
    }
}