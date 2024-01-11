package br.edu.utfpr.javai.aula02.oo.matrizes;

import java.util.Scanner;

/**
 * Crie um programa, no qual terá um vetor de inteiros, cujo tamanho será
 * definido pelo valor de uma variável local, que permita ao usuário entrar com
 * os valores. Depois, estes valores serão apresentados na ordem inversa à da
 * entrada.
 * 
 * @author marlenemoraes
 * @date 2023-09-24
 */

public class Ex01 {

	public void ex01() {
        showInverse();
		
	}

	public int[] createVector() {
		Scanner scanner = new Scanner(System.in);
		
		int size = scanner.nextInt();
		int[] vector = new int[size];
		
		for(int i = 0; i < vector.length; i++) {
			vector[i] = scanner.nextInt();
		}
		 
		scanner.close();
		
		return vector;
	}
	
	private void showInverse() {
		int[] integer = createVector();
	
		for(int i = integer.length; i <= 0; i--) {
			System.out.println(integer[i]);
		}
	}

}
