package com.utfpr.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utfpr.empresa.entity.Departamento;
import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.repository.DepartamentoRepository;
import com.utfpr.empresa.repository.FuncionarioRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Departamento> listarTodosDepartamentos() {
		return departamentoRepository.findAll();
	}

	// Criar um metodo na classe de servico de departamento para salvar um
	// departamento, associar esse departamento a um funcionario e salvar esse
	// funcionario em um mesmo controle de transacao(@Transactional).
	@Transactional
	public void salvarDados(Departamento departmento, Funcionario funcionario) {
		departamentoRepository.save(departmento);
		funcionarioRepository.save(funcionario);
	}
}
