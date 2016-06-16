package UIPackage;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listener.ViewListener;
import data.OperaGen;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Opera extends JFrame {
	
	private JPanel contentPane;
	public JTextPane tei;
	public JLabel currpage;
	final JLabel label_1 = new JLabel("/");
	public JLabel totpage;
	public JLabel img;
	public JButton EditTei;
	public JButton prev;
	public JButton next;
	public JButton ConfermaImg;
	public JButton RifiutaImg;
	public JButton UploadImg;
	public JButton RevisioneImg;
	public JButton ConfermaTei ;
	public JButton RifiutaTei;
	public JButton RevisioneTei; 

	public Opera(OperaGen a, String permesso, String username) {
		super(a.toString());
		Opera Gui=this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		setContentPane(contentPane);
		
		RevisioneImg = new JButton("Revis. Img");
		RevisioneImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				c.revisioneImg(Gui, a, permesso, username);
			}
		});
		
		ConfermaImg = new JButton("Conferma Img");
		ConfermaImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				try {
					c.confermaImg(Gui,a, permesso, username);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		RifiutaImg = new JButton("Rifiuta Img");
		RifiutaImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				try {
					c.rifiutaImg(Gui,a, permesso, username);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		UploadImg = new JButton("Upload Img");
		UploadImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListener b= ViewListener.getInstance();
				b.uploadImage(Gui, a, permesso, username);
			}
		});
		
		prev = new JButton("<");
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListener b= ViewListener.getInstance();
				b.prevPage(Gui, a, permesso, username);
			}
		});
		
		next = new JButton(">");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener b= ViewListener.getInstance();
				
				b.nextPage(Gui, a, permesso, username);
				
			}
		});
		
	    EditTei = new JButton("Edit TEI");
	    EditTei.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		ViewListener b= ViewListener.getInstance();
	    		try {
					b.editTei(Gui, a, permesso, username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    });
		
		ConfermaTei = new JButton("Conferma TEI");
		ConfermaTei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				try {
					c.confermaTei(Gui,a, permesso, username);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		RifiutaTei = new JButton("Rifiuta TEI");
		RifiutaTei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				c.rifiutaTei(Gui, a, permesso, username);
			}
		});
		
		RevisioneTei = new JButton("Revis. TEI");
		RevisioneTei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListener c = ViewListener.getInstance();
				c.revisioneTei(Gui, a, permesso, username);
			}
		});
		
		currpage = new JLabel("");
		
		totpage = new JLabel("100");
		
		img = new JLabel("");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(img, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(RevisioneImg, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ConfermaImg)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(RifiutaImg)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(UploadImg)
							.addGap(18)
							.addComponent(prev)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(currpage)
					.addGap(10)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(totpage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(next)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(EditTei)
							.addGap(10)
							.addComponent(ConfermaTei)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(RifiutaTei)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(RevisioneTei))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addComponent(img, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(RevisioneImg)
						.addComponent(RifiutaImg)
						.addComponent(UploadImg)
						.addComponent(currpage)
						.addComponent(totpage)
						.addComponent(next)
						.addComponent(EditTei)
						.addComponent(ConfermaImg)
						.addComponent(prev)
						.addComponent(label_1)
						.addComponent(RifiutaTei)
						.addComponent(RevisioneTei)
						.addComponent(ConfermaTei)))
		);
		
		tei = new JTextPane();
		tei.setContentType("text/html");
		tei.setEditable(false);
		scrollPane.setViewportView(tei);
		contentPane.setLayout(gl_contentPane);
		
		EditTei.setEnabled(false);
		ConfermaImg.setEnabled(false);
		RifiutaImg.setEnabled(false);
		UploadImg.setEnabled(false);
		RevisioneImg.setEnabled(false);
		ConfermaTei.setEnabled(false);
		RifiutaTei.setEnabled(false);
		RevisioneTei.setEnabled(false);
		
	}

}
