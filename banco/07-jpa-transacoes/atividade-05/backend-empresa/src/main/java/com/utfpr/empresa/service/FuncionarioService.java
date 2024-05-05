package com.utfpr.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> listarTodosFuncionarios() {
		return repository.findAll();
	}
	
	public void procAumentaSalario(Integer i) {
		repository.procAumentaSalario(i);
	}
}
