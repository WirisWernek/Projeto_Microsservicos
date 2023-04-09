package br.com.wernek.msclientes.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.wernek.msclientes.models.entities.Cliente;
import br.com.wernek.msclientes.models.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository repository;

	@Transactional
	public Cliente Save( Cliente cliente ) {
		return repository.save( cliente );
	}

	@Transactional
	public Optional<Cliente> Search( String cpf ) {
		return repository.findByCpf( cpf );
	}

	@Transactional
	public List<Cliente> GetAll() {
		return repository.findAll();
	}

}
