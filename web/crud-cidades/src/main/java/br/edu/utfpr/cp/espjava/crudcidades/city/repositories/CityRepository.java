package br.edu.utfpr.cp.espjava.crudcidades.city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.cp.espjava.crudcidades.city.entities.CityEntity;

import java.util.Optional;


public interface CityRepository extends JpaRepository<CityEntity, Long>{
    public Optional<CityEntity> findByNameAndState(String name, String state);
}
