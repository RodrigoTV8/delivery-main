package com.utfpr.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Restaurante;
import com.utfpr.delivery.exception.NotFoundException;
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
	
	public Restaurante getRestauranteByUuid(String uuid) {
		
		Restaurante restaurante = restauranteRepository.findByUuid(uuid);
		if(restaurante == null) {
			throw new NotFoundException("Restaurante não encontrado");
		}
		return restaurante;
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		
		return restauranteRepository.save(restaurante);
		
	}
	
	public Restaurante alterar(String uuid, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.getRestauranteByUuid(uuid);
		
		if (restauranteAtual != null) {
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "uuid");
			
			return restauranteRepository.save(restauranteAtual);
			
		}
		
		return null;
		
	}
	
public Restaurante alterarPontual(String uuid, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.getRestauranteByUuid(uuid);
		
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
	
	
	public boolean excluir(String uuid) {
		
		Restaurante restaurante = this.getRestauranteByUuid(uuid);
		
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
