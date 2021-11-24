package com.utfpr.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Produto;
import com.utfpr.delivery.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodosProdutos(){
		
		return produtoRepository.findAll();
	}
	
	public Produto salvar(Produto produto) {
		
		return produtoRepository.save(produto);
	}
	
}
