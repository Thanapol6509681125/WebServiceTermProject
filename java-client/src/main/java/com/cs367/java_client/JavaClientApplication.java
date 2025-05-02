package com.cs367.java_client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class JavaClientApplication {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // 1. ขอรายการสินค้าทั้งหมดจากผู้ให้บริการ
        String allProductsUrl = "http://localhost:8081/saraburi-customer/all-products"; 
        ResponseEntity<Item[]> response = restTemplate.getForEntity(allProductsUrl, Item[].class);
        System.out.println("📦 สินค้าทั้งหมดจากผู้ให้บริการ:");
        Arrays.stream(response.getBody()).forEach(item -> 
            System.out.println("- " + item.getName() + " (" + item.getCategory() + ")")
        );

        // 2. เลือกจองสินค้า 1 รายการ
        Item selectedItem = new Item("P001", "ข้าวเกรียบว่าว", "อาหารพื้นเมือง", "อำเภอเสาไห้");
        String reserveUrl = "http://localhost:8081/saraburi-customer/reserve-item";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Item> request = new HttpEntity<>(selectedItem, headers);

        String reserveResponse = restTemplate.postForObject(reserveUrl, request, String.class);
        System.out.println("\n✅ ผลการจอง: " + reserveResponse);

        // 3. ผู้ให้บริการแจ้งให้มารับสินค้า
        String notifyUrl = "http://localhost:8080/saraburi-provider/notify-pickup";
        String notifyResponse = restTemplate.postForObject(notifyUrl, request, String.class);
        System.out.println("\n📣 การแจ้งเตือน: " + notifyResponse);

        // 4. ขอข้อมูลรายการสินค้าที่ลูกค้าจองไว้
        String reservedListUrl = "http://localhost:8081/saraburi-customer/my-reservations";
        ResponseEntity<Item[]> reservedList = restTemplate.getForEntity(reservedListUrl, Item[].class);
        System.out.println("\n📋 รายการสินค้าที่ลูกค้าจองไว้:");
        Arrays.stream(reservedList.getBody()).forEach(item -> 
            System.out.println("- " + item.getName() + " (" + item.getLocation() + ")")
        );
    }
}
