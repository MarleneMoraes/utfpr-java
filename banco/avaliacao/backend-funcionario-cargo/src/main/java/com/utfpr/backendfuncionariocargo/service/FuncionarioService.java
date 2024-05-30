package com.utfpr.backendfuncionariocargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.utfpr.backendfuncionariocargo.entity.Funcionario;
import com.utfpr.backendfuncionariocargo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	public Funcionario saveFuncionario(Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	public List<Funcionario> listAllFuncionario() {
		return repository.findAll();
	}
	
	public List<Funcionario> findAllNomeOrdemCrescente() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	public int qtdFuncionarios() {
		return (int) repository.count();
	}
	
	public void deleteFuncionario(Integer id) {
		try {
	        repository.deleteById(id);
	    } catch (EmptyResultDataAccessException e) {
	        e.getMessage();
	    }
	}

}
