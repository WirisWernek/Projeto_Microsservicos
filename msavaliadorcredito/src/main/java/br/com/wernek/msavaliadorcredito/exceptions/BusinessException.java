package br.com.wernek.msavaliadorcredito.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer status;

	public BusinessException( String msg, Integer status ) {
		super( msg );
		this.status = status;
	}

}
