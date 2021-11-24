package com.utfpr.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.ProdutoResumoDTO;
import com.utfpr.delivery.entity.Produto;
import com.utfpr.delivery.mapper.ProdutoResumoOutputMapper;
import com.utfpr.delivery.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
		
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoResumoOutputMapper produtoResumoOutputMapper;
	
	@GetMapping
	@ResponseBody
	public List<ProdutoResumoDTO> listarTodosProdutos(){
		
		List<Produto> produtos = produtoService.listarTodosProdutos();
		
		List<ProdutoResumoDTO> produtosResumosDTO = produtoResumoOutputMapper.mapearLista(produtos);
		
		return produtosResumosDTO;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private Produto adicionar(@RequestBody Produto produto) {
		
		if(produto != null) {
			return produtoService.salvar(produto);
		}else {
			return null;
		}
	}

}
