package br.com.megabrew.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto implements Bean {

	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	private String unidadeMedida;
	private double volume;
	private double quantidade;
	private double preco;

	public Produto() {
		super();
	}

	public Produto(Integer id, String descricao, String unidadeMedida,	double volume, double quantidade, double preco) {
		this.id = id;
		this.descricao = descricao;
		this.unidadeMedida = unidadeMedida;
		this.volume = volume;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}