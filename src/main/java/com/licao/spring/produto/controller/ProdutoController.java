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

import com.licao.spring.Entidades.models.Produto;
import com.licao.spring.produto.service.ProdutoService;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	private final String PRODUTO_PATH = "/produto/";

	@GetMapping
	public ResponseEntity<Iterable<Produto>> listar() {

		Iterable<Produto> produtos = produtoService.listar();

		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable Integer id) {
		Optional<Produto> produto = produtoService.buscar(id);

		if (produto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produto.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
		try {
			Produto produtoInserido = produtoService.persistir(produto);
			URI uri = URI.create(PRODUTO_PATH + produtoInserido.getId());
			return ResponseEntity.created(uri).body(produtoInserido);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
		try {

			if (produtoService.existe(produto.getId())) {
				Produto produtoAtualizado = produtoService.persistir(produto);
				return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		if (produtoService.existe(id)) {
			produtoService.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
