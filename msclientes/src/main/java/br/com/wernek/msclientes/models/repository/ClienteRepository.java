package br.com.wernek.msclientes.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wernek.msclientes.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByCpf( String cpf );
}
