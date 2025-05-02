package com.cs367.khongdimueangsaraburi;

import com.cs367.khongdimueangsaraburi.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
