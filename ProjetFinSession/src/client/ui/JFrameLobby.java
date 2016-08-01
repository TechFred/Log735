package client.ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Dimension;

import client.events.DisconnectUser;
import client.events.MenuCreerSalleConversation;
import client.events.MenuJoindreSalleConversation;
import client.events.UserRoomMessage;
import client.model.MessageUDP;
import client.model.Session;
import client.model.User;

import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.JMenuItem;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class JFrameLobby extends JFrame implements FrameConvo {

	private static final long serialVersionUID = 1L;
	private JPanel panelRoom = new JPanel();
	private JPanel panelUsers = new JPanel();
	private JTextArea chatRoom = new JTextArea();

	private DefaultListModel<User> model;
	public JList<User> listUsers;
	private JTextField chatTextBox;

	private UserRoomMessage userRoomMessage = new UserRoomMessage();

	/**
	 * Create the frame.
	 */

	public JFrameLobby() {
		setResizable(false);

		model = new DefaultListModel<User>();
		listUsers = new JList<User>(model);

		setTitle("Pear to Pear Chat - Lobby  [" + Session.getInstance().getUser().getUsername() + "]");

		setBounds(0, 0, 900, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Salles de conversations");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmCrerUneSalle = new JMenuItem("Cr\u00E9er une salle de conversation");
		mnNewMenu_1.add(mntmCrerUneSalle);

		JMenuItem mntmRejoindreUneSalle = new JMenuItem("Rejoindre une salle existante");
		mnNewMenu_1.add(mntmRejoindreUneSalle);

		JScrollPane scrollPanelRooms = new JScrollPane(panelRoom);
		panelRoom.setLayout(null);

		chatRoom.setLineWrap(true);
		chatRoom.setBounds(0, 0, 648, 399);
		chatRoom.setEditable(false);
		panelRoom.add(chatRoom);

		chatTextBox = new JTextField();
		chatTextBox.setBounds(0, 405, 648, 31);
		panelRoom.add(chatTextBox);
		chatTextBox.setColumns(10);
		chatTextBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// refreshUserMessage(chatTextBox.getText());
				// TODO Les 45000 validation si on veut sécuriser le texte.
				userRoomMessage.sendMessage(new MessageUDP(Session.getInstance().getLobby().getUid(),
					chatTextBox.getText(), Session.getInstance().getUser().getUid()),
					Session.getInstance().getLobby()
				);
				chatTextBox.setText("");
			}
		});

		panelUsers.setBackground(Color.WHITE);
		JScrollPane scrollPanelUsers = new JScrollPane(panelUsers);
		panelUsers.setLayout(new BorderLayout(0, 0));

		JLabel lblUtilisateursEnLigne = new JLabel("<HTML><U>Utilisateurs en ligne</U></HTML>");
		lblUtilisateursEnLigne.setBackground(Color.WHITE);
		lblUtilisateursEnLigne.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilisateursEnLigne.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelUsers.add(lblUtilisateursEnLigne, BorderLayout.NORTH);

		panelUsers.add(listUsers, BorderLayout.CENTER);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPanelRooms, scrollPanelUsers);
		// contentPane.add(splitPane);
		setContentPane(splitPane);

		scrollPanelRooms.setMinimumSize(new Dimension(650, 500));
		scrollPanelUsers.setMinimumSize(new Dimension(200, 500));

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new DisconnectUser(this));

		mntmCrerUneSalle.addActionListener(new MenuCreerSalleConversation());
		mntmRejoindreUneSalle.addActionListener(new MenuJoindreSalleConversation());
	}

	@Override
	public void refreshListeUsers(User[] u) {
		model.clear();
		for (User user : u) {
			model.addElement(user);
		}
	}

	public void refreshUserMessage(String message) {
		chatRoom.setText(chatRoom.getText() + message + "\n");
	}

}
