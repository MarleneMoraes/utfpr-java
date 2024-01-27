package br.edu.utfpr.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private static Socket connection;
	
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		connection = new Socket("127.0.0.1", 51000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Cadastro Pessoa");
		System.out.print("Nome: ");
		String name = reader.readLine();
		
		System.out.print("CPF: ");
		String cpf = reader.readLine();
		
		System.out.print("Endereço: ");
		String address = reader.readLine();
		
		System.out.print("Telefone: ");
		String telephone = reader.readLine();
		
		System.out.print("E-mail: ");
		String email = reader.readLine();
		
		System.out.print("Idade: ");
		long age = Long.parseLong(reader.readLine());
		
		output = new ObjectOutputStream(connection.getOutputStream());
		Person person = new Person (name, cpf, address, telephone, email, age);
		
		output.writeObject(person);
		
		input = new ObjectInputStream(connection.getInputStream());
		Person response = (Person) input.readObject();
		System.out.println("Lista: " + response.toString());
		
		input.close();
		output.close();
		connection.close();
	}
}
