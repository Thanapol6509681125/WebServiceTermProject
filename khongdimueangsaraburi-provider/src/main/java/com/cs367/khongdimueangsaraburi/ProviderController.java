package com.cs367.khongdimueangsaraburi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    // ฐานข้อมูลจากร้านค้า
    private List<String> reservedItems = List.of("สินค้า A", "สินค้า B", "สินค้า C");

    // 1. บริการสำหรับการจองสินค้า
    @PostMapping("/reserve")
    public String reserveItem(@RequestBody String item) {
        return "จองสินค้า: " + item + " สำเร็จ";
    }

    // 2. บริการสำหรับการแจ้งเตือนให้มารับสินค้า
    @PostMapping("/notify-pickup")
    public String notifyPickup(@RequestBody String item) {
        return "โปรดมารับสินค้า: " + item;
    }

    // 3. บริการแสดงรายการสินค้าที่ถูกจอง
    @GetMapping("/reserved-items")
    public List<String> getReservedItems() {
        return reservedItems;
    }
}
