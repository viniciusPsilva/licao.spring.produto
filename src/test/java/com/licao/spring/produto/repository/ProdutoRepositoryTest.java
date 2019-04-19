package com.licao.spring.produto.repository;

import static com.licao.spring.produto.testUtil.TestUtil.getDefaultProdutoForTest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.licao.spring.Entidades.models.Produto;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProdutoRepositoryTest {

	private static final int ID_PRODUTO_ESPERADO = 1;
	private static final int ID_PRODUTO_NAO_ENCONTRADO = 2;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void quandoBuscarPorId_RetornaUmProduto() {

		// cenário
		Produto produto = getDefaultProdutoForTest();
		entityManager.persist(produto); // salva as informações no banco de dados em memória
		entityManager.flush(); // sincroniza as informações

		// ação
		Optional<Produto> OptionalProduto = produtoRepository.findById(ID_PRODUTO_ESPERADO);
		Produto produtoEncontrado = OptionalProduto.get();

		// verificação
		assertThat(produto.getName(), is(produtoEncontrado.getName()));
		assertThat(produtoEncontrado.getId(), is(ID_PRODUTO_ESPERADO));
	}

	@Test
	public void quandoBuscarPorIdInexistente_RetornarUmOptionalEmpty() {
		Optional<Produto> produtoOptional = produtoRepository.findById(ID_PRODUTO_NAO_ENCONTRADO);
		assertEquals(Optional.empty(), produtoOptional);
	}

	@Test
	public void quandoAtualizarUmProduto_AtualizarSeusDados() {

		// cenário
		entityManager.persist(getDefaultProdutoForTest());
		entityManager.flush();

		Produto produtoParaAtualizar = getDefaultProdutoForTest();
		produtoParaAtualizar.setId(ID_PRODUTO_ESPERADO);
		produtoParaAtualizar.setName("Nome do produto atualizado pelo teste");
		produtoParaAtualizar.setDescricao("Descricao do produto atualizado pelo teste");
		produtoParaAtualizar.setDisponivel(false);
		produtoParaAtualizar.setValor(new BigDecimal(1000));

		// acão
		Produto produtoAtualizado = produtoRepository.save(produtoParaAtualizar);

		// verificação
		assertThat(produtoAtualizado.getId(), is(produtoParaAtualizar.getId()));
		assertThat(produtoAtualizado.getName(), is(produtoParaAtualizar.getName()));
		assertThat(produtoAtualizado.getDescricao(), is(produtoParaAtualizar.getDescricao()));
		assertThat(produtoAtualizado.isDisponivel(), is(produtoParaAtualizar.isDisponivel()));
		assertThat(produtoAtualizado.getValor(), is(produtoParaAtualizar.getValor()));

	}
}
