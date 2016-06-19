package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listener.ViewListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author  loris
 *
 */
public class GestioneProfilo extends JFrame {

	private JPanel contentPane;
	public JTextField nuovaEmail;
	public JPasswordField vecchiaPwd;
	private JLabel lblNewLabel;
	public JPasswordField nuovaPwd;
	private JButton btnCambiaPwd;
	private JLabel lblNuovaPassword;

/**
 * 
 * @param username
 * Costruttore per la gestione profilo
 */
	public GestioneProfilo(String username){
		GestioneProfilo Gui = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nuovaEmail = new JTextField();
		nuovaEmail.setColumns(10);
		
		JButton btnCambiaEmail = new JButton("Cambia");
		btnCambiaEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListener view= ViewListener.getInstance();
				view.cambiaEmail(Gui, username);	
			}
		});
		
		vecchiaPwd = new JPasswordField();
		
		JLabel lblNuovaEmail = new JLabel("Nuova Email");
		
		lblNewLabel = new JLabel("Vecchia Password");
		
		nuovaPwd = new JPasswordField();
		nuovaPwd.setColumns(10);
		
		btnCambiaPwd = new JButton("Cambia");
		btnCambiaPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListener view= ViewListener.getInstance();
				view.cambiaPassword(Gui, username);
			}
		});
		
		lblNuovaPassword = new JLabel("Nuova Password");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNuovaEmail)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(vecchiaPwd, Alignment.LEADING)
									.addComponent(nuovaEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNuovaPassword)
								.addComponent(btnCambiaEmail)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(nuovaPwd, 153, 153, 153)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCambiaPwd)))))
					.addGap(3))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNuovaEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nuovaEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCambiaEmail))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNuovaPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(vecchiaPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nuovaPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCambiaPwd))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
