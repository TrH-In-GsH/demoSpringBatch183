/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
public class EmployForm {
    
    private long id;
    @NotNull
    @Size(min=2, max=20)
    private String name;
    @Size(min=2, max=50)
    private String city;
    @Pattern(regexp="^[0-9]*$", message = "Vui long nhap so")
    private String phone;
    @Email(regexp = "^(?:[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})?$", message = "Email khong hop le")
    private String email;
}
