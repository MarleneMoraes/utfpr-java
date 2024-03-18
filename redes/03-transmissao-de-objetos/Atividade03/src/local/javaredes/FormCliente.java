package local.javaredes;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Marlene V. Moraes de Oliveira
 *
 */

public class FormCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCliente frame = new FormCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 148, 46, 86, 0 };
		gbl_contentPane.rowHeights = new int[] { 20, 0, 0, 0, 0, 122, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Nome");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		inputNome = new JTextField();
		GridBagConstraints gbc_inputNome = new GridBagConstraints();
		gbc_inputNome.gridwidth = 3;
		gbc_inputNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNome.insets = new Insets(0, 0, 5, 5);
		gbc_inputNome.anchor = GridBagConstraints.NORTH;
		gbc_inputNome.gridx = 0;
		gbc_inputNome.gridy = 1;
		contentPane.add(inputNome, gbc_inputNome);
		inputNome.setColumns(10);

		rotuloNome = new JLabel("Idade");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(rotuloNome, gbc_lblNewLabel_1);

		inputIdade = new JTextField();
		GridBagConstraints gbc_inputIdade = new GridBagConstraints();
		gbc_inputIdade.gridwidth = 3;
		gbc_inputIdade.insets = new Insets(0, 0, 5, 5);
		gbc_inputIdade.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputIdade.gridx = 0;
		gbc_inputIdade.gridy = 3;
		contentPane.add(inputIdade, gbc_inputIdade);
		inputIdade.setColumns(10);

		rotuloIdade = new JLabel("Resposta do Servidor");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(rotuloIdade, gbc_lblNewLabel_2);

		jTArea = new JTextArea();
		jTArea.setEditable(false);
		jTArea.setRows(2);
		jTArea.setTabSize(4);
		GridBagConstraints gbc_jTArea = new GridBagConstraints();
		gbc_jTArea.gridwidth = 3;
		gbc_jTArea.insets = new Insets(0, 0, 5, 0);
		gbc_jTArea.fill = GridBagConstraints.BOTH;
		gbc_jTArea.gridx = 0;
		gbc_jTArea.gridy = 5;
		contentPane.add(jTArea, gbc_jTArea);

		jBEnviar = new JButton("Enviar");
		jBEnviar.setVerticalAlignment(SwingConstants.BOTTOM);
		jBEnviar.setHorizontalAlignment(SwingConstants.RIGHT);
		jBEnviar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new Thread(
								new ThreadCliente(inputNome.getText(), Integer.parseInt(inputIdade.getText()), jTArea))
								.start();
						inputNome.setText("");
						inputIdade.setText("");
					}
				});
			}
		});
		GridBagConstraints gbc_jBEnviar = new GridBagConstraints();
		gbc_jBEnviar.anchor = GridBagConstraints.SOUTHEAST;
		gbc_jBEnviar.gridx = 2;
		gbc_jBEnviar.gridy = 6;
		contentPane.add(jBEnviar, gbc_jBEnviar);
	}

	private JTextField inputNome;
	private JLabel rotuloNome;
	private JTextField inputIdade;
	private JLabel rotuloIdade;
	private JTextArea jTArea;
	private JButton jBEnviar;
}
