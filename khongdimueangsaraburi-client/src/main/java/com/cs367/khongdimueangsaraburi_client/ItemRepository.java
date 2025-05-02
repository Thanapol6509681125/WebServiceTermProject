package com.cs367.khongdimueangsaraburi_client;

import com.cs367.khongdimueangsaraburi_client.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
