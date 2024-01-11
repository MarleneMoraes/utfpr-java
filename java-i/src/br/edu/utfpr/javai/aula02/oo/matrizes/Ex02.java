package br.edu.utfpr.javai.aula02.oo.matrizes;

import java.util.Scanner;

/**
 *  Faça o mesmo procedimento do exercício anterior, porém desta vez estará
 *  usando uma matriz bidimensional
 * 
 * @author marlenemoraes
 * @date 2023-09-24
 */

public class Ex02 {
	public void ex02() {
		
        Scanner scanner = new Scanner(System.in);

        int linhas = scanner.nextInt();

        int colunas = scanner.nextInt();

        int[][] matriz = new int[linhas][colunas];

        // Solicitar ao usuário que insira os valores
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print("Digite o valor para a posição [" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        // Exibir os valores na ordem inversa
        System.out.println("Valores na ordem inversa:");

        for (int i = linhas - 1; i >= 0; i--) {
            for (int j = colunas - 1; j >= 0; j--) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
