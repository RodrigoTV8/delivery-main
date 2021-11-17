package com.utfpr.delivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestauranteResumoDTO {

	private Long id;
	
	private String nome;
	
	private String descricao;

}
