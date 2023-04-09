package br.com.wernek.mscartoes.services;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.wernek.mscartoes.models.entities.Cartao;
import br.com.wernek.mscartoes.models.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

	private final CartaoRepository repository;

	@Transactional
	public Cartao Salvar( Cartao cartao ) {
		return repository.save( cartao );

	}

	public List<Cartao> getCartoesRendaMenorIgual( Long renda ) {
		var rendaBigDecimal = BigDecimal.valueOf( renda );
		return repository.findByRendaLessThanEqual( rendaBigDecimal );

	}
}
