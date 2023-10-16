package br.edu.utfpr.javai.atividadeiii.java.main;

import br.edu.utfpr.javai.atividadeiii.java.entities.Carga;
import br.edu.utfpr.javai.atividadeiii.java.entities.Motor;
import br.edu.utfpr.javai.atividadeiii.java.entities.Passeio;

/**
 * @author Marlene Moraes
 * @date 2023-10-12
 * 
 *       Activity 003
 */
public class Teste {

	public static void main(String[] args) {
		// Create vehicle vector
		Passeio[] veiculosPasseio = new Passeio[5];
		Carga[] veiculosCarga = new Carga[5];

		// Setters Method Test
		veiculosPasseio[0] = new Passeio();
		veiculosPasseio[0].setPlaca("ABC1X94");
		veiculosPasseio[0].setMarca("BMW");
		veiculosPasseio[0].setModelo("iX M60");
		veiculosPasseio[0].setCor("Branco");
		veiculosPasseio[0].setVelocMax(250);
		veiculosPasseio[0].setQtdRodas(4);
		veiculosPasseio[0].getMotor().setPotencia(250);
		veiculosPasseio[0].getMotor().setQtdPist(6);
		veiculosPasseio[0].setQtdPassageiros(5);

		veiculosPasseio[1] = new Passeio("RIO2X23", "Renault", "Logan", "Preto", 164, 4, new Motor(4, 150), 5);
		veiculosPasseio[2] = new Passeio("BHZ2X20", "Toyota", "Corolla", "Prata", 200, 4, new Motor(6, 200), 5);
		veiculosPasseio[3] = new Passeio("SAO2X24", "Ford", "Mustang", "Vermelho", 250, 4, new Motor(4, 180), 5);
		veiculosPasseio[4] = new Passeio("CAN2X35", "Honda", "Civic", "Azul", 180, 4, new Motor(6, 220), 5);

		// Setters Method Test
		veiculosCarga[0] = new Carga();
		veiculosCarga[0].setPlaca("JKL3A45");
		veiculosCarga[0].setMarca("Nissan");
		veiculosCarga[0].setModelo("Frontier");
		veiculosCarga[0].setCor("Cinza");
		veiculosCarga[0].setVelocMax(155);
		veiculosCarga[0].setQtdRodas(4);
		veiculosCarga[0].setMotor(new Motor(6, 220));
		veiculosCarga[0].setCargaMax(1000);
		veiculosCarga[0].setTara(550);

		veiculosCarga[1] = new Carga("BSB2X23", "Ford", "F-150", "Branco", 160, 4, new Motor(4, 200), 1000, 500);
		veiculosCarga[2] = new Carga("POA2X20", "Chevrolet", "Silverado", "Prata", 170, 4, new Motor(6, 220), 1200,
				600);
		veiculosCarga[3] = new Carga("BEL2X24", "Dodge", "Ram 1500", "Vermelho", 155, 4, new Motor(6, 250), 1100, 550);
		veiculosCarga[4] = new Carga("ESP2X35", "Toyota", "Tacoma", "Azul", 165, 4, new Motor(4, 180), 1050, 500);

		// Print all the vehicles informations
		System.out.println("============= VEICULO PASSEIO =============");
		for (Passeio veiculo : veiculosPasseio) {
			System.out.println(veiculo.toString());
			System.out.println("===========================================");
		}

		System.out.println("\n================ VEICULO CARGA ================");
		for (Carga veiculo : veiculosCarga) {
			System.out.println(veiculo.toString());
			System.out.println("===========================================");
		}
	}
}
