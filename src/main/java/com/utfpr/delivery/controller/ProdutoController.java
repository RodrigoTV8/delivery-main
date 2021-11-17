package com.utfpr.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.entity.Produto;
import com.utfpr.delivery.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
		
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private Produto adicionar(@RequestBody Produto produto) {
		
		return produtoService.salvar(produto);
	}

}
