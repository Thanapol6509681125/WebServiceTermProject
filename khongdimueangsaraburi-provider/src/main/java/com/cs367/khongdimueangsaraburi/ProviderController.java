package com.cs367.khongdimueangsaraburi;

import com.cs367.khongdimueangsaraburi.Item;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saraburi-provider")
public class ProviderController {

    private List<Item> reservedItems = new ArrayList<>(List.of(
            new Item("P001", "ข้าวเกรียบว่าว", "อาหารพื้นเมือง", "อำเภอเสาไห้"),
            new Item("P002", "ผ้าขาวม้า", "ของฝาก", "อำเภอแก่งคอย"),
            new Item("P003", "น้ำผึ้งเดือนห้า", "ของดีจากธรรมชาติ", "อำเภอพระพุทธบาท")
    ));

    // บริการจองสินค้า
    @PostMapping("/reserve")
    public String reserveItem(@RequestBody Item item) {
        reservedItems.add(item);
        return "ระบบได้บันทึกการจองของ: " + item.getName() + " แล้วเรียบร้อย";
    }

    // บริการแจ้งเตือนให้มารับสินค้า
    @PostMapping("/notify-pickup")
    public String notifyPickup(@RequestBody Item item) {
        return "แจ้งเตือน: กรุณามารับสินค้า '" + item.getName() + "' ที่ " + item.getLocation();
    }

    // แสดงสินค้าที่ถูกจอง
    @GetMapping("/reserved-items")
    public List<Item> getReservedItems() {
        return reservedItems;
    }
}
