package com.licao.spring.produto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licao.spring.produto.model.Produto;
import com.licao.spring.produto.repository.ProdutoRepository;
import com.licao.spring.produto.service.ProdutoService;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Iterable<Produto> listar() {
		return produtoRepository.findAll();
	}

	@Override
	public Optional<Produto> buscar(Integer id) {
		return produtoRepository.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Produto persistir(Produto produto) throws Exception {
		return produtoRepository.save(produto);
	}

	@Override
	public void deletar(Integer id) {
		produtoRepository.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		return produtoRepository.existsById(id);
	}
}
