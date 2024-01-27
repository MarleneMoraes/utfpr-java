package br.edu.utfpr.redes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private static Socket connection;

	private static DataInputStream input;
	private static ObjectOutputStream output;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		connection = new Socket("127.0.0.1", 50000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Digite o peso: ");
		double weight = Double.parseDouble(reader.readLine());
		
		System.out.print("Digite a altura: ");
		double height = Double.parseDouble(reader.readLine());

		output = new ObjectOutputStream(connection.getOutputStream());
		User user = new User(weight, height);
		
		output.writeObject(user);
		
		input = new DataInputStream(connection.getInputStream());
		double response = input.readDouble();
		System.out.printf("IMC: %.2f\n", response);
		
		input.close();
		output.close();
		connection.close();
	}
}
