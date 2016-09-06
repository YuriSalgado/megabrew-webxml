package br.com.megabrew.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.megabrew.bean.Produto;
import br.com.megabrew.dao.ProdutoDao;

public class ListagemProdutosTest {
	
	ProdutoDao produtoDao = new ProdutoDao();

	@Before
	public void setUp() {
		produtoDao.openTansaction();
		
		Produto p = new Produto();
		p.setDescricao("Cerveja 1");
		p.setPreco(8.00);
		p.setQuantidade(100);
		p.setUnidadeMedida("L");
		p.setVolume(4.5);
		
		produtoDao.save(p);
		
		Produto p2 = new Produto();
		p2.setDescricao("Cerveja 2");
		p2.setPreco(12.00);
		p2.setQuantidade(90);
		p2.setUnidadeMedida("L");
		p2.setVolume(7.5);
		
		produtoDao.save(p2);
		
		produtoDao.commit();
	}
	
	@Test
	public void verificarListagem() {
		List<Produto> produtos = produtoDao.listAll();
		Assert.assertTrue(produtos.size() > 0);
	}
	
}