package br.edu.utfpr.redes.aula4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Marlene V. Moraes de Oliveira
 *
 */

public class FormCliente implements ActionListener {
	// Interface Grafica
	static FormCliente form = new FormCliente();

	static JFrame frame = new JFrame();
	static JLabel rotuloNome = new JLabel();
	static JLabel rotuloIdade = new JLabel();
	static JLabel rotuloRetorno = new JLabel();
	static JTextField inputNome = new JTextField(20);
	static JTextField inputIdade = new JTextField(20);
	static JTextField inputRetorno = new JTextField();
	static JButton btnEnviar = new JButton();

	// Conexao - cliente
	private static Socket conexao;
	private static DataInputStream entrada;
	private static ObjectOutputStream saida;

	public static void main(String[] args) {

		createBox();

		try {
			conexao = new Socket("127.0.0.1", 50000);

			saida = new ObjectOutputStream(conexao.getOutputStream());
			entrada = new DataInputStream(conexao.getInputStream());

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (conexao != null) {
					conexao.close();
				}
			} catch (IOException e) {
				Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(btnEnviar)) {
			try {
				String nome = inputNome.getText();
				int idade = Integer.parseInt(inputIdade.getText());

				Pessoa pessoa = new Pessoa(nome, idade);

				saida.writeObject(pessoa);
				saida.flush();

				String resposta = entrada.readUTF();

				inputRetorno.setText("Resposta do servidor: " + resposta);
			} catch (IOException e) {
				Logger.getLogger(FormCliente.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	/**
	 * Create box inside the frame
	 */
	private static void createBox() {
		frame.setSize(280, 200);

		rotuloNome.setText("Nome");
		rotuloIdade.setText("Idade");
		rotuloRetorno.setText("Retorno do Servidor");
		btnEnviar.setText("Enviar");

		// Box box = Box.createVerticalBox();
		Box verticalBox = Box.createVerticalBox();
		Box horizontalBox1 = Box.createHorizontalBox();
		Box horizontalBox2 = Box.createHorizontalBox();
		Box horizontalBox3 = Box.createHorizontalBox();
		Box horizontalBox4 = Box.createHorizontalBox();
		Box horizontalBox5 = Box.createHorizontalBox();
		Box horizontalBox6 = Box.createHorizontalBox();
		Box horizontalBox7 = Box.createHorizontalBox();

		horizontalBox1.add(rotuloNome);
		horizontalBox1.add(Box.createHorizontalGlue());

		horizontalBox2.add(inputNome);
		horizontalBox2.add(Box.createHorizontalGlue());

		horizontalBox3.add(rotuloIdade);
		horizontalBox3.add(Box.createHorizontalGlue());

		horizontalBox4.add(inputIdade);
		horizontalBox4.add(Box.createHorizontalGlue());

		horizontalBox5.add(rotuloRetorno);
		horizontalBox5.add(Box.createHorizontalGlue());

		horizontalBox6.add(inputRetorno);
		horizontalBox6.add(Box.createHorizontalGlue());

		horizontalBox7.add(btnEnviar);
		btnEnviar.addActionListener(form);

		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(horizontalBox1);
		verticalBox.add(horizontalBox2);
		verticalBox.add(horizontalBox3);
		verticalBox.add(horizontalBox4);
		verticalBox.add(horizontalBox5);
		verticalBox.add(horizontalBox6);
		verticalBox.add(horizontalBox7);

		frame.add(verticalBox);

		btnEnviar.addActionListener(form);

		frame.setLayout(new FlowLayout());

		frame.setVisible(true);
	}

}