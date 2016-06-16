package UIPackage;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listener.ViewListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import javax.swing.JList;

public class BackOffice extends JFrame {

	public JPanel contentPane;
	public JTextField usernamepromuovere;
	public JTextField operatorname; /* campo per operator name*/
	public JPasswordField passwordoperatore; /* campo password per la creazione di un operatore*/
	public JTextField textField_2   ;/*campo titolo opera*/
	public JTextField textField_3; /*campo autore opera*/
	public JTextField textField_4; /*campo epoca opera*/
	public JTextField textField_5; /*campo per il nome opera da pubblicare*/
	public JTextField textField_6;
	public JLabel  NomeOpera;
	public JLabel NomeOpera_1;
	public JLabel Autore;
	public JLabel Epoca;
	public JLabel lblRuolo;/* campo per decidere il ruolo dell'operatore*/
	public JComboBox ruolo;/* RUOLO  da dare all'operatore*/
	public JComboBox comboBox;
	public JComboBox comboBox_2;
	public JButton btnApplica;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackOffice frame = new BackOffice();
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
	public BackOffice() {
	    BackOffice Gui = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblGestioneUtenza = new JLabel("GESTIONE UTENZA");
		
		JLabel lblPromozioneUtenza = new JLabel("Promozione Utenza:");
		
		JLabel lblUsername = new JLabel("Username");
		
		usernamepromuovere = new JTextField();
		usernamepromuovere.setColumns(10);
		
		 comboBox = new JComboBox();
		 comboBox.addItem("ua");
		 comboBox.addItem("rt");
		 comboBox.addItem("tr");
		 comboBox.addItem("ri");
		 comboBox.addItem("ac");
		 
		 JLabel lblPermesso = new JLabel("Permesso");
		
		 btnApplica = new JButton("Applica");
		 btnApplica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ViewListener a= ViewListener.getInstance();
					try {
						a.promozioneutenza(Gui);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			});
		
		 JLabel lblCreaOperatore = new JLabel("Crea Operatore:");
		
		operatorname = new JTextField();
		operatorname.setColumns(10);
		
		passwordoperatore = new JPasswordField();
		
		JLabel lblPassword = new JLabel("Password");
		
		 ruolo = new JComboBox(); /*RUOLO */
		 ruolo.addItem("ua");
		 ruolo.addItem("rt");
		 ruolo.addItem("tr");
		 ruolo.addItem("ri");
		 ruolo.addItem("ac");
		 lblRuolo = new JLabel("Ruolo");/*RUOLO*/
		
		JLabel lblOperatorname = new JLabel("Operatorname"); 
		
		 NomeOpera = new JLabel("Nome opera");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblCreaOpera = new JLabel("Crea Opera:");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		Autore = new JLabel("Autore");
		
		
		Epoca = new JLabel("Epoca");
	
		JButton btnCrea = new JButton("Crea");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListener a= ViewListener.getInstance();
				try {
					a.creazioneopera(Gui);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		JLabel lblGestioneOpera = new JLabel("GESTIONE OPERA");
		
		JButton btnCrea_1 = new JButton("Crea");
		btnCrea_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListener a= ViewListener.getInstance();
				try {
					a.creazioneoperatore(Gui);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		JLabel lblPubblicazioneOpera = new JLabel("Pubblicazione opera:");
		
		 NomeOpera_1 = new JLabel("Nome opera");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener a= ViewListener.getInstance();
				try {
					a.searchBO(Gui);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		 comboBox_2 = new JComboBox();
		 
		JButton btnPubblica = new JButton("Pubblica");
		btnPubblica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListener a= ViewListener.getInstance();
				try {
					a.pubblicareopera(Gui);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		JList list = new JList();
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblGestioneUtenza, Alignment.LEADING)
						.addComponent(lblCreaOperatore, Alignment.LEADING)
						.addComponent(lblPromozioneUtenza, Alignment.LEADING)
						.addComponent(lblGestioneOpera, Alignment.LEADING)
						.addComponent(lblCreaOpera, Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername)
										.addComponent(lblOperatorname)
										.addComponent(operatorname, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(usernamepromuovere, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(NomeOpera)
									.addGap(112)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPermesso)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(textField_3, Alignment.LEADING)
														.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(passwordoperatore, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(Epoca)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(btnCrea))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
																.addGroup(gl_contentPane.createSequentialGroup()
																	.addComponent(btnApplica)
																	.addGap(119))
																.addGroup(gl_contentPane.createSequentialGroup()
																	.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblEmail)
																		.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
																	.addGap(9)
																	.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblRuolo)
																		.addComponent(ruolo, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
																	.addGap(18)))
															.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(btnCerca)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(btnPubblica))))
										.addComponent(Autore))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCrea_1))
								.addComponent(lblPassword)))
						.addComponent(lblPubblicazioneOpera, Alignment.LEADING)
						.addComponent(NomeOpera_1, Alignment.LEADING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGestioneUtenza)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPromozioneUtenza)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(lblPermesso))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernamepromuovere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnApplica))
					.addGap(8)
					.addComponent(lblCreaOperatore)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOperatorname)
						.addComponent(lblPassword)
						.addComponent(lblRuolo)
						.addComponent(lblEmail))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(operatorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordoperatore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrea_1)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addComponent(ruolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblGestioneOpera)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCreaOpera)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(NomeOpera)
						.addComponent(Autore)
						.addComponent(Epoca))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrea))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPubblicazioneOpera)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NomeOpera_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCerca)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPubblica))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
