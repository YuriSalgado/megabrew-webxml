package br.com.megabrew.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.megabrew.bean.Usuario;
import br.com.megabrew.dao.UsuarioDao;

public class UsuarioTest {

	@Test
	public void salvar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		
		usuario.setNome("Yuri");
		usuario.setLogin("yuri");
		usuario.setSenha("123");
		usuario.setTelefone("(47) 8900-4763");
		
		usuarioDao.openTansaction();
		usuarioDao.save(usuario);
		usuarioDao.commit();
		
		Assert.assertNotNull(usuario.getId());
	}
	
}