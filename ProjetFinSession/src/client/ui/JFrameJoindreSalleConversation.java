package client.ui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import client.events.JoindreSalleConversation;

import java.awt.Insets;
import javax.swing.JButton;

public class JFrameJoindreSalleConversation extends JFrame {
	public JFrameJoindreSalleConversation() {
		setTitle("Joindre une salle de conversation");
		setAlwaysOnTop(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 184, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNomDeLa = new JLabel("Nom de la salle");
		GridBagConstraints gbc_lblNomDeLa = new GridBagConstraints();
		gbc_lblNomDeLa.anchor = GridBagConstraints.EAST;
		gbc_lblNomDeLa.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDeLa.gridx = 1;
		gbc_lblNomDeLa.gridy = 1;
		getContentPane().add(lblNomDeLa, gbc_lblNomDeLa);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 1;
		gbc_lblMotDePasse.gridy = 2;
		getContentPane().add(lblMotDePasse, gbc_lblMotDePasse);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 2;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnCreer = new JButton("Joindre");
		GridBagConstraints gbc_btnCrer = new GridBagConstraints();
		gbc_btnCrer.gridwidth = 2;
		gbc_btnCrer.anchor = GridBagConstraints.EAST;
		gbc_btnCrer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrer.gridx = 4;
		gbc_btnCrer.gridy = 3;
		getContentPane().add(btnCreer, gbc_btnCrer);
		
		btnCreer.addActionListener(new JoindreSalleConversation(this, textField, textField_1));
		
		setBounds(100, 100, 300, 150);
	}

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

}
