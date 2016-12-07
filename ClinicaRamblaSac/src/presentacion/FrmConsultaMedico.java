package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import negocio.CitasN;
import negocio.ConsultaN;
import entidad.Consulta;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmConsultaMedico extends JInternalFrame implements ItemListener, ActionListener {
	private ArrayList<Consulta> ListaE;
	private String Columnas[]={"Paciente","Hora","F. Registro","Descargado"};
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
	
	private ArrayList<String> Medicos;
	
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
		this.cmbMedico.addItemListener(this);
		this.cmbMedico.setModel(new DefaultComboBoxModel(new String[] {"-- SELECCIONAR"}));
		this.cmbMedico.setBounds(201, 38, 372, 20);
		this.panel.add(this.cmbMedico);
		
		this.lblIngresarFecha = new JLabel("Ingresar Fecha :");
		this.lblIngresarFecha.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblIngresarFecha.setBounds(49, 83, 128, 14);
		this.panel.add(this.lblIngresarFecha);
		try {
			MaskFormatter mascara1 = new MaskFormatter("##/##/####");
			mascara1.setPlaceholderCharacter('_');
			this.txtFechaCita = new JFormattedTextField(mascara1);
			this.txtFechaCita.setBounds(201, 81, 111, 20);
			this.panel.add(this.txtFechaCita);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
		this.btnBuscar = new JButton("Consultar");
		this.btnBuscar.addActionListener(this);
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
		comboMed();
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaConsulta.setModel(MiModelo);
	}

	void cargarDatos(String id, String fec){
		ConsultaN objE = new ConsultaN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar(id,fec);
		Filas = new Object[ListaE.size()][4];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getPaciente();
			Filas[i][1] = ListaE.get(i).getHoraC(); 
			Filas[i][2] = ListaE.get(i).getF_registro(); 
			Filas[i][3] = ListaE.get(i).getUsuario();  
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaConsulta.setModel(MiModelo);
		tablaConsulta.getColumnModel().getColumn(3).setMaxWidth(310);  
		tablaConsulta.getColumnModel().getColumn(1).setMaxWidth(110);  
		tablaConsulta.getColumnModel().getColumn(2).setMaxWidth(110);  
	}
	void comboMed(){
		ConsultaN n = new ConsultaN();
		cmbMedico.removeAllItems();
		Medicos  = new ArrayList<>();
		Medicos = n .ListaNombreMed();
		cmbMedico.addItem("--SELECCIONAR");
		for (String x : Medicos) {
			cmbMedico.addItem(x);
		}
	}
	public void itemStateChanged(ItemEvent arg0) {
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		cargarDatos(cmbMedico.getSelectedItem().toString(),txtFechaCita.getText());
	}
}
