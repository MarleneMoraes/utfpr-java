package br.edu.utfpr.javai.atividadev.java.main;

import br.edu.utfpr.javai.atividadev.java.entities.Pessoa;

/**
 * @author Marlene Moraes
 * @date 2023-10-28
 * 
 *       Activity 005
 */
public class Main {

	public static void main(String[] args) {
		String[] dados = { "Marlene", "29", "1234.56", "true" };

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(dados[0]);
		pessoa.setIdade(Short.parseShort(dados[1]));
		pessoa.setSalario(Double.valueOf(dados[2]));
		pessoa.setMaior_18(Boolean.parseBoolean(dados[3]));

		System.out.println(pessoa.toString());
	}

}