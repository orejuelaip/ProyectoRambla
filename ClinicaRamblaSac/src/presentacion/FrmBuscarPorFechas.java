package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FrmBuscarPorFechas extends JInternalFrame {
	private JPanel panel;
	private JLabel lblIngresarFecha;
	private JFormattedTextField txtfecha;
	private JButton btnConsultar;
	private JPanel panel_1;
	private JTable tablaConsulta;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBuscarPorFechas frame = new FrmBuscarPorFechas();
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
	public FrmBuscarPorFechas() {
		setBounds(100, 100, 710, 479);
		this.setClosable(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Consulta por Medico");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
					(pantalla.height - ventana.height) / 2);
		this.setPreferredSize(new java.awt.Dimension(649, 423));
		getContentPane().setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Consulta por fecha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel.setBounds(22, 24, 646, 91);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblIngresarFecha = new JLabel("Ingresar Fecha");
		this.lblIngresarFecha.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblIngresarFecha.setBounds(82, 47, 97, 14);
		this.panel.add(this.lblIngresarFecha);
		
		this.txtfecha = new JFormattedTextField();
		this.txtfecha.setBounds(202, 45, 132, 20);
		this.panel.add(this.txtfecha);
		
		this.btnConsultar = new JButton("Consultar");
		this.btnConsultar.setBounds(388, 44, 89, 23);
		this.panel.add(this.btnConsultar);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Lista de Citas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(22, 127, 646, 311);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.tablaConsulta = new JTable();
		this.scrollPane.setViewportView(this.tablaConsulta);

	}

}
