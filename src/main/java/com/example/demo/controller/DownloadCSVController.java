/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import au.com.bytecode.opencsv.CSVWriter;
import com.example.demo.modal.Employee;
import com.example.demo.service.EmpServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadCSVController {
    @Autowired
    EmpServiceImpl empServiceImpl;
    
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        //get list employ
        List<Employee> empList = new ArrayList<>();
        empList = empServiceImpl.findAllEmployee();
        
        //add data vao result 
        List<String[]> result = new ArrayList<>();
        result.add(new String[] {"Employ Name","Employ City","Employ phone","Employ email"});
        empList.forEach(emp -> {
            result.add(new String[] {emp.getName(),emp.getCity(),emp.getPhone(),emp.getEmail()});
        });
        String fileName = "country.csv";
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(),Charset.forName("UTF-8")))){
            writer.writeAll(result);
        }
    }
}
