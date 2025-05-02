package com.cs367.khongdimueangsaraburi_client;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/saraburi-customer")
public class CustomerController {

    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;

    public CustomerController(ItemRepository itemRepository, RestTemplate restTemplate) {
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate;

        if (itemRepository.count() == 0) {
            itemRepository.saveAll(List.of(
                new Item("P001", "ข้าวเกรียบว่าว", "อาหารพื้นเมือง", "อำเภอเสาไห้"),
                new Item("P002", "ผ้าขาวม้า", "ของฝาก", "อำเภอแก่งคอย"),
                new Item("P003", "น้ำผึ้งเดือนห้า", "ของดีจากธรรมชาติ", "อำเภอพระพุทธบาท"),
                new Item("P004", "กระยาสารท", "ขนมโบราณ", "อำเภอหนองแค")
            ));
        }
    }

    // แสดงรายการสินค้าทั้งหมดจากลูกค้า
    @GetMapping("/all-products")
    public List<Item> getAllProducts() {
        return itemRepository.findAll();
    }

    // จองสินค้าและส่งข้อมูลไปยัง Provider
    @PostMapping("/reserve-item")
    public String reserveItem(@RequestBody Item item) {
        // เรียกไปยัง provider เพื่อบันทึกการจอง
        String reserveUrl = "http://localhost:8080/saraburi-provider/reserve";
        String result = restTemplate.postForObject(reserveUrl, item, String.class);
        return "จองสำเร็จ: " + result;
    }

    // ดูรายการสินค้าที่จองไว้
    @GetMapping("/my-reservations")
    public Item[] getMyReservations() {
        // ดึงข้อมูลสินค้าที่จองไว้ทั้งหมดจาก provider
        String url = "http://localhost:8080/saraburi-provider/reserved-items";
        return restTemplate.getForObject(url, Item[].class);
    }
}
