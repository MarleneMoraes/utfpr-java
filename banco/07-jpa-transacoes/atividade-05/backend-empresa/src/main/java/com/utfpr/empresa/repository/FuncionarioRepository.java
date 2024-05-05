package com.utfpr.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.utfpr.empresa.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	List<Funcionario> findByQuantidadeDependentes(int quantidadeDependentes);

	List<Funcionario> findByPartialName(@Param("partialName") String partialName);

	List<Funcionario> procurarNomeEDependentes(String nome, int quantidadeDependentes);

	@Procedure("proc_aumenta_salario")
	void procAumentaSalario(Integer i);
	
	
	// Uma instrucao de update que troca todos os funcionários de um determinado
	// departamento para outro departamento utilizando a anotação @Modifying.
	@Modifying
	@Query("UPDATE Funcionarios funcionario SET funcionario.codigo = :novoCodigoDepartamento WHERE funcionario.novoCodigoDepartamento = :antigoCodigoDepartamento")
	void updateFuncionarioDepartamento(@Param("novoCodigoDepartamento") Long codigo,
			@Param("antigoCodigoDepartamento") Long antigoCodigo);
	
	// Uma instrucao de delete que exclui todos os funcionários de um determinado
	// departamento utilizando a anotação @Modifying.
	@Modifying
	@Query("DELETE FROM Funcionario funcionario WHERE funcionario.codigo = :codigo")
	void deleteFuncionariosPorDepartmento(@Param("codigoDepartamento") Long codigo);
	

	/* @Query("SELECT f FROM Funcionario f WHERE f.departamento.nome = :nomeDepartamento")
	List<Funcionario> procurarPorDepartamentoNome(@Param("nomeDepartamento") String nomeDepartamento);

	@Query("SELECT d FROM Departamento d ORDER BY d.id ASC")
	Departamento procurarPrimeiroDepartamento();

	@Query("SELECT f FROM Funcionario f WHERE f.salario = (SELECT MAX(f2.salario) FROM Funcionario f2)")
	Funcionario procurarPrimeiroPorMaiorSalario();

	@Query("SELECT f FROM Funcionario f ORDER BY f.salario DESC")
	List<Funcionario> procurarTop3DeMaioresSalarios(Pageable pageable);

	@Query("SELECT f FROM Funcionario f WHERE f.dependentes IS EMPTY ORDER BY f.nome ASC")
	List<Funcionario> procurarSemDependentesPorNome();

	@Query("SELECT f FROM Funcionario f WHERE f.salario > :valorSalario")
	List<Funcionario> procurarPorSalarioMaior(@Param("valorSalario") BigDecimal valorSalario);

	@Query(value = "SELECT * FROM Funcionario WHERE salario > :valorSalario", nativeQuery = true)
	List<Funcionario> procurarPorSalarioMaiorComNative(@Param("valorSalario") BigDecimal valorSalario); */
	
	

}
