package com.utfpr.delivery.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestauranteDTO {

	private String uuid;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	

}
