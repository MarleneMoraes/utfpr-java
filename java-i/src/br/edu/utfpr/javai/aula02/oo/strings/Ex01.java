package br.edu.utfpr.javai.aula02.oo.strings;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Construa um programa que permita ao usuario entrar com determinada frase,
 * depois permita “escolher” uma letra qualquer e: caso a letra escolhida esteja
 * na frase (seja maiuscula ou minuscula) diga quantas vezes ela apareceu e em
 * que posicao da frase. Senao, apareça uma frase informando que esta letra nao
 * existe na frase.
 * 
 * @author marlenemoraes
 * @date 2023-09-24
 */

public class Ex01 {
	public static void main() {
		InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Scanner scanner = new Scanner(inputStreamReader);

		String string = scanner.nextLine().toLowerCase();
		String letter = scanner.nextLine().toLowerCase();
		
		scanner.close();
		
		char character = letter.charAt(0);

		int qtdLetter = comparingStrings(string, character);

		if (qtdLetter > 0) {
			System.out.printf("A letra apareceu %d vezes", qtdLetter);
		} else {
			System.out.println("Esta letra não existe nesta frase");
		}
	}

	public static int comparingStrings(String string, char character) {
		int qtdLetter = 0;

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == character) {
				qtdLetter++;
			}
		}

		return qtdLetter;
	}
}
