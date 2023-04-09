package br.com.wernek.msavaliadorcredito.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wernek.msavaliadorcredito.exceptions.BusinessException;
import br.com.wernek.msavaliadorcredito.models.entities.DadosAvaliacao;
import br.com.wernek.msavaliadorcredito.models.entities.DadosSolicitacaoEmissaoCartao;
import br.com.wernek.msavaliadorcredito.models.entities.ProtocoloSolicitacaoCartao;
import br.com.wernek.msavaliadorcredito.models.entities.RetornoAvaliacaoCliente;
import br.com.wernek.msavaliadorcredito.models.entities.SituacaoCliente;
import br.com.wernek.msavaliadorcredito.services.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping( "/avaliacoes-credito" )
@Slf4j
@RequiredArgsConstructor
public class AvaliadorCreditoController {

	private final AvaliadorCreditoService service;

	@GetMapping( path = "/status" )
	public String status() {
		log.info( "Obtendo o status do microserviço" );
		return "ok";
	}

	@GetMapping( path = "/situacao-cliente/{cpf}" )
	public ResponseEntity consultaSituacaoCliente( @PathVariable String cpf ) {

		try {
			SituacaoCliente situacaoCliente;
			situacaoCliente = service.obterSituacaoCliente( cpf );
			return ResponseEntity.ok( situacaoCliente );
		} catch ( BusinessException cbe ) {
			return ResponseEntity.badRequest().body( cbe.getMessage() );
		} catch ( Exception e ) {
			log.info( e.getMessage() );
			log.info( e.toString() );
			return ResponseEntity.internalServerError().body( e.getMessage() );

		}

	}

	@PostMapping( path = "/" )
	public ResponseEntity realizarAvaliacao( @RequestBody DadosAvaliacao dados ) {
		try {
			RetornoAvaliacaoCliente avaliacao;
			avaliacao = service.realizarAvaliacao( dados );
			return ResponseEntity.ok( avaliacao );
		} catch ( BusinessException cbe ) {
			return ResponseEntity.badRequest().body( cbe.getMessage() );
		} catch ( Exception e ) {
			log.info( e.getMessage() );
			log.info( e.toString() );
			return ResponseEntity.internalServerError().body( e.getMessage() );

		}
	}

	@PostMapping( path = "/solicitacoes-cartao" )
	public ResponseEntity solicitaCartao( @RequestBody DadosSolicitacaoEmissaoCartao dados ) {
		try {
			ProtocoloSolicitacaoCartao protocolo = service.solicitarEmissaoCartao( dados );
			return ResponseEntity.ok( protocolo );
		} catch ( BusinessException cbe ) {
			return ResponseEntity.badRequest().body( cbe.getMessage() );
		} catch ( Exception e ) {
			log.info( e.getMessage() );
			log.info( e.toString() );
			return ResponseEntity.internalServerError().body( e.getMessage() );

		}
	}
}
