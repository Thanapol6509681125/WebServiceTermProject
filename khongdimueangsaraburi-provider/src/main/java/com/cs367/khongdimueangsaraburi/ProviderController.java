package com.cs367.khongdimueangsaraburi;

import com.cs367.khongdimueangsaraburi.Item;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saraburi-provider")
public class ProviderController {

    private final List<Item> reservedItems = new ArrayList<>();

    // รับการจองสินค้าจากลูกค้า แล้วบันทึกไว้
    @PostMapping("/reserve")
    public String reserveItem(@RequestBody Item item) {
        reservedItems.add(item);
        return "✅ บันทึกการจองสินค้าเรียบร้อย: " + item.getName();
    }

    // แจ้งเตือนให้ลูกค้ามารับสินค้า (ใช้ข้อมูลที่ถูกจองไว้)
    @PostMapping("/notify-pickup")
    public String notifyPickup(@RequestBody Item item) {
        return "📣 แจ้งเตือน: กรุณามารับสินค้า '" + item.getName() + "' ที่ " + item.getLocation();
    }

    // แสดงรายการสินค้าที่ถูกจองไว้ทั้งหมด
    @GetMapping("/reserved-items")
    public List<Item> getReservedItems() {
        return reservedItems;
    }

    // แสดงรายการสินค้าทั้งหมด (รวมทั้งสินค้าที่ไม่ได้ถูกจอง)
    @GetMapping("/all-products")
    public List<Item> getAllProducts() {
        // ข้อมูลสินค้าทั้งหมด
        List<Item> allProducts = new ArrayList<>();
        allProducts.add(new Item("P001", "ข้าวเกรียบว่าว", "อาหารพื้นเมือง", "อำเภอเสาไห้"));
        allProducts.add(new Item("P002", "ผ้าขาวม้า", "ของฝาก", "อำเภอแก่งคอย"));
        allProducts.add(new Item("P003", "น้ำผึ้งเดือนห้า", "ของดีจากธรรมชาติ", "อำเภอพระพุทธบาท"));
        allProducts.add(new Item("P004", "กระยาสารท", "ขนมโบราณ", "อำเภอหนองแค"));
        return allProducts;
    }
}
