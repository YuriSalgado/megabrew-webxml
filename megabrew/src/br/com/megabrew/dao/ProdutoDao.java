package br.com.megabrew.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.megabrew.bean.Produto;

public class ProdutoDao extends JpaDaoBase<Produto>{
	
	public List<Produto> obterProdutos(){
		ProdutoDao produtoDao = new ProdutoDao();
		return produtoDao.listAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> obterProdutoDescricao(String descricao) {
		List<Produto> produto = null;
		
		Query query = em.createQuery("SELECT p FROM Produto p WHERE descricao LIKE :descricao");
		query.setParameter("descricao", "%" + descricao + "%");
		
		if (query.getResultList().size() > 0) {
			produto = (List<Produto>) query.getResultList();
		}
		
		return produto;
	}
	
	public void diminuirQuantidade(Produto produto, double quantidade) {
		ProdutoDao produtoDao = new ProdutoDao();
		
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		
		produtoDao.openTansaction();
		produtoDao.save(produto);
		produtoDao.commit();
	}

}