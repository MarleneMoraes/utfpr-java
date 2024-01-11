package br.edu.utfpr.javai.atividadeviii.java.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.utfpr.javai.atividadeviii.java.databases.BDVeiculos;
import br.edu.utfpr.javai.atividadeviii.java.entities.Carga;
import br.edu.utfpr.javai.atividadeviii.java.entities.Passeio;
import br.edu.utfpr.javai.atividadeviii.java.exception.VeicExistException;
import br.edu.utfpr.javai.atividadeviii.java.exception.VelocException;

/**
 * @author Marlene Moraes
 * @date 2023-11-30
 * 
 *       Activity 008
 */
public class Teste implements ActionListener {

	static Teste janela = new Teste();
	
	static BDVeiculos database = new BDVeiculos();
	static List<Passeio> veiculosPasseio = database.getListaPasseio();
	static List<Carga> veiculosCarga = database.getListaCarga();
	
	static JFrame frameInicial = new JFrame("Sistema de Gestão de Veículos");
	static JFrame frameMenuVeiculo = new JFrame();
	static JFrame frameCadastro = new JFrame();
	static JFrame frameConsultaExclui = new JFrame("Consultar/Excluir pela Placa");
	static JFrame frameImprimeExclui = new JFrame("Imprimir/Excluir Todos");

	static JMenuBar menuBar = new JMenuBar();

	static JButton btnPasseio = new JButton("Passeio");
	static JButton btnCarga = new JButton("Carga");

	static JButton btnImprimirExlcuirTodos = new JButton("Imprimir/Excluir Todos");
	static JButton btnConsultarExcluirPelaPlaca = new JButton("Consultar/Excluir pela Placa");
	static JButton btnCadastrarNovo = new JButton("Cadastrar"); // go to a register page
	static JButton btnCadastrar = new JButton("Cadastrar");
	static JButton btnLimpar = new JButton("Limpar");
	static JButton btnNovo = new JButton("Novo");
	static JButton btnConsultar = new JButton("Consultar");
	static JButton btnExcluir = new JButton("Excluir");
	static JButton btnImprimirTodos = new JButton("Imprimir Todos");
	static JButton btnExcluirTodos = new JButton("Excluir Todos");
	static JButton btnSair = new JButton("Sair");
	
	static JLabel labelQtdPassageiros = new JLabel("Qtd. Passageiros: ");
	static JTextField inputQtdPassageiros = new JTextField(20);	
	static JLabel labelTara = new JLabel("Tara: ");
	static JTextField inputTara = new JTextField(20);
	static JLabel labelCargaMax = new JLabel("Carga Máx.: ");
	static JTextField inputCargaMax = new JTextField(20);
	static JLabel labelPlaca = new JLabel("Placa: ");
	static JTextField inputPlaca = new JTextField(20);
	static JLabel labelMarca = new JLabel("Marca: ");
	static JTextField inputMarca = new JTextField(20);
	static JLabel labelModelo = new JLabel("Modelo: ");
	static JTextField inputModelo = new JTextField(20);
	static JLabel labelCor = new JLabel("Cor: ");
	static JTextField inputCor = new JTextField(20);
	static JLabel labelQtdRodas = new JLabel("Qtd. Rodas: ");
	static JTextField inputQtdRodas = new JTextField(20);
	static JLabel labelVelocMax = new JLabel("Velocidade Máx.: ");
	static JTextField inputVelocMax = new JTextField(20);
	static JLabel labelQtdPist = new JLabel("Qtd. Pistões: ");
	static JTextField inputQtdPist = new JTextField(20);
	static JLabel labelPotencia = new JLabel("Potência: ");
	static JTextField inputPotencia = new JTextField(20);

	public static void main(String[] args) throws VeicExistException, VelocException {

			menuInicial();
		
	}

	public static void menuInicial() {
		Box box = criarJanela(frameInicial, 280, 200);

		box.add(btnPasseio);
		box.add(btnCarga);

		frameInicial.setLayout(new FlowLayout());
		frameInicial.add(box);
		frameInicial.setVisible(true);

		btnPasseio.addActionListener(janela);
		btnCarga.addActionListener(janela);
	}

