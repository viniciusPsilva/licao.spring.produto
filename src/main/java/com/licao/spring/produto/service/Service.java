package com.licao.spring.produto.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

public interface Service<T> {

	/**
	 * lista todos os produtos na base de dados
	 * @return Iterable<Produto>
	 */
	public Iterable<T> listar();
	
	/**
	 * Busca um produto pelo id no banco de dados 
	 * @param id
	 * @return Optional<Produto>
	 */
	public Optional<T> buscar(Integer id);
	
	/**
	 * Cadastra ou altera um  produto no banco de dados
	 * @param produto
	 * @return Produto
	 * @throws Exception
	 */
	public <S extends T> S persistir(@RequestBody S entity) throws Exception;
	
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
