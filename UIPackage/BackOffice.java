package UIPackage;



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
	public JTextField operatorname; 
	public JPasswordField passwordoperatore;
	public JTextField nomeopera   ;
	public JTextField autore; 
	public JTextField epoca; 
	public JTextField nomeoperdaapubb; 
	private JLabel  NomeOpera;
	private JLabel NomeOpera_1;
	private JLabel Autore;
	private JLabel Epoca;
	private JLabel lblRuolo;
	public JComboBox ruolo;
	public JComboBox ruolo1;
	public JComboBox elencooperedapubb;
	private JButton btnApplica;
	public JTextField Email;
	
	public BackOffice() {
	    BackOffice Gui = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblGestioneUtenza = new JLabel("GESTIONE UTENZA");
		
		JLabel lblPromozioneUtenza = new JLabel("Promozione Utenza:");
		
		JLabel lblUsername = new JLabel("Username");
		
		usernamepromuovere = new JTextField();
		usernamepromuovere.setColumns(10);
		
		ruolo1 = new JComboBox();
		ruolo1.addItem("ua");
		 ruolo1.addItem("rt");
		 ruolo1.addItem("tr");
		 ruolo1.addItem("ri");
		 ruolo1.addItem("ac");
		 
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
		
		JLabel lblOperatorname = new JLabel("Username Operatore"); 
		
		 NomeOpera = new JLabel("Nome opera");
		
		nomeopera = new JTextField();
		nomeopera.setColumns(30);
		
		JLabel lblCreaOpera = new JLabel("Crea Opera:");
		
		autore = new JTextField();
		autore.setColumns(30);
		
		epoca = new JTextField();
		epoca.setColumns(10);
		
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
		
		 nomeoperdaapubb = new JTextField();
		 nomeoperdaapubb.setColumns(10);
		
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
		elencooperedapubb = new JComboBox();
		 
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
		
		Email = new JTextField();
		Email.setColumns(10);
		
		JLabel lblEmailopzionale = new JLabel("Email(Opzionale)");
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
								.addComponent(NomeOpera)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(usernamepromuovere, Alignment.LEADING)
											.addComponent(operatorname, Alignment.LEADING)
											.addComponent(nomeoperdaapubb, Alignment.LEADING)
											.addComponent(nomeopera, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
										.addComponent(lblUsername)
										.addComponent(lblOperatorname))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblPermesso)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(ruolo1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnApplica))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnCerca)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(elencooperedapubb, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnPubblica))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(autore, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
												.addComponent(Autore))
											.addGap(18)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(Epoca)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(epoca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(btnCrea))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(passwordoperatore, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPassword))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEmailopzionale)
												.addComponent(Email))))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(ruolo, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCrea_1)
									.addGap(187)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRuolo)))
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
						.addComponent(ruolo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnApplica))
					.addGap(8)
					.addComponent(lblCreaOperatore)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOperatorname)
						.addComponent(lblPassword)
						.addComponent(lblRuolo)
						.addComponent(lblEmailopzionale))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(operatorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordoperatore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrea_1)
						.addComponent(ruolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
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
						.addComponent(nomeopera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(autore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(epoca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrea))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPubblicazioneOpera)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NomeOpera_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nomeoperdaapubb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCerca)
						.addComponent(elencooperedapubb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPubblica))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
