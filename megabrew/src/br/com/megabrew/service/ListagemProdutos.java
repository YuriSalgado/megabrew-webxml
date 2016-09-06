package br.com.megabrew.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import br.com.megabrew.bean.Pedido;
import br.com.megabrew.bean.PedidoItem;
import br.com.megabrew.bean.Produto;
import br.com.megabrew.bean.Usuario;
import br.com.megabrew.dao.PedidoDao;
import br.com.megabrew.dao.ProdutoDao;
import br.com.megabrew.dao.UsuarioDao;

@WebService
public class ListagemProdutos {

	public List<Produto> listagemProdutos(@WebParam(name="usuario", header=true) Usuario usuario) throws UsuarioNaoAutorizadoException{
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Produto> produtos = null;
		
		if (usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha()) != null) {
			ProdutoDao produtoDao = new ProdutoDao();
			produtos = produtoDao.obterProdutos();
		} else {
			throw new UsuarioNaoAutorizadoException("Usu·rio n„o autorizado");
		}
		
		return produtos; 
	}
	
	public Produto pesquisarProdutoId(@WebParam(name="usuario", header=true) Usuario usuario,
			@WebParam(name="id") Integer id) throws UsuarioNaoAutorizadoException{
		UsuarioDao usuarioDao = new UsuarioDao();
		Produto produto = null;
		
		if (usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha()) != null) {
			ProdutoDao produtoDao = new ProdutoDao();
			produto = produtoDao.findById(id);
		} else {
			throw new UsuarioNaoAutorizadoException("Usu√°rio n√£o autorizado");
		}
		
		return produto; 
	}
	
	public List<Produto> pesquisarProdutoDescricao(@WebParam(name="usuario", header=true) Usuario usuario,
			@WebParam(name="descricao") String descricao) throws UsuarioNaoAutorizadoException{
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Produto> produto = null;
		
		if (usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha()) != null) {
			ProdutoDao produtoDao = new ProdutoDao();
			produto = produtoDao.obterProdutoDescricao(descricao);
		} else {
			throw new UsuarioNaoAutorizadoException("Usu·rio n„o autorizado");
		}
		
		return produto; 
	}
	
	public String criarPedido(@WebParam(name="produtosQuantidades") ArrayList<Produto> produtosQuantidades, 
			@WebParam(name="usuario", header=true) Usuario usuario) throws UsuarioNaoAutorizadoException {
		
		String retorno = "N„o gravou";
		UsuarioDao usuarioDao = new UsuarioDao();
		usuario = usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha());
		
		if (usuario != null) {
			ProdutoDao produtoDao = new ProdutoDao();
			PedidoDao pedidoDao = new PedidoDao();
			
			Produto produto;
			PedidoItem pedidoItem;
			
			Pedido pedido = new Pedido();		
			pedido.setUsuario(usuario);			
			
			for (Produto pq : produtosQuantidades) {
				produto = produtoDao.findById(pq.getId());
				
				pedidoItem = new PedidoItem();
				
				pedidoItem.setPedido(pedido);				
				pedidoItem.setProduto(produto);
				pedidoItem.setQuantidade(pq.getQuantidade());
				pedidoItem.setValorUnitario(produto.getPreco());
				pedidoItem.setValorTotal(produto.getPreco() * pq.getQuantidade());
				
				pedido.getItens().add(pedidoItem);
			}
			
			pedido.setValorTotal(this.calcularValorItens(pedido.getItens()));
			
			pedidoDao.openTansaction();
			pedidoDao.save(pedido);
			pedidoDao.commit();
			
			retorno = "Gravado!";
		} else {
			throw new UsuarioNaoAutorizadoException("Usuario n„o autorizado");
		}
		
		return retorno;
	}
	
	public double calcularValorItens(List<PedidoItem> itens) {
		double valor = 0;
		
		for (PedidoItem pedidoItem : itens) {
			valor += (pedidoItem.getProduto().getPreco() * pedidoItem.getQuantidade());
		}
		
		return valor;
	}
	
	public List<Pedido> pesquisarPedidosUsuario(@WebParam(name="usuario", header=true) Usuario usuario) throws UsuarioNaoAutorizadoException{
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Pedido> pedidos = null;
		usuario = usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha());
		
		if (usuario != null) {
			PedidoDao pedidoDao = new PedidoDao();
			pedidos = pedidoDao.obterPedidosUsuario(usuario);
		} else {
			throw new UsuarioNaoAutorizadoException("Usuario n„o autorizado");
		}
		
		return pedidos; 
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8180/megabrew/cervejas", new ListagemProdutos());
		System.out.println("Subiu cervejas!");
	}
	
}