package br.com.wernek.msavaliadorcredito.models.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {

	private DadosCliente cliente;
	private List<CartaoCliente> cartoes;
}
