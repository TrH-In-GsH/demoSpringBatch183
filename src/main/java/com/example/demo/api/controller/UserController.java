/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.api.controller;
import com.example.demo.api.config.ZaloPayOrderStatusResponse;
import com.example.demo.api.config.ZaloPayRequest;
import com.example.demo.api.config.ZaloPayResponse;
import com.example.demo.api.service.ZaloPayService;
import com.example.demo.modal.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAllUser() {
        User u = userRepository.findByUsername("admin");
        u.setRole("ADMIN");
        userRepository.save(u);
       return ResponseEntity.ok(userRepository.findAll());
    }
  
}