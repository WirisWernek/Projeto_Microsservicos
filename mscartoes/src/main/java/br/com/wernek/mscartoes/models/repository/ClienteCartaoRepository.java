package br.com.wernek.mscartoes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wernek.mscartoes.models.entities.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {

	public List<ClienteCartao> findByCpf( String cpf );
}
