/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @Entity annotation defines that a 
// class can be mapped to a table
@Entity 
public class Employee {
  
    // @ID This annotation specifies 
    // the primary key of the entity.
    @Id 
  
    // @GeneratedValue This annotation 
    // is used to specify the primary 
    // key generation strategy to use
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;
    private String name;
    private String city;
    private String phone;
    private String email;
    public Employee() {
        super();
    }
    public Employee(String name, String city, String phone, String email) {
        super();
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public Employee(long id,String name, String city, String phone, String email) {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
}
