package br.edu.utfpr.javai.interfacegrafica.calculadora;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Marlene Moraes
 * @date 2023-12-09
 * 
 *       Calculator
 */

public class Main implements ActionListener {
	static Main window = new Main();

	static JFrame frame = new JFrame("Calculadora");

	static JButton btnProxValor = new JButton("Próximo Valor");

	static JButton btnUm = new JButton("1");
	static JButton btnDois = new JButton("2");
	static JButton btnTres = new JButton("3");
	static JButton btnQuatro = new JButton("4");
	static JButton btnCinco = new JButton("5");
	static JButton btnSeis = new JButton("6");
	static JButton btnSete = new JButton("7");
	static JButton btnOito = new JButton("8");
	static JButton btnNove = new JButton("9");
	static JButton btnZero = new JButton("0");

	static JButton btnSoma = new JButton("+");
	static JButton btnSubtracao = new JButton("-");
	static JButton btnDivisao = new JButton("/");
	static JButton btnMultiplicacao = new JButton("*");
	static JButton btnResult = new JButton("=");

	static JButton btnClear = new JButton("Clear");
	static JButton btnExit = new JButton("Exit");

	static JLabel labelValor1 = new JLabel("Valor1: ");
	static JTextField inputValor1 = new JTextField(5);
	static JLabel labelValor2 = new JLabel("Valor2: ");
	static JTextField inputValor2 = new JTextField(5);
	static JLabel labelResult = new JLabel("Resultado: ");
	static JTextField inputResult = new JTextField(5);

	public static void main(String[] args) {
		
		frameCalculadora(frame, 300, 300);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) throws ArithmeticException {
		Object source = event.getSource();
		Calculadora calculadora = new Calculadora();
		
		if (source.equals(btnUm)) {
			enterValue(btnUm, source);
		}
		
		if (source.equals(btnDois)) {
			enterValue(btnDois, source);
		}
		
		if (source.equals(btnTres)) {
			enterValue(btnTres, source);
		}
		
		if (source.equals(btnQuatro)) {
			enterValue(btnQuatro, source);
		}
		
		if (source.equals(btnCinco)) {
			enterValue(btnCinco, source);
		}
		
		if (source.equals(btnSeis)) {
			enterValue(btnSeis, source);
		}
		
		if (source.equals(btnSete)) {
			enterValue(btnSete, source);
		}
		
		if (source.equals(btnOito)) {
			enterValue(btnOito, source);
		}
		
		if (source.equals(btnNove)) {
			enterValue(btnNove, source);
		}
		
		if (source.equals(btnZero)) {
			enterValue(btnZero, source);
		}

		if(source.equals(btnSoma)) {
			calculadora = setCalculatorValues(btnSoma);
			
			inputResult.setText(Integer.toString(calculadora.getResultado()));	
		}
		
		if (source.equals(btnSubtracao)) {
			calculadora = setCalculatorValues(btnSubtracao);
			
			inputResult.setText(Integer.toString(calculadora.getResultado()));	

		}
		
		
		if(source.equals(btnDivisao)) {
			calculadora = setCalculatorValues(btnDivisao);
			
			if(!inputValor2.getText().equals("0")){
				inputResult.setText(Integer.toString(calculadora.getResultado()));					
			} else {
				inputValor2.setText("");
				inputValor2.requestFocus();
			}
		} 
		
		if(source.equals(btnMultiplicacao)) {
			calculadora = setCalculatorValues(btnMultiplicacao);
			
			inputResult.setText(Integer.toString(calculadora.getResultado()));	
		}
		
		
		if (source.equals(btnResult)) {			
			inputResult.setText(Integer.toString(calculadora.getResultado()));	
		}
		
		
		if (event.getSource().equals(btnProxValor) && inputValor1.isFocusable()) {
			inputValor2.requestFocus();
		} 

		if (source.equals(btnClear)) {
			inputValor1.setText("");
			inputValor2.setText("");
			inputResult.setText("");
			
			inputValor1.requestFocus();
		}
		
		if (source.equals(btnExit)) {
			System.exit(0);
		}
	}
	
	private static Calculadora setCalculatorValues(JButton btn) {
		Calculadora calculadora = new Calculadora();
		
		calculadora.setValor1(Integer.parseInt(inputValor1.getText()));
		calculadora.setValor2(Integer.parseInt(inputValor2.getText()));
		
		
		if(btn.equals(btnSoma)) {
			calculadora.setResultado(calculadora.somar
					(calculadora.getValor1(), calculadora.getValor2()));
		}
		
		if(btn.equals(btnSubtracao)) {
			calculadora.setResultado(calculadora.subtrair
					(calculadora.getValor1(), calculadora.getValor2()));
		}
		
		if (btn.equals(btnDivisao)) {
			try {
				calculadora.setResultado(calculadora.dividir(
						calculadora.getValor1(), calculadora.getValor2()));
			} catch (ArithmeticException e) {
				e.getMessage();
			} finally {
				inputValor2.setText("");
				inputValor2.requestFocus();
			}
		}
		
		if(btn.equals(btnMultiplicacao)) {
			calculadora.setResultado(calculadora.multiplicar
					(calculadora.getValor1(), calculadora.getValor2()));
		}
		
		return calculadora;
	}
	
