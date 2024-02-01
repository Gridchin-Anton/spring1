package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.controller.ItemController;
import com.example.demo.model.Item;
import com.example.demo.repository.JDBCItemRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

			SpringApplication.run(DemoApplication.class, args);



//		@Configuration
//		class MyConfiguration implements WebMvcConfigurer {
//			@Bean
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//			}
//		};


		@Configuration
		@EnableWebMvc
		class WebConfig implements WebMvcConfigurer {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		}


	}
}
