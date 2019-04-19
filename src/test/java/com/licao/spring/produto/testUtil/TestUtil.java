package com.licao.spring.produto.testUtil;

import java.math.BigDecimal;

import com.licao.spring.Entidades.models.Produto;

public class TestUtil {

	public static Produto getDefaultProdutoForTest() {
		return Produto.builder()
				.name("Produto teste")
				.descricao("Descricao Teste")
				.disponivel(true)
				.valor(new BigDecimal(200.00))
				.build();

	}

}
