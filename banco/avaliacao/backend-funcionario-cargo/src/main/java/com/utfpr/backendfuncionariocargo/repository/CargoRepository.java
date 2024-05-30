package com.utfpr.backendfuncionariocargo.repository;

import com.utfpr.backendfuncionariocargo.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
