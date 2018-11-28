package com.licao.spring.produto.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.licao.spring.produto.model.Produto;

public interface ProdutoService {
	
	/**
	 * lista todos os produtos na base de dados
	 * @return Iterable<Produto>
	 */
	public Iterable<Produto> listar();
	
	/**
	 * Busca um produto pelo id no banco de dados 
	 * @param id
	 * @return Optional<Produto>
	 */
	public Optional<Produto> buscar(Integer id);
	
	/**
	 * Cadastra ou altera um  produto no banco de dados
	 * @param produto
	 * @return Produto
	 * @throws Exception
	 */
	public Produto persistir(@RequestBody Produto produto) throws Exception;
	
	/**
	 * Deleta um produto do banco de dados pelo seu id
	 * @param id
	 */
	public void deletar(Integer id);
	
	/**
	 * verifica se um produto existe no banco de dados pelo seu id
	 * @param id
	 * @return boolean
	 */
	public boolean existe(Integer id);
	
}
