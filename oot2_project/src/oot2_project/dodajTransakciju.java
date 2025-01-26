package oot2_project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class dodajTransakciju {

	 JFrame frame;
	 private JTextField transakcijaNaziv;
	 private JTextField transakcijaIznos;
	 private JList listKategorija;
	 private JLabel lblNewLabel_3;
	 private JTextField datum;
	 private JLabel lblNewLabel_4;
	 private login user;
	 

	/**
	 * Create the application.
	 */
	public dodajTransakciju(login user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv");
		lblNewLabel.setBounds(10, 70, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nova Transakcija");
		lblNewLabel_1.setBounds(160, 11, 128, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		transakcijaNaziv = new JTextField();
		transakcijaNaziv.setBounds(83, 67, 86, 20);
		frame.getContentPane().add(transakcijaNaziv);
		transakcijaNaziv.setColumns(10);
		
		transakcijaIznos = new JTextField();
		transakcijaIznos.setBounds(83, 95, 86, 20);
		frame.getContentPane().add(transakcijaIznos);
		transakcijaIznos.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Iznos");
		lblNewLabel_2.setBounds(10, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		String [] kategorije= {"Prihod","Rashod"};
		JScrollPane scrollPaneLista = new JScrollPane();
		scrollPaneLista.setBounds(190, 62, 98, 126);
		frame.getContentPane().add(scrollPaneLista);
		
		/////lista///////
		JList listKategorija = new JList(kategorije);
		scrollPaneLista.setViewportView(listKategorija);
		listKategorija.setVisibleRowCount(3);
		listKategorija.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    /////lista////////
	    ///user///
	   
	   
		
		
		
		
	    ///user///
	
		lblNewLabel_3 = new JLabel("Kategorija");
		lblNewLabel_3.setBounds(209, 36, 65, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		datum = new JTextField();
		datum.setBounds(83, 126, 86, 20);
		frame.getContentPane().add(datum);
		datum.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Datum");
		lblNewLabel_4.setBounds(10, 129, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton unesiTransakciju = new JButton("Unesi Transakciju");
		unesiTransakciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String transakcijaNazivs, transakcijaIznoss, datums, listKategorijas;
				transakcijaNazivs = transakcijaNaziv.getText();
				transakcijaIznoss = transakcijaIznos.getText();
				listKategorijas = listKategorija.getSelectedValue().toString();
			   	int id_korisnika=user.getKorisnicki_id();
			   
				datums = datum.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
					String upitTransakcija="INSERT INTO OOT_Transakcije(naziv_transakcije, iznos_transakcije, kategorija_transakcije , id_korisnik_transakcija, datum_transakcije) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement ps=con.prepareStatement(upitTransakcija);
					ps.setString(1, transakcijaNazivs);
					ps.setString(2, transakcijaIznoss);
					ps.setString(3, listKategorijas);
					ps.setInt(4, id_korisnika);
					ps.setString(5, datums);
					
					int ubacenoRedaka=ps.executeUpdate();
					if (ubacenoRedaka>0) {
						JOptionPane.showMessageDialog(null, "Uspješno upisani podaci.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Podaci nisu upisani!");
					}
						}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				
			}
		});
		unesiTransakciju.setBounds(80, 211, 137, 23);
		frame.getContentPane().add(unesiTransakciju);
	}

	private void add(JScrollPane jScrollPane) {
		// TODO Auto-generated method stub
		
	}
}
