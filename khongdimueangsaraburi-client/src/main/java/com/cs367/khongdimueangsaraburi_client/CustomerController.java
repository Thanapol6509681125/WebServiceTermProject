package com.cs367.khongdimueangsaraburi_client;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    // ฐานข้อมูลจากร้านค้า
    private List<String> availableItems = Arrays.asList("สินค้า A", "สินค้า B", "สินค้า C");

    // 1. บริการดึงข้อมูลสินค้าจากเจ้าของร้าน
    @GetMapping("/all-products")
    public List<String> getAllProducts() {
        return availableItems;
    }

    // 2. บริการสำหรับการจองสินค้า
    @PostMapping("/reserve-item")
    public String reserveItem(@RequestBody String item) {
        return "จองสินค้า: " + item + " สำเร็จ";
    }
}
