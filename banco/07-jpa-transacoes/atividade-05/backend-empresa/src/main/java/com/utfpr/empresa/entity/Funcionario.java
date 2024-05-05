package com.utfpr.empresa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@NamedQuery(name = "Funcionario.findByQuantidadeDependentes", query = "SELECT f FROM Funcionario f WHERE SIZE(f.dependentes) = :quantidadeDependentes")
@NamedNativeQuery(name = "Funcionario.findByPartialName", query = "SELECT * FROM Funcionario WHERE nome LIKE :partialName", resultClass = Funcionario.class)
@Table(name = "funcionarios")
@Data
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_funcionario")
	private int codigo;

	@Column(name = "nome_funcionario")
	private String nome;

	@Column(name = "qtd_dependentes")
	private int dependentes;

	@Column(name = "salario_funcionario")
	private double salario;

	@Column(name = "cargo_funcionario")
	private String cargo;

	@OneToOne
	@JoinColumn(name = "codigo_departamento", nullable = false)
	private int codigoDepartamento;
}
