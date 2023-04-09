package br.com.wernek.mscartoes.models.representation;

import java.math.BigDecimal;

import br.com.wernek.mscartoes.models.entities.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {

	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;

	public static CartoesPorClienteResponse fromModel( ClienteCartao model ) {
		return new CartoesPorClienteResponse( model.getCartao().getNome(), model.getCartao().getBandeira().toString(), model.getCartao().getLimiteBasico() );
	}
}
