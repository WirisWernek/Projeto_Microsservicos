package br.com.wernek.mscartoes.models.representation;

import java.math.BigDecimal;

import br.com.wernek.mscartoes.models.entities.Cartao;
import br.com.wernek.mscartoes.models.enums.BandeiraCartao;
import lombok.Data;

@Data
public class CartaoSaveRequest {

	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limiteBasico;

	public Cartao toModel() {
		return new Cartao( this.nome, this.bandeira, this.renda, this.limiteBasico );
	}
}
