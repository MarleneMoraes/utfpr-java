package com.utfpr.empresa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utfpr.empresa.entity.Departamento;
import com.utfpr.empresa.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	List<Funcionario> findByQuantidadeDependentes(int quantidadeDependentes);

	List<Funcionario> findByPartialName(@Param("partialName") String partialName);

	// Listar um funcionário pelo seu nome e quantidade de dependentes utilizando
	// consulta
	List<Funcionario> procurarNomeEDependentes(String nome, int quantidadeDependentes);

	// Listar todos os funcionários de um determinado departamento por JPQL via
	// @Query
	@Query("SELECT f FROM Funcionario f WHERE f.departamento.nome = :nomeDepartamento")
	List<Funcionario> procurarPorDepartamentoNome(@Param("nomeDepartamento") String nomeDepartamento);

	// Listar o primeiro departamento cadastrado
	@Query("SELECT d FROM Departamento d ORDER BY d.id ASC")
	Departamento procurarPrimeiroDepartamento();

	// Listar o primeiro funcionário que tem o maior salário
	@Query("SELECT f FROM Funcionario f WHERE f.salario = (SELECT MAX(f2.salario) FROM Funcionario f2)")
	Funcionario procurarPrimeiroPorMaiorSalario();

	// Listar os 3 (três) primeiros funcionários que têm os maiores salários
	@Query("SELECT f FROM Funcionario f ORDER BY f.salario DESC")
	List<Funcionario> procurarTop3DeMaioresSalarios(Pageable pageable);

	// Listar os funcionários que não têm dependentes em ordem crescente de nome por
	// JPQL via @Query
	@Query("SELECT f FROM Funcionario f WHERE f.dependentes IS EMPTY ORDER BY f.nome ASC")
	List<Funcionario> procurarSemDependentesPorNome();

	// Listar os funcionários que têm salário maior que um determinado valor por
	// JPQL sobrescrevendo palavras-chaves @Query
	@Query("SELECT f FROM Funcionario f WHERE f.salario > :valorSalario")
	List<Funcionario> procurarPorSalarioMaior(@Param("valorSalario") BigDecimal valorSalario);

	// Listar os funcionários que têm salário maior que um determinado valor por
	// @Query com native query
	@Query(value = "SELECT * FROM Funcionario WHERE salario > :valorSalario", nativeQuery = true)
	List<Funcionario> procurarPorSalarioMaiorComNative(@Param("valorSalario") BigDecimal valorSalario);

}