	public static void menuVeiculo(String titulo) {
		Box box = criarJanela(frameMenuVeiculo, 300, 300);

		frameMenuVeiculo.setTitle(titulo);
		
		// Menu Options
		box.add(btnCadastrarNovo);
		box.add(btnConsultarExcluirPelaPlaca);
		box.add(btnImprimirExlcuirTodos);
		box.add(btnSair);

		// Button Events
		btnCadastrarNovo.addActionListener(janela);
		btnConsultarExcluirPelaPlaca.addActionListener(janela);
		btnImprimirExlcuirTodos.addActionListener(janela);
		btnSair.addActionListener(janela);
		
		frameMenuVeiculo.add(box);

		frameMenuVeiculo.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		

		// Initial menu buttons
		if (event.getSource().equals(btnPasseio)) {
			frameInicial.setVisible(false);
			menuVeiculo("Veiculos de Passeio");
		}

		if (event.getSource().equals(btnCarga)) {
			frameInicial.setVisible(false);
			menuVeiculo("Veiculos de Carga");
		}
		
		// Menu vehicle

		if (event.getSource().equals(btnCadastrarNovo)) {
			frameMenuVeiculo.setVisible(false);
			
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				frameCadastro.setTitle("Cadastro de Passeio");
				cadastrar(veiculosPasseio);
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				frameCadastro.setTitle("Cadastro de Carga");
				cadastrar(veiculosCarga);
			}
		}

