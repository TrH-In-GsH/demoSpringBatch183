/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.api.controller;
import com.example.demo.api.config.ZaloPayOrderStatusResponse;
import com.example.demo.api.config.ZaloPayRequest;
import com.example.demo.api.config.ZaloPayResponse;
import com.example.demo.api.service.ZaloPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private ZaloPayService zaloPayService;

    @PostMapping("/zalopay")
    public ResponseEntity<ZaloPayResponse> createZaloPayPayment(@RequestBody ZaloPayRequest request) {
        try {
            ZaloPayResponse response = zaloPayService.createOrder(
                    Double.parseDouble(request.getAmount()),
                    request.getAppuser(),
                    request.getDescription()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @GetMapping("/zalopay/status/{appTransId}")
    public ResponseEntity<ZaloPayOrderStatusResponse> getOrderStatus(@PathVariable String appTransId) {
        try {
            ZaloPayOrderStatusResponse response = zaloPayService.getOrderStatus(appTransId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}