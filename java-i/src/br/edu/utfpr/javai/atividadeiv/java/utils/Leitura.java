package br.edu.utfpr.javai.atividadeiv.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {
	public String entDados(String string) throws IOException {
		System.out.println(string);
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
		String output = "";
		
		output = input.readLine();
		
		return output;
	}
}
