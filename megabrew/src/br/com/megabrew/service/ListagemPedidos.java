package br.com.megabrew.service;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import br.com.megabrew.bean.Pedido;
import br.com.megabrew.bean.PedidoItem;
import br.com.megabrew.bean.Produto;
import br.com.megabrew.bean.Usuario;
import br.com.megabrew.dao.PedidoDao;
import br.com.megabrew.dao.PedidoItemDao;
import br.com.megabrew.dao.ProdutoDao;
import br.com.megabrew.dao.UsuarioDao;

@WebService
public class ListagemPedidos {

	public void criarPedido(@WebParam(name="produto_quantidade") HashMap<Integer, Double> produtosQuantidades, 
			@WebParam(name="usuario", header=true) Usuario usuario) throws UsuarioNaoAutorizadoException {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		usuario = usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha());
		
		if (usuario != null) {
			ProdutoDao produtoDao = new ProdutoDao();
			PedidoItemDao pedidoItemDao = new PedidoItemDao();		
			
			Produto produto;
			PedidoItem pedidoItem;
			
			Pedido pedido = new Pedido();		
			pedido.setUsuario(usuario);			
			
			for (Entry<Integer, Double> entry : produtosQuantidades.entrySet()) {
				pedidoItem = new PedidoItem();
				
				pedidoItem.setPedido(pedido);				
				produto = produtoDao.findById(entry.getKey());
				pedidoItem.setProduto(produto);
				pedidoItem.setQuantidade(entry.getValue());
				pedidoItem.setValorUnitario(produto.getPreco());
				pedidoItem.setValorTotal(produto.getPreco() * entry.getValue());
				
				pedidoItemDao.openTansaction();
				pedidoItemDao.save(pedidoItem);
				pedidoItemDao.commit();
				
				produtoDao.diminuirQuantidade(produto, entry.getValue());
			}
		} else {
			throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
		}
	}
	
	public void pesquisarPedidosPorUsuario(@WebParam(name="usuario", header=true) Usuario usuario) throws UsuarioNaoAutorizadoException {
		UsuarioDao usuarioDao = new UsuarioDao();
		usuario = usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha());
		
		if (usuario != null) {
			PedidoDao pedidoDao = new PedidoDao();
			pedidoDao.obterPedidosUsuario(usuario);
		} else {
			throw new UsuarioNaoAutorizadoException("Não autorizado: "+usuario.getSenha()+ " - "+usuario.getLogin());
		}
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8180/megabrew/pedidos", new ListagemProdutos());
		System.out.println("Subiu pedidos!");
	}
}