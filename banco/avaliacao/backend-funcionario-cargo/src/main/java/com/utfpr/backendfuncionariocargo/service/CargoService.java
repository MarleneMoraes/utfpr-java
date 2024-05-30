package com.utfpr.backendfuncionariocargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.backendfuncionariocargo.entity.Cargo;
import com.utfpr.backendfuncionariocargo.repository.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository repository;
	
	public Cargo saveCargo(Cargo cargo) {
		return repository.save(cargo);
	}
	
	public List<Cargo> listAllCargo() {
		return repository.findAll();
	}
	
	public void deleteCargo(Integer id) {
		try {
	        repository.deleteById(id);
	    } catch (EmptyResultDataAccessException e) {
	        e.getMessage();
	    }
	}

}
