package br.com.wernek.mscartoes.models.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wernek.mscartoes.models.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	public List<Cartao> findByRendaLessThanEqual( BigDecimal rendaBigDecimal );

}
