package com.abdo.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.abdo.shop.config.SecretsConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecretsConfigProperties.class)
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
