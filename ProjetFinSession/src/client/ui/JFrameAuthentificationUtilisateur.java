package client.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.events.UserAuthentification;
import client.events.DisconnectAndClose;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class JFrameAuthentificationUtilisateur extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JFrameAuthentificationUtilisateur thiis = this;

	/**
	 * Create the frame.
	 */
	public JFrameAuthentificationUtilisateur() {
		setType(Type.UTILITY);
		setTitle("PearToPear Chat - Authentification");
		setBounds(100, 100, 471, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Avant de continiuer, veuillez vous identifier!");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblPourDbuterVeuillez = new JLabel("Si vous ne poss\u00E9dez pas d\u00E9j\u00E0 un compte, vous pouvez en cr\u00E9er un rapidement!");
		GridBagConstraints gbc_lblPourDbuterVeuillez = new GridBagConstraints();
		gbc_lblPourDbuterVeuillez.gridwidth = 6;
		gbc_lblPourDbuterVeuillez.insets = new Insets(0, 0, 5, 0);
		gbc_lblPourDbuterVeuillez.gridx = 0;
		gbc_lblPourDbuterVeuillez.gridy = 2;
		contentPane.add(lblPourDbuterVeuillez, gbc_lblPourDbuterVeuillez);
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 3;
		contentPane.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("Nom d'utilisateur:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setText("CrumDubh");
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 2;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 4;
		contentPane.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPort = new JLabel("Mot de passe:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 1;
		gbc_lblPort.gridy = 5;
		contentPane.add(lblPort, gbc_lblPort);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		
		JButton btnCreerUnCompte = new JButton("Cr\u00E9er un compte");
		GridBagConstraints gbc_btnCrerUnCompte = new GridBagConstraints();
		gbc_btnCrerUnCompte.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrerUnCompte.gridx = 2;
		gbc_btnCrerUnCompte.gridy = 6;
		contentPane.add(btnCreerUnCompte, gbc_btnCrerUnCompte);
		
		JButton btnSeConnecter = new JButton("S'authentifier!");
		GridBagConstraints gbc_btnSauthentifier = new GridBagConstraints();
		gbc_btnSauthentifier.anchor = GridBagConstraints.EAST;
		gbc_btnSauthentifier.insets = new Insets(0, 0, 5, 5);
		gbc_btnSauthentifier.gridx = 3;
		gbc_btnSauthentifier.gridy = 6;
		contentPane.add(btnSeConnecter, gbc_btnSauthentifier);
		
		btnSeConnecter.addActionListener(new UserAuthentification(this, txtUsername, passwordField));
		
		btnCreerUnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameCreationUser users = new JFrameCreationUser();
				users.setVisible(true);
				thiis.dispose();
			}
		});
		
		addWindowListener(new DisconnectAndClose());
		
	}

}
