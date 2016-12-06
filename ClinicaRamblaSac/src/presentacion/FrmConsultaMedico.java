package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import entidad.Citas;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class FrmConsultaMedico extends JInternalFrame {
	private ArrayList<Citas> ListaE;
	private String Columnas[]={"Codigo","Medico","Paciente","Fecha","Hora"};
	private Object Filas[][];
	private String operacion ;
	public String usuario;
	private JPanel panel;
	private JLabel lblSeleccionarMedico;
	private JComboBox cmbMedico;
	private JPanel panel_1;
	private JTable tablaConsulta;
	private JScrollPane scrollPane;
	private JLabel lblIngresarFecha;
	private JFormattedTextField txtFechaCita;
	private JButton btnBuscar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaMedico frame = new FrmConsultaMedico();
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
	public FrmConsultaMedico() {
		setBounds(100, 100, 734, 483);
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
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar citas por medico y fecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		this.panel.setBounds(24, 11, 664, 142);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblSeleccionarMedico = new JLabel("Seleccionar Medico");
		this.lblSeleccionarMedico.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblSeleccionarMedico.setBounds(49, 40, 128, 14);
		this.panel.add(this.lblSeleccionarMedico);
		
		this.cmbMedico = new JComboBox();
		this.cmbMedico.setModel(new DefaultComboBoxModel(new String[] {"-- SELECCIONAR"}));
		this.cmbMedico.setBounds(201, 38, 287, 20);
		this.panel.add(this.cmbMedico);
		
		this.lblIngresarFecha = new JLabel("Ingresar Fecha :");
		this.lblIngresarFecha.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblIngresarFecha.setBounds(49, 83, 128, 14);
		this.panel.add(this.lblIngresarFecha);
		
		this.txtFechaCita = new JFormattedTextField();
		this.txtFechaCita.setBounds(201, 81, 111, 20);
		this.panel.add(this.txtFechaCita);
		
		this.btnBuscar = new JButton("Consultar");
		this.btnBuscar.setBounds(399, 80, 89, 23);
		this.panel.add(this.btnBuscar);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Lista de Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(24, 189, 664, 253);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.tablaConsulta = new JTable();
		this.scrollPane.setViewportView(this.tablaConsulta);

	}
}
