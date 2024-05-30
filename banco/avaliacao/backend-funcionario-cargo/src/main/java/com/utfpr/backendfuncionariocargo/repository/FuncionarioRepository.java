package com.utfpr.backendfuncionariocargo.repository;

import com.utfpr.backendfuncionariocargo.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
