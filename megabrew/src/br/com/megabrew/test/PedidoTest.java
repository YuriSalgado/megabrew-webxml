package br.com.megabrew.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.megabrew.bean.Pedido;
import br.com.megabrew.bean.PedidoItem;
import br.com.megabrew.bean.Produto;
import br.com.megabrew.dao.PedidoDao;
import br.com.megabrew.dao.ProdutoDao;
import br.com.megabrew.dao.UsuarioDao;

public class PedidoTest {

	@Test
	public void test() {
		
		ProdutoDao produtoDao = new ProdutoDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		PedidoDao pedidoDao = new PedidoDao();
		
		Produto produto = produtoDao.listAll().get(0);
		
		Pedido pedido = new Pedido();		
		PedidoItem pedidoItem = new PedidoItem();
		
		pedidoItem.setPedido(pedido);
		pedido.setUsuario(usuarioDao.listAll().get(0));
		pedidoItem.setProduto(produto);
		pedidoItem.setQuantidade(2);
		pedidoItem.setValorUnitario(produto.getPreco());
		pedidoItem.setValorTotal(produto.getPreco() * 2);
		
		pedido.getItens().add(pedidoItem);
		
		pedidoDao.openTansaction();
		pedidoDao.save(pedido);
		pedidoDao.commit();
		
		produtoDao.diminuirQuantidade(produto, 2);
		
		Assert.assertNotNull(pedidoItem.getId());
		
	}
	
}