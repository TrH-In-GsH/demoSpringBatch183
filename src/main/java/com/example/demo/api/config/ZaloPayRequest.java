/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.api.config;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author HP
 */
@Getter
@Setter
public class ZaloPayRequest {
   private String appid;
    private String apptransid;
    private String apptime;
    private String amount;
    private String appuser;
    private String embeddata;
    private String item;
    private String description;
    private String mac;
}
