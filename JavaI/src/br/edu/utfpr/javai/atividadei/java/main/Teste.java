package br.edu.utfpr.javai.atividadei.java.main;

import br.edu.utfpr.javai.atividadei.java.entities.Motor;
import br.edu.utfpr.javai.atividadei.java.entities.Veiculo;

/**
 * @author Marlene Moraes
 * @date 2023-10-01
 * 
 * Activity 001
 */
public class Teste {

	public static void main(String[] args) {
		// Create vehicle vector
		Veiculo[] veiculos = new Veiculo[5];
		
		// Instantiation
		veiculos[0] = new Veiculo();
		veiculos[1] = new Veiculo("RIO2X23", "Renault", "Logan", "Preto",  164, 4, new Motor(4, 150));
		veiculos[2] = new Veiculo("BHZ2X20", "Toyota", "Corolla", "Prata", 200, 4, new Motor(6, 200));
		veiculos[3] = new Veiculo("SAO2X24", "Ford", "Mustang", "Vermelho", 250, 4, new Motor(4, 180));
		veiculos[4] = new Veiculo("CAN2X35", "Honda", "Civic", "Azul", 180, 4, new Motor(6, 220));

		// Print Null Vehicle
		imprimir(veiculos[0]);
		
		// Setters Method Test
		veiculos[0].setPlaca("ABC1X94");
		veiculos[0].setMarca("BMW");
		veiculos[0].setModelo("iX M60");
		veiculos[0].setCor("Branco");
		veiculos[0].setVelocMax(250);
		veiculos[0].setQtdRodas(4);
		veiculos[0].setMotor(new Motor(6,250));
		
		// Print all the vehicles informations
        System.out.println("Informa��es dos Ve�culos:");
        for(Veiculo veiculo : veiculos) {
        	imprimir(veiculo);
        }
    }

    // Print all the vehicles informations Method
    public static void imprimir(Veiculo veiculo) {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("Informa��es do Ve�culo:\n");
    	stringBuilder.append("Placa: ").append(veiculo.getPlaca()).append("\n");
    	stringBuilder.append("Marca: ").append(veiculo.getMarca()).append("\n");
    	stringBuilder.append("Modelo: ").append(veiculo.getModelo()).append("\n");
    	stringBuilder.append("Cor: ").append(veiculo.getCor()).append("\n");
        stringBuilder.append("Velocidade M�xima: ").append(veiculo.getVelocMax()).append(" km/h\n");
        stringBuilder.append("Quantidade de Rodas: ").append(veiculo.getQtdRodas()).append("\n");

        Motor motor = veiculo.getMotor();
        stringBuilder.append("\nInforma��es do Motor:\n");
        stringBuilder.append("Quantidade de Pist�es: ").append(motor.getQtdPist()).append("\n");
        stringBuilder.append("Pot�ncia do Motor: ").append(motor.getPotencia()).append(" HP\n");
        
        System.out.println(stringBuilder.toString());
    }

  

}
