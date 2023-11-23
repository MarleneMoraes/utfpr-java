package br.edu.utfpr.javai.atividadevi.java.main;

import java.io.IOException;

import br.edu.utfpr.javai.atividadeiv.java.entities.Carga;
import br.edu.utfpr.javai.atividadeiv.java.entities.Passeio;
import br.edu.utfpr.javai.atividadeiv.java.utils.Leitura;
import br.edu.utfpr.javai.atividadevi.java.databases.BDVeiculos;
import br.edu.utfpr.javai.atividadevi.java.exception.VeicExistException;
import br.edu.utfpr.javai.atividadevi.java.exception.VelocException;

/**
 * @author Marlene Moraes
 * @date 2023-11-11
 * 
 *       Activity 006
 */
public class Teste {

	public static void main(String[] args) throws VeicExistException, VelocException {
		Leitura scan = new Leitura();
		BDVeiculos database = new BDVeiculos();

		try {
			menuInicial(scan, database);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Show menu in while looping for
	public static void menuInicial(Leitura scan, BDVeiculos database) throws IOException, VeicExistException, VelocException {

		int input = 0;

		while (input != 7) {
			System.out.println("============================================\n");
			System.out.println("Sistema de Gestão de Veículos - Menu Inicial\n");
			System.out.println("  1. Cadastrar Veículo de Passeio");
			System.out.println("  2. Cadastrar Veículo de Carga");
			System.out.println("  3. Imprimir Todos os Veículos de Passeio");
			System.out.println("  4. Imprimir Todos os Veículos de Carga");
			System.out.println("  5. Imprimir Veículo de Passeio pela Placa");
			System.out.println("  6. Imprimir Veículo de Carga pela Placa");
			System.out.println("  7. Sair do Sistema");

			System.out.println("\n\n============================================");

			try {
				input = Integer.parseInt(scan.entDados("Digite uma das opções acima: "));
				opcoesMenu(scan, input, database);
			} catch (NumberFormatException e) {
				System.out.println("A opção escolhida deve ser um número inteiro.");
				scan.entDados("");
				continue;
			}
		}

	}

	// Execute the options methods
	public static void opcoesMenu(Leitura scan, int opcao, BDVeiculos database) throws VeicExistException, VelocException {
		Passeio[] veiculosPasseio = database.getListaPasseio();
		Carga[] veiculosCarga = database.getListaCarga();

		switch (opcao) {
		case 1:
			database.setListaPasseio(cadastraVeiculoPasseio(scan, veiculosPasseio));
			break;
		case 2:
			database.setListaCarga(cadastraVeiculoCarga(scan, veiculosCarga));
			break;
		case 3:
			imprimeVeiculos(veiculosPasseio);
			break;
		case 4:
			imprimeVeiculos(veiculosCarga);
			break;
		case 5:
			imprimeVeiculosPasseioPelaPlaca(veiculosPasseio, scan);
			break;
		case 6:
			imprimeVeiculosCargaPelaPlaca(veiculosCarga, scan);
			break;
		case 7:
			break;
		default:
			System.out.println("Opção inválida. Escolha uma opção de 1 a 7.");
		}

	}

	// Option 1
	public static Passeio[] cadastraVeiculoPasseio(Leitura scan, Passeio[] veiculosPasseio) throws VeicExistException, VelocException {
		String input = "";

		do {
			System.out.println("============= CADASTRO DE VEICULO PASSEIO =============");
			System.out.println("Insira os dados e valores solicitados abaixo: ");
			System.out.println("=======================================================");

			try {
				String inputPlaca = scan.entDados("Placa: ");
				Passeio veiculo = buscaVeiculosPasseio(veiculosPasseio, inputPlaca);

				if (veiculo == null) {
					veiculo = new Passeio();

					veiculo.setPlaca(inputPlaca);
					veiculo.setMarca(scan.entDados("Marca: "));
					veiculo.setModelo(scan.entDados("Modelo: "));
					veiculo.setCor(scan.entDados("Cor: "));
					veiculo.setQtdRodas(Integer.parseInt(scan.entDados("Quantidade de Rodas: ")));
					veiculo.setQtdPassageiros(Integer.parseInt(scan.entDados("Capacidade de Passageiros: ")));
					try {
						veiculo.setVelocMax(Integer.parseInt(scan.entDados("Velocidade Máxima: ")));
						
						if (veiculo.getVelocMax() > 80 || veiculo.getVelocMax() < 110) {
							throw new VelocException();
						} 
						
					} finally {
						veiculo.setVelocMax(100);
						
						veiculo.getMotor().setPotencia(Integer.parseInt(scan.entDados("Potência do Motor: ")));
						veiculo.getMotor().setQtdPist(Integer.parseInt(scan.entDados("Quantidade de Pistões do Motor: ")));
						
						for (int i = 0; i < veiculosPasseio.length; i++) {
							if (veiculosPasseio[i] == null) {
								veiculosPasseio[i] = veiculo;
								break;
							}
						}
					}
										

				} else {
					throw new VeicExistException();
				}

				input = scan.entDados("Deseja adicionar mais veículos de passeio? (SIM/NAO)");

				if (input.equals("SIM") && isCheio(veiculosPasseio)) {
					System.out.println("\nA lista de veículos de passeio está cheia.\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (input.equals("SIM") && !isCheio(veiculosPasseio));

		return veiculosPasseio;
	}

	// Option 2
	public static Carga[] cadastraVeiculoCarga(Leitura scan, Carga[] veiculosCarga) throws VeicExistException, VelocException {
		String input = "";

		do {

			System.out.println("============= CADASTRO DE VEICULO CARGA =============");
			System.out.println("Insira os dados e valores solicitados abaixo: ");
			System.out.println("=======================================================");

			try {
				String inputPlaca = scan.entDados("Placa: ");
				Carga veiculo = buscaVeiculosCarga(veiculosCarga, inputPlaca);

				if (veiculo == null) {
					veiculo = new Carga();

					veiculo.setPlaca(inputPlaca);
					veiculo.setMarca(scan.entDados("Marca: "));
					veiculo.setModelo(scan.entDados("Modelo: "));
					veiculo.setCor(scan.entDados("Cor: "));
					veiculo.setVelocMax(Integer.parseInt(scan.entDados("Velocidade Máxima: ")));
					
					if (veiculo.getVelocMax() > 80 || veiculo.getVelocMax() < 110) {
						throw new VelocException();
					} else {
						veiculo.setVelocMax(90);
					}
					
					veiculo.setQtdRodas(Integer.parseInt(scan.entDados("Quantidade de Rodas: ")));
					veiculo.getMotor().setPotencia(Integer.parseInt(scan.entDados("Potência do Motor: ")));
					veiculo.getMotor().setQtdPist(Integer.parseInt(scan.entDados("Quantidade de Pistões do Motor: ")));
					veiculo.setCargaMax(Integer.parseInt(scan.entDados("Carga Máxima: ")));
					veiculo.setTara(Integer.parseInt(scan.entDados("Quantidade de Tara: ")));

					for (int i = 0; i < veiculosCarga.length; i++) {
						if (veiculosCarga[i] == null) {
							veiculosCarga[i] = veiculo;
							break;
						}
					}
					
					
				} else {
					throw new VeicExistException();
				}
				
				input = scan.entDados("Deseja adicionar mais veículos de carga? (SIM/NAO)");

				if (input.equals("SIM") && isCheio(veiculosCarga)) {
					System.out.println("\nA lista de veículos de carga está cheia.\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (input.equals("SIM") && !isCheio(veiculosCarga));

		return veiculosCarga;
	}

	// Option 3 and 4
	public static <T> void imprimeVeiculos(T[] veiculos) {
		if (veiculos[0] instanceof Passeio) {
			System.out.println("============= VEICULOS PASSEIO =============");
		} else if (veiculos[0] instanceof Carga) {
			System.out.println("============= VEICULOS CARGA =============");
		}

		for (T veiculo : veiculos) {
			if (veiculo != null) {
				System.out.println(veiculo.toString());
				System.out.println("===========================================");
			}
		}
	}

	// Option 5
	public static void imprimeVeiculosPasseioPelaPlaca(Passeio[] veiculosPasseio, Leitura scan) {
		String inputPlaca;
		try {
			inputPlaca = scan.entDados("Placa: ");
			Passeio veiculo = buscaVeiculosPasseio(veiculosPasseio, inputPlaca);

			if (veiculo != null) {
				System.out.println("============= VEICULO PASSEIO =============");
				System.out.println(veiculo.toString());
				System.out.println("===========================================");
			} else {
				System.out.println("Veículo com placa " + inputPlaca + " não foi encontrado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Option 6
	public static void imprimeVeiculosCargaPelaPlaca(Carga[] veiculosCarga, Leitura scan) {

		String inputPlaca;
		try {
			inputPlaca = scan.entDados("Placa: ");
			Carga veiculo = buscaVeiculosCarga(veiculosCarga, inputPlaca);

			if (veiculo != null) {
				System.out.println("============= VEICULO CARGA =============");
				System.out.println(veiculo.toString());
				System.out.println("===========================================");
			} else {
				System.out.println("Veículo com placa " + inputPlaca + " não foi encontrado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static <T> boolean isCheio(T[] lista) {
		for (T veiculo : lista) {
			if (veiculo == null) {
				return false;
			}
		}
		return true;
	}

	// Search Methods vehicles in vector
	private static Passeio buscaVeiculosPasseio(Passeio[] veiculosPasseio, String input) {
		for (int i = 0; i < veiculosPasseio.length; i++) {
			if (veiculosPasseio[i] == null) { // Verify the list is empty
				break;
			} else if (veiculosPasseio[i].getPlaca().equals(input)) {
				veiculosPasseio[i].setPlaca(input);
				return veiculosPasseio[i];
			}
		}
		return null;
	}

	private static Carga buscaVeiculosCarga(Carga[] veiculosCarga, String input) {
		for (int i = 0; i < veiculosCarga.length; i++) {
			if (veiculosCarga[i] == null) { // Verify the list is empty
				break;
			} else if (veiculosCarga[i].getPlaca().equals(input)) {
				veiculosCarga[i].setPlaca(input);
				return veiculosCarga[i];
			}
		}
		return null;
	}

}
