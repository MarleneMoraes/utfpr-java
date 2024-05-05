package com.utfpr.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utfpr.empresa.entity.Departamento;
import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.service.DepartamentoService;
import com.utfpr.empresa.service.FuncionarioService;

@SpringBootApplication
public class BackendEmpresaApplication {

	private static final Logger logger = LoggerFactory.getLogger(BackendEmpresaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendEmpresaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FuncionarioService funcionarioService, DepartamentoService departamentoService) {
		return (arg) -> {
			logger.info("LISTAGEM DE TODOS OS FUNCIONARIOS");
			
			for(Funcionario funcionario : funcionarioService.listarTodosFuncionarios()) {
				logger.info(funcionario.toString());
			}
			
			logger.info("===================================");
			logger.info("LISTAGEM DE TODOS OS DEPARTAMENTOS");
			
			for(Departamento departamento : departamentoService.listarTodosDepartamentos()) {
				logger.info(departamento.toString());
			}
		};
	}

}
