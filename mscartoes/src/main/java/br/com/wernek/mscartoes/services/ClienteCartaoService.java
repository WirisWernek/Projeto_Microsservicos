package br.com.wernek.mscartoes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wernek.mscartoes.models.entities.ClienteCartao;
import br.com.wernek.mscartoes.models.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

	private final ClienteCartaoRepository repository;

	public List<ClienteCartao> Search( String cpf ) {
		return repository.findByCpf( cpf );
	}
}
