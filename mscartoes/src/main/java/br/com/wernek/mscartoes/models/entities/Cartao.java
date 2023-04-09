package br.com.wernek.mscartoes.models.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.wernek.mscartoes.models.enums.BandeiraCartao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column
	private String nome;

	@Enumerated( EnumType.STRING )
	private BandeiraCartao bandeira;

	@Column
	private BigDecimal renda;

	@Column
	private BigDecimal limiteBasico;

	public Cartao( String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico ) {
		super();
		this.nome = nome;
		this.bandeira = bandeira;
		this.renda = renda;
		this.limiteBasico = limiteBasico;
	}

}
