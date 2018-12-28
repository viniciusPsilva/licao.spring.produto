package com.licao.spring.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.licao.spring.Entidades.models.Estoque;
public interface EstoqueRepository extends CrudRepository<Estoque, Integer> {

}