package br.com.wernek.mscartoes.queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wernek.mscartoes.models.entities.Cartao;
import br.com.wernek.mscartoes.models.entities.ClienteCartao;
import br.com.wernek.mscartoes.models.entities.DadosSolicitacaoEmissaoCartao;
import br.com.wernek.mscartoes.models.repository.CartaoRepository;
import br.com.wernek.mscartoes.models.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

	private final CartaoRepository cartaoRepository;
	private final ClienteCartaoRepository clienteCartaoRepository;

	@RabbitListener( queues = "${mq.queues.emissao-cartoes}" )
	public void receberSolicitacaoEmissao( @Payload String message ) {
		System.out.println( message );
		try {
			var mapper = new ObjectMapper();
			DadosSolicitacaoEmissaoCartao dados = mapper.readValue( message, DadosSolicitacaoEmissaoCartao.class );
			Cartao cartao = cartaoRepository.findById( dados.getIdCartao() ).orElseThrow();
			ClienteCartao clienteCartao = new ClienteCartao();
			clienteCartao.setCartao( cartao );
			clienteCartao.setCpf( dados.getCpf() );
			clienteCartao.setLimite( dados.getLimiteLiberado() );
			clienteCartaoRepository.save( clienteCartao );
		} catch ( JsonProcessingException jpe ) {
			jpe.printStackTrace();
		}

	}
}
