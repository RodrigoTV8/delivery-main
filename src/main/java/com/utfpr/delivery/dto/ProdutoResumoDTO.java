package com.utfpr.delivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProdutoResumoDTO {
	
	private Long id;
	
	private String nome;

}
