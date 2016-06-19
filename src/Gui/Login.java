package Gui;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listener.ViewListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


/**
 * Classe che descrive la finestra per il Login
 * @author Luca
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	
	/**
	 * Costruttore classe Login
	 */
	public Login() {
		Login Gui = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textField = new JTextField();
		textField.setColumns(10);
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				
				ViewListener view= ViewListener.getInstance();
				view.login(Gui);	
			}
		});
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		JButton btnNewButton_1 = new JButton("Signin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener view= ViewListener.getInstance();
				view.signin(Gui);	
			}
		});
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		JButton btnNewButton_2 = new JButton("Accesso Libero");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener view= ViewListener.getInstance();
				view.freeAccess(Gui);	
			}
		});
		JLabel lblUsername_1 = new JLabel("Username");
		JLabel lblPassword_1 = new JLabel("Password");
		JLabel lblEmail = new JLabel("E-Mail");
		passwordField = new JPasswordField();
		passwordField_1 = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(lblEmail)
						.addComponent(lblPassword_1)
						.addComponent(lblUsername_1)
						.addComponent(lblPassword)
						.addComponent(lblUsername)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField_1, Alignment.LEADING)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
								.addComponent(textField_2, Alignment.LEADING)
								.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1))))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblUsername)
					.addGap(10)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(40)
					.addComponent(lblUsername_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassword_1)
					.addGap(5)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
