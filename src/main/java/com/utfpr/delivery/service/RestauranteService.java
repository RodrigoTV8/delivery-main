package com.utfpr.delivery.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Restaurante;
import com.utfpr.delivery.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listarTodosOsRestaurantes() {
		
		return restauranteRepository.findAll();
		
	}
	
	public Restaurante getRestauranteById(Long id) {
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if (restaurante.isPresent()) {
			return restaurante.get();
		}
		
		return null;
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		
		return restauranteRepository.save(restaurante);
		
	}
	
	public Restaurante alterar(Long id, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.getRestauranteById(id);
		
		if (restauranteAtual != null) {
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
			
			return restauranteRepository.save(restauranteAtual);
			
		}
		
		return null;
		
	}
	
public Restaurante alterarPontual(Long id, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.getRestauranteById(id);
		
		if (restauranteAtual != null) {
			
			if(restaurante.getNome() != null) {
				restauranteAtual.setNome(restaurante.getNome());
			}
			if(restaurante.getTaxaFrete() != null) {
				restauranteAtual.setTaxaFrete(restaurante.getTaxaFrete());
			}
			
			return restauranteRepository.save(restauranteAtual);
			
		}
		
		return null;
		
	}
	
	
	public boolean excluir(Long id) {
		
		Restaurante restaurante = this.getRestauranteById(id);
		
		if (restaurante != null) {
			
			try {
		
				restauranteRepository.delete(restaurante);
				
				return true;
				
			} catch (EmptyResultDataAccessException ex) {
				
				System.out.println(ex.getMessage());
				
			}
			
		}
		
		return false;
		
	}
	
}
