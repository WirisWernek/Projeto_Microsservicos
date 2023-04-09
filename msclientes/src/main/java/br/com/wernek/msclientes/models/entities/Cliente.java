package br.com.wernek.msclientes.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column
	private String cpf;

	@Column
	private String nome;

	@Column
	private Integer idade;

	public Cliente( String cpf, String nome, Integer idade ) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

}
