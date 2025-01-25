import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class glavniIzbornik {

	private JFrame frame;
	private JTable tablePrihodi;
	private JTable tableRashodi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniIzbornik window = new glavniIzbornik();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public glavniIzbornik() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		JLabel PrihodiLabel = new JLabel("PRIHODI");
		PrihodiLabel.setBounds(47, 43, 46, 14);
		frame.getContentPane().add(PrihodiLabel);
		
		JLabel rashodiLabel = new JLabel("RASHODI");
		rashodiLabel.setBounds(327, 43, 46, 14);
		frame.getContentPane().add(rashodiLabel);
		
		JLabel lblNewLabel = new JLabel("PREGLED BUDZETA");
		lblNewLabel.setBounds(156, 11, 114, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton pregledUredjivanje = new JButton("Pregledaj i Uredi");
		pregledUredjivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pregledUredjivanje window = new pregledUredjivanje();
				window.frame.setVisible(true);
			}
		});
		pregledUredjivanje.setBounds(23, 213, 114, 23);
		frame.getContentPane().add(pregledUredjivanje);
		
		JButton dodajTransakciju = new JButton("Dodaj Transakciju");
		dodajTransakciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajTransakciju window = new dodajTransakciju();
				window.frame.setVisible(true);
				
			}
		});
		dodajTransakciju.setBounds(159, 213, 127, 23);
		frame.getContentPane().add(dodajTransakciju);
		
		JButton postavke = new JButton("Postavke");
		postavke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postavke window = new postavke();
				window.frame.setVisible(true);
			}
		});
		postavke.setBounds(322, 213, 89, 23);
		frame.getContentPane().add(postavke);
		
		DefaultTableModel modelPrihodi= new DefaultTableModel();
		modelPrihodi.addColumn("mjesec");
		modelPrihodi.addColumn("Iznos");
		
		tablePrihodi = new JTable(modelPrihodi);
		JScrollPane scrollPane=new JScrollPane(tablePrihodi);
		scrollPane.setBounds(23, 68, 98, 126);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel modelRashodi= new DefaultTableModel();
		modelRashodi.addColumn("mjesec");
		modelRashodi.addColumn("Iznos");
		
		tableRashodi = new JTable();
		JScrollPane scrollPaneRashodi=new JScrollPane(tableRashodi);
		scrollPaneRashodi.setBounds(313, 68, 98, 126);
		frame.getContentPane().add(scrollPaneRashodi);
		
	}
}
