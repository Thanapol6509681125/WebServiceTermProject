package com.cs367.khongdimueangsaraburi_client;

import com.cs367.khongdimueangsaraburi_client.Item;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saraburi-customer")
public class CustomerController {

    private final ItemRepository itemRepository;

    public CustomerController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

        // preload some data
        if (itemRepository.count() == 0) {
            itemRepository.saveAll(List.of(
                new Item("P001", "ข้าวเกรียบว่าว", "อาหารพื้นเมือง", "อำเภอเสาไห้"),
                new Item("P002", "ผ้าขาวม้า", "ของฝาก", "อำเภอแก่งคอย"),
                new Item("P003", "น้ำผึ้งเดือนห้า", "ของดีจากธรรมชาติ", "อำเภอพระพุทธบาท"),
                new Item("P004", "กระยาสารท", "ขนมโบราณ", "อำเภอหนองแค")
            ));
        }
    }

    @GetMapping("/all-products")
    public List<Item> getAllProducts() {
        return itemRepository.findAll();
    }

    @PostMapping("/reserve-item")
    public String reserveItem(@RequestBody Item item) {
        return "คุณได้จองสินค้า '" + item.getName() + "' เรียบร้อยแล้ว!";
    }
}
