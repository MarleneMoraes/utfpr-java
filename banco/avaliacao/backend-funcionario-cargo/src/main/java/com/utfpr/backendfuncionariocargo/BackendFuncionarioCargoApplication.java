package com.utfpr.backendfuncionariocargo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utfpr.backendfuncionariocargo.entity.Cargo;
import com.utfpr.backendfuncionariocargo.entity.Funcionario;
import com.utfpr.backendfuncionariocargo.service.CargoService;
import com.utfpr.backendfuncionariocargo.service.FuncionarioService;

@SpringBootApplication
public class BackendFuncionarioCargoApplication {

	private static final Logger logger = LoggerFactory.getLogger(BackendFuncionarioCargoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BackendFuncionarioCargoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(CargoService cargoService, FuncionarioService funcionarioService) {
		return (arg) -> {
			
			// Inserir pelo menos 3 Cargos 
			List<Cargo> cargos = new ArrayList<>();

			cargos.add(new Cargo("Gerente"));
			cargos.add(new Cargo("Coordenador"));
			cargos.add(new Cargo("Analista"));
			cargos.add(new Cargo("Diretor"));
			
			for(Cargo cargo : cargos) {
				cargoService.saveCargo(cargo);
			}
			
			// Inserir pelo menos 3 Funcionários (associando aos seus respectivos cargos); 
			List<Funcionario> funcionarios = new ArrayList<>();
			funcionarios.add(new Funcionario("João Silva", "Masculino", "(31) 98765-4321", cargos.get(0)));
			funcionarios.add(new Funcionario("Maria Santos", "Feminino", "(31) 97654-3210", cargos.get(1)));
			funcionarios.add(new Funcionario("Carlos Pereira", "Masculino", "(31) 96543-2109", cargos.get(2)));
			funcionarios.add(new Funcionario("Ana Costa", "Feminino", "(31) 95432-1098", cargos.get(2)));
			funcionarios.add(new Funcionario("Pedro Rocha", "Masculino", "(31) 94321-0987", cargos.get(3)));
			
			for(Funcionario funcionario : funcionarios) {
				funcionarioService.saveFuncionario(funcionario);
			}
			
			// Excluir pelo menos 1 Cargo pelo identificador
	        cargoService.deleteCargo(1);

	        // Excluir pelo menos 1 Funcionário pelo identificador
	        funcionarioService.deleteFuncionario(2);
			
	        // Listar todos os Funcionários
			logger.info("==============================================");
			logger.info("LISTAGEM DE FUNCIONÁRIOS");
			logger.info("==============================================");
			for(Funcionario funcionario : funcionarioService.listAllFuncionario()) {
				logger.info(funcionario.toString());
			}
			
			// Listar todos os Cargos
			logger.info("==============================================");
			logger.info("LISTAGEM DE CARGOS");
			logger.info("==============================================");
			for(Cargo cargo : cargoService.listAllCargo()) {
				logger.info(cargo.toString());
			}
			
			
			// Listar os Funcionários em Ordem Alfabética
			logger.info("==============================================");
			logger.info("LISTAGEM DE FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
			logger.info("==============================================");
			for(Funcionario funcionario : funcionarioService.findAllNomeOrdemCrescente()) {
				logger.info(funcionario.toString());
			}
			
			// Listar a Quantidade de Funcionários
			logger.info("==============================================");
			logger.info("QUANTIDADE DE FUNCIONÁRIOS: " + funcionarioService.qtdFuncionarios());
			logger.info("==============================================");
		};
	}

}