	/**
	 * Abstraction for if case inputs
	 * @param btn
	 * @param source
	 */
	private static void enterValue(JButton btn, Object source) {
		if (source.equals(btn)) {
			if (inputValor1.getText().equals("")) {
				inputSetText(btn, inputValor1);
				inputValor2.requestFocus();
			} else if (inputValor2.getText().equals("")) {
				inputSetText(btn, inputValor2);
			}
		}
	}
	
	/**
	 * Setting text according button value
	 * @param btn
	 * @param input
	 */
	private static void inputSetText(JButton btn, JTextField input) {
		switch (btn.getText()) {
		case "0":
			input.setText("0");
			break;
		case "1":
			input.setText("1");
			break;

		case "2":
			input.setText("2");
			break;
		case "3":
			input.setText("3");
			break;
		case "4":
			input.setText("4");
			break;
		case "5":
			input.setText("5");
			break;
		case "6":
			input.setText("6");
			break;
		case "7":
			input.setText("7");
			break;
		case "8":
			input.setText("8");
			break;
		case "9":
			input.setText("9");
			break;
		}
	}
	
	/**
	 * Create a default calculator
	 * 
	 * @param frame
	 * @param altura
	 * @param largura
	 */
	private static void frameCalculadora(JFrame frame, int altura, int largura) {

		frame.setSize(altura, largura);
		frame.setLayout(new FlowLayout());
		
		Box box = createBox();
		
		buttonsEvents();

		frame.setLayout(new FlowLayout());
		frame.add(box);
		frame.setVisible(true);
	}
	
	/**
	 * Create all boxes inside the frame
	 * @return box
	 */
	private static Box createBox() {
		Box box = Box.createVerticalBox();
		Box boxValues = Box.createVerticalBox();
		Box numbersBox0 = Box.createHorizontalBox();
		Box numbersBox1 = Box.createHorizontalBox();
		Box numbersBox2 = Box.createHorizontalBox();
		Box numbersBox3 = Box.createHorizontalBox();
		Box numbersBox4 = Box.createHorizontalBox();

		boxValues.add(labelValor1);
		boxValues.add(inputValor1);
		boxValues.add(labelValor2);
		boxValues.add(inputValor2);
		boxValues.add(labelResult);
		boxValues.add(inputResult);

		numbersBox0.add(btnProxValor);
		numbersBox0.add(btnSoma);

		numbersBox1.add(btnUm);
		numbersBox1.add(btnDois);
		numbersBox1.add(btnTres);
		numbersBox1.add(btnSubtracao);

		numbersBox2.add(btnQuatro);
		numbersBox2.add(btnCinco);
		numbersBox2.add(btnSeis);
		numbersBox2.add(btnDivisao);

		numbersBox3.add(btnSete);
		numbersBox3.add(btnOito);
		numbersBox3.add(btnNove);
		numbersBox3.add(btnMultiplicacao);

		numbersBox4.add(btnClear);
		numbersBox4.add(btnZero);
		numbersBox4.add(btnExit);
		numbersBox4.add(btnResult);
		
		box.add(boxValues);
		box.add(numbersBox0);
		box.add(numbersBox1);
		box.add(numbersBox2);
		box.add(numbersBox3);
		box.add(numbersBox4);
		
		return box;
	}
	
	/**
	 * Add all buttons' actions in main frame 
	 */
	private static void buttonsEvents() {
		btnUm.addActionListener(window);
		btnDois.addActionListener(window);
		btnTres.addActionListener(window);
		btnQuatro.addActionListener(window);
		btnCinco.addActionListener(window);
		btnSeis.addActionListener(window);
		btnSete.addActionListener(window);
		btnOito.addActionListener(window);
		btnNove.addActionListener(window);
		btnZero.addActionListener(window);
		
		btnSoma.addActionListener(window);
		btnSubtracao.addActionListener(window);
		btnDivisao.addActionListener(window);
		btnMultiplicacao.addActionListener(window);
		
		btnProxValor.addActionListener(window);
		btnResult.addActionListener(window);
		btnClear.addActionListener(window);
		btnExit.addActionListener(window);
	}
	
	
}
