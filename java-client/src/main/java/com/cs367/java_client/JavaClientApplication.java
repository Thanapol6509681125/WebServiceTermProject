package com.cs367.java_client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class JavaClientApplication {

        public static void main(String[] args) throws Exception {

                HttpClient client = HttpClient.newHttpClient();

                String itemJson = """
                                {
                                  "id": "P001",
                                  "name": "ข้าวเกรียบว่าว",
                                  "category": "อาหารพื้นเมือง",
                                  "location": "อำเภอเสาไห้"
                                }
                                """;

                String itemJson2 = """
                                {
                                  "id": "P002",
                                  "name": "ผ้าขาวม้า",
                                  "category": "ของฝาก",
                                  "location": "อำเภอแก่งคอย"
                                }
                                """;

                HttpRequest request = HttpRequest.newBuilder()
                                .uri(new URI("http://localhost:8081/saraburi-customer/all-products"))
                                .GET()
                                .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("🛒 รายการสินค้าทั้งหมดจากลูกค้า: \n" + response.body());

                request = HttpRequest.newBuilder()
                                .uri(new URI("http://localhost:8081/saraburi-customer/reserve-item"))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(itemJson2))
                                .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("✅ การจองสินค้าฝั่งลูกค้า: " + response.body());

                request = HttpRequest.newBuilder()
                                .uri(new URI("http://localhost:8080/saraburi-provider/notify-pickup"))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(itemJson2))
                                .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("📣 การแจ้งเตือนจากผู้ให้บริการ: " + response.body());

                request = HttpRequest.newBuilder()
                                .uri(new URI("http://localhost:8080/saraburi-provider/reserved-items"))
                                .GET()
                                .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("📦 รายการสินค้าที่ถูกจองจากผู้ให้บริการ: \n" + response.body());
        }
}