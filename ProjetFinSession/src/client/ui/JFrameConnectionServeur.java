package client.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.events.ConnectionServeur;

import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JFrameConnectionServeur extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the frame.
	 */
	public JFrameConnectionServeur() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameConnectionServeur.class.getResource("/img/logo.jpg")));
		setType(Type.UTILITY);
		setTitle("PearToPear Chat - Connection au Serveur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenu dans l'application Pear2Pear Chat!");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblPourDbuterVeuillez = new JLabel("Pour d\u00E9buter, veuillez entrer l'adresse du serveur d'authentification!");
		GridBagConstraints gbc_lblPourDbuterVeuillez = new GridBagConstraints();
		gbc_lblPourDbuterVeuillez.gridwidth = 5;
		gbc_lblPourDbuterVeuillez.insets = new Insets(0, 0, 5, 0);
		gbc_lblPourDbuterVeuillez.gridx = 0;
		gbc_lblPourDbuterVeuillez.gridy = 2;
		contentPane.add(lblPourDbuterVeuillez, gbc_lblPourDbuterVeuillez);
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 3;
		contentPane.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("Adresse IP :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setText("127.0.0.1");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port :");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 1;
		gbc_lblPort.gridy = 5;
		contentPane.add(lblPort, gbc_lblPort);
		
		textField_1 = new JTextField();
		textField_1.setText("9000");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 5;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnSeConnecter = new JButton("Se connecter!");
		GridBagConstraints gbc_btnSeConnecter = new GridBagConstraints();
		gbc_btnSeConnecter.anchor = GridBagConstraints.EAST;
		gbc_btnSeConnecter.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeConnecter.gridx = 2;
		gbc_btnSeConnecter.gridy = 6;
		contentPane.add(btnSeConnecter, gbc_btnSeConnecter);
		
		btnSeConnecter.addActionListener(new ConnectionServeur(this, textField, textField_1));
	}

}
