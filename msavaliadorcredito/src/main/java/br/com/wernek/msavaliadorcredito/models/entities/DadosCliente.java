package br.com.wernek.msavaliadorcredito.models.entities;

import lombok.Data;

@Data
public class DadosCliente {

	private String cpf;
	private String nome;
	private Integer idade;
}
