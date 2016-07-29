package client.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.events.DisconnectAndClose;
import client.events.UserCreation;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameCreationUser extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFrameCreationUser thiis = this;

	public JFrameCreationUser() {
		setType(Type.UTILITY);
		setTitle("PearToPear Chat - Cr\u00E9ation d'un utilisateur");
		setBounds(100, 100, 556, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Veillez entrer vos informations d'authentification!");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 7;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Utilisateur :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 6;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setText("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 6;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);
		
		JLabel lblPort = new JLabel("Mot de passe :");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 1;
		gbc_lblPort.gridy = 8;
		contentPane.add(lblPort, gbc_lblPort);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 8;
		contentPane.add(passwordField, gbc_passwordField);
		
		JLabel lblRpter = new JLabel("R\u00E9p\u00E9ter mot de passe :");
		GridBagConstraints gbc_lblRpter = new GridBagConstraints();
		gbc_lblRpter.anchor = GridBagConstraints.EAST;
		gbc_lblRpter.insets = new Insets(0, 0, 5, 5);
		gbc_lblRpter.gridx = 1;
		gbc_lblRpter.gridy = 9;
		contentPane.add(lblRpter, gbc_lblRpter);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 9;
		contentPane.add(passwordField_1, gbc_passwordField_1);
		
		JButton btnCreerCompte = new JButton("Cr\u00E9er un compte");
		btnCreerCompte.addActionListener(new UserCreation(this, textField, passwordField, passwordField_1));
		
		JButton btnRetour = new JButton("Retour");
		GridBagConstraints gbc_btnRetour = new GridBagConstraints();
		gbc_btnRetour.insets = new Insets(0, 0, 5, 5);
		gbc_btnRetour.gridx = 1;
		gbc_btnRetour.gridy = 11;
		contentPane.add(btnRetour, gbc_btnRetour);
		GridBagConstraints gbc_btnSeConnecter = new GridBagConstraints();
		gbc_btnSeConnecter.anchor = GridBagConstraints.EAST;
		gbc_btnSeConnecter.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeConnecter.gridx = 2;
		gbc_btnSeConnecter.gridy = 11;
		contentPane.add(btnCreerCompte, gbc_btnSeConnecter);
		
		
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameAuthentificationUtilisateur users = new JFrameAuthentificationUtilisateur();
				users.setVisible(true);
				thiis.dispose();
			}
		});
		

		addWindowListener(new DisconnectAndClose());
		
	}

}
