package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entidad.Consulta;
import negocio.ConsultaN;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmBuscarPorFechas extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel lblIngresarFecha;
	private JFormattedTextField txtfecha;
	private JButton btnConsultar;
	private JPanel panel_1;
	private JTable tablaConsulta;
	private JScrollPane scrollPane;
	private ArrayList<Consulta> ListaE;
	private String Columnas[]={"Paciente","Medico","Medico","Hora"};
	private Object Filas[][];
	private String operacion ;

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
		try {
			MaskFormatter mascara1 = new MaskFormatter("##/##/####");
			mascara1.setPlaceholderCharacter('_');
			this.txtfecha = new JFormattedTextField(mascara1);
			this.txtfecha.setBounds(202, 45, 132, 20);
			this.panel.add(this.txtfecha);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
		this.btnConsultar = new JButton("Consultar");
		this.btnConsultar.addActionListener(this);
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
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaConsulta.setModel(MiModelo);

	}
	void cargarDatos(String fec){
		ConsultaN objE = new ConsultaN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar(fec);
		Filas = new Object[ListaE.size()][4];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getPaciente();
			Filas[i][3] = ListaE.get(i).getHoraC(); 
			Filas[i][2] = ListaE.get(i).getF_registro(); 
			Filas[i][1] = ListaE.get(i).getUsuario();  
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaConsulta.setModel(MiModelo);
		tablaConsulta.getColumnModel().getColumn(3).setMaxWidth(310);  
		tablaConsulta.getColumnModel().getColumn(1).setMaxWidth(110);  
		tablaConsulta.getColumnModel().getColumn(2).setMaxWidth(110);  
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		cargarDatos(txtfecha.getText());
	}
}
