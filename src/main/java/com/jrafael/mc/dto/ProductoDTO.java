package com.jrafael.mc.dto;

import java.io.Serializable;

import com.jrafael.mc.domain.Producto;

public class ProductoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;
	
	public ProductoDTO() {
		
	}
	
	public ProductoDTO(Producto producto) {
		this.id = producto.getId();
		this.nome = producto.getNome();
		this.preco = producto.getPreco();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
