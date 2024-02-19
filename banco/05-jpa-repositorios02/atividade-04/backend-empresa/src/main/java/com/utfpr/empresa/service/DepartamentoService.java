package com.utfpr.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.empresa.entity.Departamento;
import com.utfpr.empresa.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	public List<Departamento> listarTodosDepartamentos() {
		return repository.findAll();
	}
}
