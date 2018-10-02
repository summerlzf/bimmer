package com.kedu.bimmer;

import com.kedu.bimmer.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebMvcConfig.class})
public class BimmerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BimmerApplication.class, args);
	}
}
