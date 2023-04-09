package br.com.wernek.mscartoes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wernek.mscartoes.models.entities.Cartao;
import br.com.wernek.mscartoes.models.representation.CartaoSaveRequest;
import br.com.wernek.mscartoes.models.representation.CartoesPorClienteResponse;
import br.com.wernek.mscartoes.services.CartaoService;
import br.com.wernek.mscartoes.services.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping( "/cartoes" )
@RequiredArgsConstructor
@Slf4j
public class CartoesController {

	private final CartaoService cartaoService;
	private final ClienteCartaoService clienteCartaoService;

	@GetMapping( path = "/status" )
	public String status() {
		log.info( "Obtendo o status do microserviço" );
		return "ok";
	}

	@PostMapping
	public ResponseEntity Salvar( @RequestBody CartaoSaveRequest request ) {
		Cartao cartao = request.toModel();
		cartao = cartaoService.Salvar( cartao );
		return ResponseEntity.status( HttpStatus.CREATED ).body( cartao );
	}

	@GetMapping( path = "/renda/{renda}" )
	public ResponseEntity<List<Cartao>> getCartoesRendaAte( @PathVariable Long renda ) {
		var list = cartaoService.getCartoesRendaMenorIgual( renda );
		return ResponseEntity.ok( list );
	}

	@GetMapping( path = "/autorizados/{cpf}" )
	public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesPorCliente( @PathVariable String cpf ) {
		var list = clienteCartaoService.Search( cpf );
		var resultList = list.stream().map( CartoesPorClienteResponse::fromModel ).collect( Collectors.toList() );
		return ResponseEntity.ok( resultList );
	}
}
