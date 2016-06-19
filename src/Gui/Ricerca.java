package Gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Listener.ViewListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

/**
 * Classe che descrive la finestra per la ricerca
 * @author Luca
 *
 */

public class Ricerca extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JComboBox comboBox;
	public JButton btnAdmin;
	public JButton btnConsulta;
	public JLabel hellouser;
	public JCheckBox operenonpubb;
	public JButton btnProfilo;
	
	/**
	 * Costruttore della finestra Ricerca
	 * @param username  
	 * @param permesso
	 */
	public Ricerca(String username, String permesso) {
		
		Ricerca Gui = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener view= ViewListener.getInstance();
				view.search(Gui);
					
			}
		});
		
		comboBox = new JComboBox();
		
		
		
		btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener view= ViewListener.getInstance();
				view.view(Gui, username, permesso);	
			}
		});
		
		
		btnAdmin = new JButton("Amministrazione");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				ViewListener view= ViewListener.getInstance();
				view.viewAdmin(Gui, username, permesso);
			}
		});
		
		JLabel lblCercaTitolo = new JLabel("Cerca titolo");
		
		JLabel lblRisultati = new JLabel("Risultati");
		
		hellouser = new JLabel("Salve Utente");
		
	operenonpubb = new JCheckBox("Cerca opere da completare");
		
		btnProfilo = new JButton("Profilo");
		btnProfilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListener view= ViewListener.getInstance();
				view.gestioneProfilo(username);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(operenonpubb)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblRisultati)
								.addContainerGap())
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(hellouser)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCercaTitolo)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnAdmin)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnProfilo)
											.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
											.addComponent(btnConsulta))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboBox, Alignment.LEADING, 0, 359, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(textField, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
													.addGap(18)
													.addComponent(btnSearch)))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(38))))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(hellouser)
					.addGap(13)
					.addComponent(lblCercaTitolo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(operenonpubb)
					.addGap(11)
					.addComponent(lblRisultati)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConsulta)
						.addComponent(btnAdmin)
						.addComponent(btnProfilo))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
