/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.api.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author HP
 */
@Getter
@Setter
public class ZaloPayResponse {
    private int returncode;
    private String returnmessage;
    private String subreturncode;
    private String subreturnmessage;
    private Map<String, Object> orderinfo;
}
