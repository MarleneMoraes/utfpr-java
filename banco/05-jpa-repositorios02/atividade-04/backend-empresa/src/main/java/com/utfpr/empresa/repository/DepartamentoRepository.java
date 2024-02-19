package com.utfpr.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.empresa.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> { }
