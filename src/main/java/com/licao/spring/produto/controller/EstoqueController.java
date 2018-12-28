package com.licao.spring.produto.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.licao.spring.Entidades.models.Estoque;
import com.licao.spring.produto.service.EstoqueService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;
	
	private final String ESTOQUE_PATH = "/estoque/";
	
	@GetMapping
	public ResponseEntity<Iterable<Estoque>> listar(){
		Iterable<Estoque> estoques = estoqueService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(estoques);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estoque> buscar(@PathVariable("id") Integer id){
		Optional<Estoque> estoque = estoqueService.buscar(id);
		
		if (estoque.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(estoque.get());
		}
	
		return ResponseEntity.status(HttpStatus.GONE).build();		
	}
	
	@PostMapping
	public ResponseEntity<Estoque> cadastrar(@RequestBody Estoque estoque){
		try {
			Estoque estoqueCadastrado = estoqueService.persistir(estoque);
			
			URI uri = URI.create(ESTOQUE_PATH+estoqueCadastrado.getId());
			
			return ResponseEntity.created(uri).body(estoqueCadastrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Estoque> atualizar(@RequestBody Estoque estoque){
		try {
			
			if (estoqueService.existe(estoque.getId())) {
				Estoque EstoqueAtualizado = estoqueService.persistir(estoque);	
				return ResponseEntity.status(HttpStatus.OK).body(EstoqueAtualizado);
			}
			
			return ResponseEntity.status(HttpStatus.GONE).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id")Integer id){
		Optional<Estoque> estoque = estoqueService.buscar(id);
		
		if (estoque.isPresent()) {
			estoqueService.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.GONE).build();
	}
	
}
