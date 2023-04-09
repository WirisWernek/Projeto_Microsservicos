package br.com.wernek.msclientes.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wernek.msclientes.models.entities.Cliente;
import br.com.wernek.msclientes.models.representation.ClienteSaveRequest;
import br.com.wernek.msclientes.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping( "/clientes" )
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

	private final ClienteService service;

	@GetMapping( path = "/status" )
	public String status() {
		log.info( "Obtendo o status do microserviço" );
		return "ok";
	}

	@PostMapping( )
	public ResponseEntity Salvar( @RequestBody ClienteSaveRequest request ) {
		log.info( "Inserindo um registro pelo microserviço" );

		Cliente cliente = request.toModel();
		service.Save( cliente );
		URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{cpf}" ).buildAndExpand( cliente.getCpf() ).toUri();
		return ResponseEntity.created( headerLocation ).build();

	}

	@GetMapping( path = "/{cpf}" )
	public ResponseEntity Buscar( @PathVariable String cpf ) {
		log.info( "Buscando um registro pelo microserviço" );

		Optional<Cliente> cliente = service.Search( cpf );
		if( cliente.isEmpty() ) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok( cliente );
	}

	@GetMapping( path = "/list" )
	public ResponseEntity ListarTodos() {
		log.info( "Listando todos os registros pelo microserviço" );
		List<Cliente> listClientes = service.GetAll();
		return ResponseEntity.ok( listClientes );
	}

}
