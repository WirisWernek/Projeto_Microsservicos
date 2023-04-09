package br.com.wernek.msavaliadorcredito.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.wernek.msavaliadorcredito.clients.CartoesResourceClient;
import br.com.wernek.msavaliadorcredito.clients.ClienteResourceClient;
import br.com.wernek.msavaliadorcredito.exceptions.BusinessException;
import br.com.wernek.msavaliadorcredito.models.entities.Cartao;
import br.com.wernek.msavaliadorcredito.models.entities.CartaoAprovado;
import br.com.wernek.msavaliadorcredito.models.entities.CartaoCliente;
import br.com.wernek.msavaliadorcredito.models.entities.DadosAvaliacao;
import br.com.wernek.msavaliadorcredito.models.entities.DadosCliente;
import br.com.wernek.msavaliadorcredito.models.entities.DadosSolicitacaoEmissaoCartao;
import br.com.wernek.msavaliadorcredito.models.entities.ProtocoloSolicitacaoCartao;
import br.com.wernek.msavaliadorcredito.models.entities.RetornoAvaliacaoCliente;
import br.com.wernek.msavaliadorcredito.models.entities.SituacaoCliente;
import br.com.wernek.msavaliadorcredito.queues.EmissaoCartoesPublisher;
import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

	private final ClienteResourceClient clienteClient;
	private final CartoesResourceClient cartoesClient;
	private final EmissaoCartoesPublisher publisher;

	public SituacaoCliente obterSituacaoCliente( String cpf ) throws Exception {

		try {

			ResponseEntity<DadosCliente> dadosCliente = clienteClient.dadosCliente( cpf );
			ResponseEntity<List<CartaoCliente>> cartoesCliente = cartoesClient.cartoesPorCliente( cpf );

			return SituacaoCliente.builder().cliente( dadosCliente.getBody() ).cartoes( cartoesCliente.getBody() ).build();

		} catch ( FeignClientException fe ) {
			int status = fe.status();
			if( HttpStatus.NOT_FOUND.value() == status ) {
				throw new BusinessException( "Não foram encontrados os dados para o cliente com o CPF " + cpf, status );
			}
			throw new BusinessException( "Ocorreu um erro na comunicação com os microserviços.", status );

		} catch ( Exception e ) {
			throw new Exception( "Erro interno no servidor" + UUID.randomUUID() );
		}

	}

	public RetornoAvaliacaoCliente realizarAvaliacao( DadosAvaliacao dados ) throws Exception {
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente( dados.getCpf() );
			ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAte( dados.getRenda() );

			List<Cartao> cartoes = cartoesResponse.getBody();
			var listaCartoesAprovados = cartoes.stream().map( cartao -> {

				BigDecimal limiteBasico = cartao.getLimiteBasico();
				BigDecimal idadeDB = BigDecimal.valueOf( dadosClienteResponse.getBody().getIdade() );

				var fator = idadeDB.divide( BigDecimal.valueOf( 10 ) );
				var limiteAprovado = fator.multiply( limiteBasico );

				CartaoAprovado aprovado = new CartaoAprovado();
				aprovado.setCartao( cartao.getNome() );
				aprovado.setBandeira( cartao.getBandeira() );
				aprovado.setLimiteAprovado( limiteAprovado );
				return aprovado;
			} ).collect( Collectors.toList() );

			return new RetornoAvaliacaoCliente( listaCartoesAprovados );

		} catch ( FeignClientException fe ) {
			int status = fe.status();
			if( HttpStatus.NOT_FOUND.value() == status ) {
				throw new BusinessException( "Não foram encontrados os dados para o cliente com o CPF " + dados.getCpf(), status );
			}
			throw new BusinessException( "Ocorreu um erro na comunicação com os microserviços.", status );

		} catch ( Exception e ) {
			throw new Exception( "Erro interno no servidor" + UUID.randomUUID() );
		}
	}

	public ProtocoloSolicitacaoCartao solicitarEmissaoCartao( DadosSolicitacaoEmissaoCartao dados ) throws Exception {
		try {
			publisher.solicitarCartao( dados );
			var protocolo = UUID.randomUUID().toString();
			return new ProtocoloSolicitacaoCartao( protocolo );
		} catch ( Exception e ) {
			throw new Exception( "Erro interno no servidor" + UUID.randomUUID() );
		}
	}

}
