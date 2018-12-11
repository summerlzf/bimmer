package com.kedu.bimmer;

import com.kedu.bimmer.config.BimmerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({BimmerConfiguration.class})
public class BimmerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BimmerApplication.class, args);
	}
}
