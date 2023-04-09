package br.com.wernek.msavaliadorcredito.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wernek.msavaliadorcredito.models.entities.DadosSolicitacaoEmissaoCartao;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoCartoesPublisher {

	private final RabbitTemplate rabbitTemplate;
	private final Queue emissaoCartoesQueue;

	public void solicitarCartao( DadosSolicitacaoEmissaoCartao dados ) throws JsonProcessingException {
		var json = convertIntoJson( dados );
		rabbitTemplate.convertAndSend( emissaoCartoesQueue.getName(), json );
	}

	private String convertIntoJson( DadosSolicitacaoEmissaoCartao dados ) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString( dados );
		return json;
	}
}
