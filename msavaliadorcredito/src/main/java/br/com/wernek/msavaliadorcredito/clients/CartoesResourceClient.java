package br.com.wernek.msavaliadorcredito.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wernek.msavaliadorcredito.models.entities.Cartao;
import br.com.wernek.msavaliadorcredito.models.entities.CartaoCliente;

@FeignClient( value = "mscartoes", path = "/cartoes" )
public interface CartoesResourceClient {

	@GetMapping( path = "/autorizados/{cpf}" )
	public ResponseEntity<List<CartaoCliente>> cartoesPorCliente( @PathVariable String cpf );

	@GetMapping( path = "/renda/{renda}" )
	public ResponseEntity<List<Cartao>> getCartoesRendaAte( @PathVariable Long renda );
}
