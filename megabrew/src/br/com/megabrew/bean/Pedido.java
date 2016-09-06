package br.com.megabrew.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.megabrew.service.AdaptadorDate;

@Entity
public class Pedido implements Bean {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pedido", fetch=FetchType.EAGER)
	private List<PedidoItem> itens = new ArrayList<>();
	private double valorTotal;
	@XmlJavaTypeAdapter(AdaptadorDate.class)
	private Date data = new Date();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}
	
	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
