package br.com.wernek.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Value( "${mq.queues.emissao-cartoes}" )
	private String emissaoCartoesFila;

	@Bean
	public Queue emissaoCartaoQueue() {
		return new Queue( this.emissaoCartoesFila, true );
	}
}
