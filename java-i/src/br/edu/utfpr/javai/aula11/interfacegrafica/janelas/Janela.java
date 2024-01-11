package br.edu.utfpr.javai.aula11.interfacegrafica.janelas;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Janela implements ActionListener {

	// Atributos con Visibilidade Global
	static Janela janela_ = new Janela();
	
	static JFrame janela = new JFrame();
	static JLabel rotuloNome = new JLabel();
	static JLabel rotuloNota = new JLabel();
	static JButton btnTrocar = new JButton();
	static JButton btnLimpar = new JButton();
	static JButton btnSair = new JButton();
	static JTextField inputNome = new JTextField(20); // tamanho dele na tela
	static JTextField inputNota = new JTextField(20); // tamanho dele na tela
	
	static JMenuBar menuBar = new JMenuBar();
	static JMenu menuPrincipal = new JMenu();
	static JMenu subMenu = new JMenu();
	
	static JMenuItem itemSair = new JMenuItem();
	static JMenuItem itemLimpar = new JMenuItem();
	static JMenuItem itemTrocar = new JMenuItem();

	public static void main(String[] args) {

		janela.setSize(280, 200);
		janela.setTitle("Minha primeira janela");

		rotuloNome.setText("Nome");
		rotuloNota.setText("Nota");
		
		inputNome.setText("Digite seu nome");
		inputNota.setText("Digite sua nota");
		

		btnTrocar.setText("Trocar");
		btnLimpar.setText("Limpar");
		btnSair.setText("Sair");
		
		menuPrincipal.setText("Menu Principal");
		subMenu.setText("Sub Menu");
		
		itemSair.setText("Sair");
		itemLimpar.setText("Limpar");
		itemTrocar.setText("Trocar");
		
		menuBar.add(menuPrincipal);
		menuBar.add(itemSair);
		
		menuPrincipal.add(subMenu);
		menuPrincipal.add(itemLimpar);
		
		subMenu.add(itemTrocar);
		
		janela.setJMenuBar(menuBar);

		janela.add(rotuloNome);
		janela.add(inputNome);

		janela.add(rotuloNota);
		janela.add(inputNota);

		janela.add(btnTrocar);
		janela.add(btnLimpar);
		janela.add(btnSair);

		btnTrocar.addActionListener(janela_);
		btnLimpar.addActionListener(janela_);
		btnSair.addActionListener(janela_);
		
		itemSair.addActionListener(janela_);
		itemLimpar.addActionListener(janela_);
		itemTrocar.addActionListener(janela_);

		janela.setLayout(new FlowLayout());

		janela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource().equals(btnTrocar) || event.getSource().equals(itemTrocar)) {
			String frase = inputNome.getText();
			inputNome.setText(inputNota.getText());
			inputNota.setText(frase);
		}
		
		if (event.getSource().equals(btnLimpar) || event.getSource().equals(itemLimpar)) {
			inputNome.setText("");
			inputNota.setText("");
			
			inputNome.requestFocus();
		}
		
		if(event.getSource().equals(btnSair) || event.getSource().equals(itemSair)) {
			System.exit(0);
		}

	}
}
