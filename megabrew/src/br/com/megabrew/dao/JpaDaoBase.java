package br.com.megabrew.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.megabrew.bean.Bean;

public abstract class JpaDaoBase<T extends Bean> implements Dao<T> {
	
	@PersistenceContext
	protected EntityManager em = JPAUtil.getEntityManager();

	private final Class<T> classe;

	public void openTansaction() {
		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public JpaDaoBase() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		if (t.getId() != null) {
			update(t);
		} else {
			em.persist(t);
		}
	}

	@Override
	public void remove(T t) {
		if (t != null) {
			em.remove(t);
		}
	}

	@Override
	public void update(T t) {
		if (t != null) {
			em.merge(t);
		}
	}

	@Override
	public List<T> listAll() {
		Query query = em
				.createQuery("SELECT a from " + classe.getName() + " a order by a.id ASC");
		@SuppressWarnings("unchecked")
		List<T> lista = query.getResultList();
		return lista;
	}

	@Override
	public T findById(Integer id) {
		return (T) em.find(classe, id);
	}

}