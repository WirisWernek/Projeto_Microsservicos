package br.com.wernek.msclientes.models.representation;

import br.com.wernek.msclientes.models.entities.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {

	private String cpf;
	private String nome;
	private Integer idade;

	public Cliente toModel() {
		return new Cliente( this.cpf, this.nome, this.idade );
	}
}
