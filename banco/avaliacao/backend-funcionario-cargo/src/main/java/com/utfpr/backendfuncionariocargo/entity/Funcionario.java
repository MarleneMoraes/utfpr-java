package com.utfpr.backendfuncionariocargo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="funcionario")
@Data
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_funcionario")
	private Integer id;
	
	@Column(name = "nome", length = 70)
	private String nome;
	
	@Column(name = "sexo", length = 10)
	private String sexo;
	
	@Column(name = "telefone", length = 20)
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "cod_cargo", nullable = false)
	private Cargo cargo;

	public Funcionario(String nome, String sexo, String telefone, Cargo cargo) {
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.cargo = cargo;
	}
}
