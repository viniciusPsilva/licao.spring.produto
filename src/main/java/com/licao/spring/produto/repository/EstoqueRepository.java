package com.licao.spring.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.licao.spring.produto.model.Estoque;

public interface EstoqueRepository extends CrudRepository<Estoque, Integer> {

}