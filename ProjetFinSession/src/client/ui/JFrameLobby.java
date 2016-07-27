package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

import client.Session;

public class JFrameLobby extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JFrameLobby() {
		
		setTitle("Pear to Pear Chat - Lobby  ["+Session.getInstance().getUser().getUsername()+"]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fichier");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Salles de conversations");
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
	}

}
