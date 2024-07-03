package br.edu.utfpr.cp.espjava.crudcidades.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByName(String name);
} 
