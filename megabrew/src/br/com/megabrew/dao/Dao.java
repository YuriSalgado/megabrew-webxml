package br.com.megabrew.dao;

import java.util.List;

public interface Dao<T> {
	
	public void save(T t);
	public void remove(T t);
	public void update(T t);
	public List<T> listAll();
	public T findById(Integer id);
	
}