		if (event.getSource().equals(btnConsultarExcluirPelaPlaca)) {
			frameMenuVeiculo.setVisible(false);
			
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				consultarExcluir(veiculosPasseio);
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				consultarExcluir(veiculosCarga);
			}
		}

		if (event.getSource().equals(btnImprimirExlcuirTodos)) {
			frameMenuVeiculo.setVisible(false);

			if(frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				imprimirExcluir(veiculosPasseio);				
			} else if(frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				imprimirExcluir(veiculosCarga);				
			}
		}

		// Functional buttons
		if (event.getSource().equals(btnCadastrar)) {
			if (frameCadastro.getTitle().equals("Cadastro de Passeio")) {
				Passeio veiculo = new Passeio();
				
				veiculo.setQtdPassageiros(Integer.parseInt(inputQtdPassageiros.getText()));
				veiculo.setPlaca(inputPlaca.getText());
				veiculo.setMarca(inputMarca.getText());
				veiculo.setModelo(inputModelo.getText());
				veiculo.setCor(inputCor.getText());
				veiculo.setQtdRodas(Integer.parseInt(inputQtdRodas.getText()));
				veiculo.setVelocMax(Integer.parseInt(inputVelocMax.getText()));
				veiculo.getMotor().setQtdPist(Integer.parseInt(inputQtdPist.getText()));
				veiculo.getMotor().setPotencia(Integer.parseInt(inputPotencia.getText()));
				
				veiculosPasseio.add(veiculo);
			} else if (frameCadastro.getTitle().equals("Cadastro de Carga")) {
				Carga veiculo = new Carga();
				
				veiculo.setTara(Integer.parseInt(inputTara.getText())); 
				veiculo.setCargaMax(Integer.parseInt(inputCargaMax.getText()));
				veiculo.setPlaca(inputPlaca.getText());
				veiculo.setMarca(inputMarca.getText());
				veiculo.setModelo(inputModelo.getText());
				veiculo.setCor(inputCor.getText());
				veiculo.setQtdRodas(Integer.parseInt(inputQtdRodas.getText()));
				veiculo.setVelocMax(Integer.parseInt(inputVelocMax.getText()));
				veiculo.getMotor().setQtdPist(Integer.parseInt(inputQtdPist.getText()));
				veiculo.getMotor().setPotencia(Integer.parseInt(inputPotencia.getText()));
				
				veiculosCarga.add(veiculo);
			}

			limparInputs();
			
		}
		
		if (event.getSource().equals(btnLimpar) || event.getSource().equals(btnNovo)) {
			limparInputs();
		}

		if (event.getSource().equals(btnConsultar)) {
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				Passeio veiculo = buscaVeiculosPasseio(veiculosPasseio, inputPlaca.getText());

				if (veiculo != null) {
					inputQtdPassageiros.setText(Integer.toString(veiculo.getQtdPassageiros()));
					inputMarca.setText(veiculo.getMarca());
					inputModelo.setText(veiculo.getModelo());
					inputCor.setText(veiculo.getCor());
					inputQtdRodas.setText(Integer.toString(veiculo.getQtdRodas()));
					inputVelocMax.setText(Float.toString(veiculo.getVelocMax()));
					inputQtdPist.setText(Integer.toString(veiculo.getMotor().getQtdPist()));
					inputPotencia.setText(Integer.toString(veiculo.getMotor().getPotencia()));
				}
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				Carga veiculo = buscaVeiculosCarga(veiculosCarga, inputPlaca.getText());

				if (veiculo != null) {
					inputTara.setText(Integer.toString(veiculo.getTara()));
					inputCargaMax.setText(Integer.toString(veiculo.getCargaMax()));
					inputMarca.setText(veiculo.getMarca());
					inputModelo.setText(veiculo.getModelo());
					inputCor.setText(veiculo.getCor());
					inputQtdRodas.setText(Integer.toString(veiculo.getQtdRodas()));
					inputVelocMax.setText(Float.toString(veiculo.getVelocMax()));
					inputQtdPist.setText(Integer.toString(veiculo.getMotor().getQtdPist()));
					inputPotencia.setText(Integer.toString(veiculo.getMotor().getPotencia()));
				}
			}

		}
		
		if (event.getSource().equals(btnExcluir)) {
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				Passeio veiculo = buscaVeiculosPasseio(veiculosPasseio, inputPlaca.getText());

				if (veiculo != null) {
					veiculosPasseio.remove(veiculo);
					limparInputs();
				}
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				Carga veiculo = buscaVeiculosCarga(veiculosCarga, inputPlaca.getText());

				if (veiculo != null) {
					veiculosCarga.remove(veiculo);
					limparInputs();
				}
			}
		}

		if (event.getSource().equals(btnImprimirTodos)) {
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				frameImprimeExclui.getContentPane().removeAll();
				imprimirExcluir(veiculosPasseio);
				criarTabelaPasseio(veiculosPasseio);
				frameImprimeExclui.revalidate();
				frameImprimeExclui.repaint();
				
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				frameImprimeExclui.getContentPane().removeAll();
				imprimirExcluir(veiculosCarga);
				criarTabelaCarga(veiculosCarga);
				frameImprimeExclui.revalidate();
				frameImprimeExclui.repaint();
			}
		}
		
		if (event.getSource().equals(btnExcluirTodos)) {
			if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
				frameImprimeExclui.getContentPane().removeAll();
				imprimirExcluir(veiculosPasseio);
				veiculosPasseio.removeAll(veiculosPasseio);
				frameImprimeExclui.revalidate();
				frameImprimeExclui.repaint();
				
			} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
				frameImprimeExclui.getContentPane().removeAll();
				imprimirExcluir(veiculosCarga);
				veiculosCarga.removeAll(veiculosCarga);
				frameImprimeExclui.revalidate();
				frameImprimeExclui.repaint();
			}
		}
		

		if (event.getSource().equals(btnSair)) {
			
			if(frameMenuVeiculo.isVisible()) {
				frameMenuVeiculo.setVisible(false);
				frameCadastro.setVisible(false);
				frameConsultaExclui.setVisible(false);
				frameImprimeExclui.setVisible(false);
				menuInicial();
			} 
			
			if (frameCadastro.getTitle().equals("Cadastro de Passeio")) {
				if(frameCadastro.isVisible() || frameConsultaExclui.isVisible() 
						|| frameImprimeExclui.isVisible()) {
					frameInicial.setVisible(false);
					frameCadastro.setVisible(false);
					frameConsultaExclui.setVisible(false);
					frameImprimeExclui.setVisible(false);					
				}
					
				menuVeiculo("Veiculos de Passeio");
			}
			
			if (frameCadastro.getTitle().equals("Cadastro de Carga")) {
				if(frameCadastro.isVisible() || frameConsultaExclui.isVisible() 
						|| frameImprimeExclui.isVisible()) {
					frameInicial.setVisible(false);
					frameCadastro.setVisible(false);
					frameConsultaExclui.setVisible(false);
					frameImprimeExclui.setVisible(false);					
				}
					
				menuVeiculo("Veiculos de Carga");
			}
			
		}
	}

	private static <T> void cadastrar(List<T> veiculos) {

		Box box = criarJanela(frameCadastro, 350, 450);
		JPanel panel = new JPanel();

		// Labels and inputs
		if (frameCadastro.getTitle().equals("Cadastro de Passeio")) {
			inputsPasseio(box);
		} else if (frameCadastro.getTitle().equals("Cadastro de Carga")) {
			inputsCarga(box);
		}

		box.add(labelPlaca);
		box.add(inputPlaca);
		box.add(labelMarca);
		box.add(inputMarca);
		box.add(labelModelo);
		box.add(inputModelo);
		box.add(labelCor);
		box.add(inputCor);
		box.add(labelQtdRodas);
		box.add(inputQtdRodas);
		box.add(labelVelocMax);
		box.add(inputVelocMax);
		box.add(labelQtdPist);
		box.add(inputQtdPist);
		box.add(labelPotencia);
		box.add(inputPotencia);

		// Menu Options
		panel.add(btnCadastrar);
		panel.add(btnLimpar);
		panel.add(btnNovo);
		panel.add(btnSair);
		
		// Button Events
		btnCadastrar.addActionListener(janela);
		btnLimpar.addActionListener(janela);
		btnNovo.addActionListener(janela);
		btnSair.addActionListener(janela);

		frameCadastro.add(box);
		frameCadastro.add(panel, BorderLayout.SOUTH);
		frameCadastro.setVisible(true);
	}

	private static <T> void consultarExcluir(List<T> veiculos) {
		
		Box box = criarJanela(frameConsultaExclui, 350, 450);
		JPanel panel = new JPanel();

		labelPlaca.setText("Informe a Placa: ");
		
		// Labels and inputs
		box.add(labelPlaca);
		box.add(inputPlaca);
		
		if (frameMenuVeiculo.getTitle().equals("Veiculos de Passeio")) {
			inputsPasseio(box);
		} else if (frameMenuVeiculo.getTitle().equals("Veiculos de Carga")) {
			inputsCarga(box);
		}			
		
		box.add(labelMarca);
		box.add(inputMarca);
		box.add(labelModelo);
		box.add(inputModelo);
		box.add(labelCor);
		box.add(inputCor);
		box.add(labelQtdRodas);
		box.add(inputQtdRodas);
		box.add(labelVelocMax);
		box.add(inputVelocMax);
		box.add(labelQtdPist);
		box.add(inputQtdPist);
		box.add(labelPotencia);
		box.add(inputPotencia);
		
		// Menu Options
		panel.add(btnConsultar);
		panel.add(btnExcluir);
		panel.add(btnSair);
		
		// Button Events
		btnConsultar.addActionListener(janela);
		btnExcluir.addActionListener(janela);
		btnSair.addActionListener(janela);
		
		frameConsultaExclui.add(box);
		frameConsultaExclui.add(panel, BorderLayout.SOUTH);
		frameConsultaExclui.setVisible(true);
	}

	private static void inputsPasseio(Box box) {
		box.add(labelQtdPassageiros);
		box.add(inputQtdPassageiros);
	}
	
	private static void inputsCarga(Box box) {
		box.add(labelTara);
		box.add(inputTara);
		box.add(labelCargaMax);
		box.add(inputCargaMax);
	}
	
	private static <T> void imprimirExcluir(List<T> veiculos) {
		
		Box box = criarJanela(frameImprimeExclui, 500, 600);
		JPanel panel = new JPanel();
		
		// Menu Options
		panel.add(btnImprimirTodos);
		panel.add(btnExcluirTodos);
		panel.add(btnSair);
		
		// Button Events
		btnImprimirTodos.addActionListener(janela);
		btnExcluirTodos.addActionListener(janela);
		btnSair.addActionListener(janela);
		
		frameImprimeExclui.add(box);
		frameImprimeExclui.add(panel, BorderLayout.SOUTH);
		frameImprimeExclui.setVisible(true);
	}
	
	private static void limparInputs() {
		if (frameCadastro.getTitle().equals("Cadastro de Passeio")) {
			inputQtdPassageiros.setText("");
			inputQtdPassageiros.requestFocus();
		} else if (frameCadastro.getTitle().equals("Cadastro de Carga")) {
			inputTara.setText("");
			inputCargaMax.setText("");
			inputTara.requestFocus();
		}

		inputPlaca.setText("");
		inputMarca.setText("");
		inputModelo.setText("");
		inputCor.setText("");
		inputQtdRodas.setText("");
		inputVelocMax.setText("");
		inputQtdPist.setText("");
		inputPotencia.setText("");
	}
	
	private static void criarTabelaPasseio(List<Passeio> veiculosPasseio) {
		String[] column = {"Placa", "Marca", "Modelo", "Cor", "Qtd.Rodas", "Veloc.Máx", 
				"Qtd.Pist", "Potênc", "Qtd.Passag"};
		
		DefaultTableModel model = new DefaultTableModel(column, 0);
		JTable table = new JTable(model);
		
		if(!veiculosPasseio.isEmpty()) {
			String[] row;
			
			for(Passeio veiculo : veiculosPasseio) {
				row = new String[9];
				
				row[0] = veiculo.getPlaca();
				row[1] = veiculo.getMarca();
				row[2] = veiculo.getModelo();
				row[3] = veiculo.getCor();
				row[4] = Integer.toString(veiculo.getQtdRodas());
				row[5] = Float.toString(veiculo.getVelocMax());
				row[6] = Integer.toString(veiculo.getMotor().getQtdPist());
				row[7] = Integer.toString(veiculo.getMotor().getPotencia());
				row[8] = Integer.toString(veiculo.getQtdPassageiros());
	
				model.addRow(row);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		frameImprimeExclui.add(scrollPane);
		frameImprimeExclui.setVisible(true);
	}
	
	private static void criarTabelaCarga(List<Carga> veiculosCarga) {
		String[] column = {"Placa", "Marca", "Modelo", "Cor", "Qtd.Rodas", "Veloc.Máx", 
				"Qtd.Pist", "Potênc", "Tara", "Carga Máx"};
		
		DefaultTableModel model = new DefaultTableModel(column, 0);
		JTable table = new JTable(model);
		
		if(!veiculosCarga.isEmpty()) {
			String[] row;
			
			for(Carga veiculo : veiculosCarga) {
				row = new String[10];
				
				row[0] = veiculo.getPlaca();
				row[1] = veiculo.getMarca();
				row[2] = veiculo.getModelo();
				row[3] = veiculo.getCor();
				row[4] = Integer.toString(veiculo.getQtdRodas());
				row[5] = Float.toString(veiculo.getVelocMax());
				row[6] = Integer.toString(veiculo.getMotor().getQtdPist());
				row[7] = Integer.toString(veiculo.getMotor().getPotencia());
				row[8] = Integer.toString(veiculo.getTara());
				row[9] = Integer.toString(veiculo.getCargaMax());
				
				model.addRow(row);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		frameImprimeExclui.add(scrollPane);
		frameImprimeExclui.setVisible(true);
	}
	
	// Create a default window
	private static Box criarJanela(JFrame frame, int altura, int largura) {
		
		frame.setSize(altura, largura);
		Box box = Box.createVerticalBox();
		
		//menuBar(frame);
		
		frame.setLayout(new FlowLayout());
		
		return box;
	}
	
	// Search Methods vehicles in list
	private static Passeio buscaVeiculosPasseio(List<Passeio> veiculosPasseio, String input) {
		for (Passeio veiculo : veiculosPasseio) {
			if (veiculosPasseio.isEmpty()) {
				break;
			} else if (veiculo.getPlaca().equals(input)) {
				veiculo.setPlaca(input);
				return veiculo;
			}
		}
		return null;
	}

	private static Carga buscaVeiculosCarga(List<Carga> veiculosCarga, String input) {
		for (Carga veiculo : veiculosCarga) {
			if (veiculosCarga.isEmpty()) {
				break;
			} else if (veiculo.getPlaca().equals(input)) {
				veiculo.setPlaca(input);
				return veiculo;
			}
		}
		return null;
	}
}
