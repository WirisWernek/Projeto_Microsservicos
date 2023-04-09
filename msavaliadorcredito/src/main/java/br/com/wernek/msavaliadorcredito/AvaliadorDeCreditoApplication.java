package br.com.wernek.msavaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
public class AvaliadorDeCreditoApplication {

	public static void main( String[] args ) {
		SpringApplication.run( AvaliadorDeCreditoApplication.class, args );
	}

}
