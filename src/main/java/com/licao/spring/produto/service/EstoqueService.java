package com.licao.spring.produto.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.licao.spring.produto.model.Estoque;

public interface EstoqueService {

	/**
	 * Lista todos os Estoques presentes no Banco de dados
	 * @return Iterable<Estoque>
	 */
	public Iterable<Estoque> listar();
	
	/**
	 * Busca um estoque especifico com base em seu id
	 * @param Integer id
	 * @return Optional<Estoque>
	 */
	public Optional<Estoque> buscar(Integer id);
	
	/**
	 * Cadastra ou altera um estoque no banco de dados
	 * @param estoque
	 * @return Estoque.class
	 * @throws Exception
	 */
	public Estoque persistir(@RequestBody Estoque estoque) throws Exception;
	
	/**
	 * Deleta um estoque do banco de dados pelo seu id
	 * @param Integer id
	 */
	public void deletar(Integer id);
	
	/**
	 * verifica se um produto existe no banco de dados pelo seu id
	 * @param id
	 * @return boolean
	 */
	public boolean existe(Integer id);
	
}
