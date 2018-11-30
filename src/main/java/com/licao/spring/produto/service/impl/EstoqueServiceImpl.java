package com.licao.spring.produto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.licao.spring.produto.model.Estoque;
import com.licao.spring.produto.repository.EstoqueRepository;
import com.licao.spring.produto.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Override
	public Iterable<Estoque> listar() {
		return estoqueRepository.findAll();
	}

	@Override
	public Optional<Estoque> buscar(Integer id) {
		return estoqueRepository.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Estoque persistir(Estoque estoque) throws Exception {
		return estoqueRepository.save(estoque);
	}

	@Override
	public void deletar(Integer id) {
		estoqueRepository.deleteById(id);
	}

	@Override
	public boolean existe(Integer id) {
		return estoqueRepository.existsById(id);
	}
}
