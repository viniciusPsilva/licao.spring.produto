package com.licao.spring.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.licao.spring.produto.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
