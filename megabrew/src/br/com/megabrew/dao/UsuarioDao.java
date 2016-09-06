package br.com.megabrew.dao;

import javax.persistence.Query;

import br.com.megabrew.bean.Usuario;
import br.com.megabrew.service.UsuarioNaoAutorizadoException;

public class UsuarioDao extends JpaDaoBase<Usuario>{
	
	public Usuario autenticar(String login, String senha) throws UsuarioNaoAutorizadoException {
		Usuario usuario = null;
		
		if (login == null || senha == null || login.equals("") || senha.equals("")) {
			throw new UsuarioNaoAutorizadoException("Login ou senha não informados. Enviados (U:"+login+" P:"+senha+")");
		} else {
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE login = :login AND senha = :senha");

			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			if (query.getResultList().size() > 0) {
				usuario = (Usuario) query.getSingleResult();
			}
		}		
		
		return usuario;
	}
	
}