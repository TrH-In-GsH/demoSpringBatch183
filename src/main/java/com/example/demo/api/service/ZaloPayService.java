/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.api.service;
import com.example.demo.api.config.ZaloPayConfig;
import com.example.demo.api.config.ZaloPayOrderStatusResponse;
import com.example.demo.api.config.ZaloPayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service
public class ZaloPayService {

    @Autowired
    private ZaloPayConfig zaloPayConfig;

    private RestTemplate restTemplate = new RestTemplate();

    public ZaloPayResponse createOrder(double amount, String appUser, String description) throws Exception {
        String appId = zaloPayConfig.getAppId();
        String key1 = zaloPayConfig.getKey1();
        String endpoint = zaloPayConfig.getEndpoint();

        String appTransId = getAppTransId(); // Tạo mã giao dịch duy nhất
        String appTime = String.valueOf(System.currentTimeMillis()); // Thời gian hiện tại

        Map<String, Object> order = new HashMap<>();
        order.put("appid", appId);
        order.put("apptransid", appTransId);
        order.put("apptime", appTime);
        order.put("amount", amount);
        order.put("appuser", appUser);
        order.put("embeddata", "{}");
        order.put("item", "[]");
        order.put("description", description);

        // Tạo dữ liệu để tính MAC
        String data = appId + "|" + appTransId + "|" + appUser + "|" + amount + "|" + appTime + "|" + "{}" + "|" + "[]";

        // Tính MAC
        String mac = HMACUtil.HMacHexStringEncode(key1, data);

        order.put("mac", mac);

        // Gửi yêu cầu đến ZaloPay
        ZaloPayResponse response = restTemplate.postForObject(endpoint, order, ZaloPayResponse.class);
        return response;
    }

    public ZaloPayOrderStatusResponse getOrderStatus(String appTransId) throws NoSuchAlgorithmException, InvalidKeyException {
        String appId = zaloPayConfig.getAppId();
        String key1 = zaloPayConfig.getKey1();
        String endpoint = zaloPayConfig.getEndpoint();

        // Tạo dữ liệu để tính MAC
        String data = appId + "|" + appTransId + "|" + key1;

        // Tính MAC
        String mac = HMACUtil.HMacHexStringEncode(key1, data);

        // Tạo payload cho yêu cầu
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("appid", appId);
        requestPayload.put("apptransid", appTransId);
        requestPayload.put("mac", mac);

        // Gửi yêu cầu đến ZaloPay
        ZaloPayOrderStatusResponse response = restTemplate.postForObject(endpoint, requestPayload, ZaloPayOrderStatusResponse.class);
        return response;
    }
    
    private String getAppTransId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String datePrefix = sdf.format(new Date());
        String transId = datePrefix + "_" + System.currentTimeMillis();
        return transId;
    }
}