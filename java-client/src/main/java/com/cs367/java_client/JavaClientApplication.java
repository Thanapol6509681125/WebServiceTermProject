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
	
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8081/customer/all-products"))  // เปลี่ยนพอร์ตและ URL ให้ถูกต้อง
					.build();
	
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response from customer service: " + response.body());
	
			
			request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8081/customer/reserve-item"))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString("\"สินค้า A\""))
					.build();
	
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response from customer service: " + response.body());
	
			
			request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8080/provider/notify-pickup"))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString("\"สินค้า A\""))
					.build();
	
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response from provider service: " + response.body());
	
			
			request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8080/provider/reserved-items"))
					.build();
	
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response from provider service: " + response.body());
		}
	}
	