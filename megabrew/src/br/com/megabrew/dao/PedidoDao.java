package br.com.megabrew.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.megabrew.bean.Pedido;
import br.com.megabrew.bean.Usuario;

public class PedidoDao extends JpaDaoBase<Pedido>{

	@SuppressWarnings("unchecked")
	public List<Pedido> obterPedidosUsuario(Usuario usuario) {
		List<Pedido> pedidos = null;
		
		Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.usuario.id = :usuario");
		query.setParameter("usuario", usuario.getId());
		
		if (query.getResultList().size() > 0) {
			pedidos = (List<Pedido>) query.getResultList();
		}
		
		return pedidos;
	}
	
}