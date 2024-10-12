/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modal.Employee;
import com.example.demo.repository.EmployeeRepository;


//  @Service marks a Java class that performs some service,
//  such as executing business logic, performing 
//  calculations, and calling external APIs.
@Service 
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmployeeRepository employeeRepository;
  
    @Override
    public ArrayList<Employee> findAllEmployee() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }
  
    @Override
    public Employee findAllEmployeeByID(long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public void addEmployee(Employee employee) {
  
        employeeRepository.save(employee);
        
    }
  
    @Override
    public void deleteAllData() {
        employeeRepository.deleteAll();
    }
    
    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}