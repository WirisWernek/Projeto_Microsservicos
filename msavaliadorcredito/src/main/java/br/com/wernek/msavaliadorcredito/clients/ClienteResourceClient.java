package br.com.wernek.msavaliadorcredito.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wernek.msavaliadorcredito.models.entities.DadosCliente;

@FeignClient( value = "msclientes", path = "/clientes" )
public interface ClienteResourceClient {

	@GetMapping( path = "/{cpf}" )
	public ResponseEntity<DadosCliente> dadosCliente( @PathVariable String cpf );

}
