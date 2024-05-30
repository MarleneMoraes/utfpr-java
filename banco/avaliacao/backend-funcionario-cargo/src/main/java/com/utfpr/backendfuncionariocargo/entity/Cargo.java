package com.utfpr.backendfuncionariocargo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cargo")
@Data
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_cargo", nullable = false)
	private Integer id;
	
	@Column(name="cargo", length = 50)
	private String cargo;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public Cargo(String cargo) {
        this.cargo = cargo;
    }
	
}
