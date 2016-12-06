package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
 

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//datos
import entidad.Medico;
 
import negocio.MedicoN;
 
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmMedico extends JInternalFrame implements ActionListener, MouseListener, KeyListener {
	private JPanel panel;
	private JLabel lblCodigo;
	private JLabel lblApellido;
	private JLabel lblNombre;
	private JLabel lblFNacimiento;
	private JLabel lblNDocumento;
	private JLabel lblDireccion;
	private JLabel lblEmail;
	private JLabel lblEspecialidad;
	private JLabel lblFIngreso;
	private JTextField txtid;
	private JTextField txtape;
	private JTextField txtnom;
	private JTextField txtdireccion;
	private JTextField txtmail;
	private JFormattedTextField txtdni;
	private JFormattedTextField txtf_ingreso;
	private JComboBox cmbEspecialidad;
	private JLabel lblSexo;
	private JComboBox cmbSexo;
	private JFormattedTextField txtfnaci;
	private JTable tablaMedico;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnListar;
	private JButton btnBuscar;
	private ArrayList<String> Especialidad;
	
	//Datos de tablas
	private  MedicoN medN ;
	
	
	private ArrayList<Medico> ListaE;
	private String Columnas[]={"Codigo","Apellido y nombres","F. Nacimiento","Especialidad"};
	private Object Filas[][];
	
	private String operacion ;
	public String usuario;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMedico frame = new FrmMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void cargarDatos(){
		MedicoN objE = new MedicoN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar();
		Filas = new Object[ListaE.size()][4];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getId_medico();
			Filas[i][1] = ListaE.get(i).getApellido_medico(); 
			Filas[i][2] = ListaE.get(i).getF_nacimiento(); 
			Filas[i][3] = ListaE.get(i).getNombre_especialidad(); 
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaMedico.setModel(MiModelo);
		tablaMedico.getColumnModel().getColumn(0).setMaxWidth(110);  
		tablaMedico.getColumnModel().getColumn(2).setMaxWidth(110);  
		tablaMedico.getColumnModel().getColumn(3).setMaxWidth(110);  
 
	}
	void comboE(){
		medN = new MedicoN();
		Especialidad = new ArrayList<>();
		Especialidad = medN.ListarEspe();
		cmbEspecialidad.addItem("--SELECCIONAR");
		for (String x : Especialidad) {
			cmbEspecialidad.addItem(x);
		}
	}
	/**
	 * Create the frame.
	 */
	public FrmMedico() {
		this.setVisible(false);
		this.setClosable(true);
		setBounds(100, 100, 689, 556);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Medicos");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
					(pantalla.height - ventana.height) / 2);
		this.setPreferredSize(new java.awt.Dimension(649, 423));
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Ingresar datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel.setBounds(10, 11, 602, 260);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblCodigo = new JLabel("CODIGO");
		this.lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblCodigo.setBounds(98, 38, 74, 14);
		this.panel.add(this.lblCodigo);
		
		this.lblApellido = new JLabel("APELLIDO");
		this.lblApellido.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblApellido.setBounds(98, 63, 74, 14);
		this.panel.add(this.lblApellido);
		
		this.lblNombre = new JLabel("NOMBRE");
		this.lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNombre.setBounds(98, 88, 74, 14);
		this.panel.add(this.lblNombre);
		
		this.lblFNacimiento = new JLabel("F. NACIMIENTO");
		this.lblFNacimiento.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblFNacimiento.setBounds(98, 110, 96, 14);
		this.panel.add(this.lblFNacimiento);
		
		this.lblNDocumento = new JLabel("N\u00B0 DOCUMENTO");
		this.lblNDocumento.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNDocumento.setBounds(298, 110, 96, 14);
		this.panel.add(this.lblNDocumento);
		
		this.lblDireccion = new JLabel("DIRECCION :");
		this.lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblDireccion.setBounds(98, 135, 74, 14);
		this.panel.add(this.lblDireccion);
		
		this.lblEmail = new JLabel("E-MAIL :");
		this.lblEmail.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblEmail.setBounds(98, 185, 74, 14);
		this.panel.add(this.lblEmail);
		
		this.lblEspecialidad = new JLabel("ESPECIALIDAD");
		this.lblEspecialidad.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblEspecialidad.setBounds(98, 160, 96, 14);
		this.panel.add(this.lblEspecialidad);
		
		this.lblFIngreso = new JLabel("F. INGRESO :");
		this.lblFIngreso.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblFIngreso.setBounds(333, 211, 74, 14);
		this.panel.add(this.lblFIngreso);
		
		this.txtid = new JTextField();
		this.txtid.addKeyListener(this);
		this.txtid.setEditable(false);
		this.txtid.setBounds(199, 36, 124, 20);
		this.panel.add(this.txtid);
		this.txtid.setColumns(10);
		
		this.txtape = new JTextField();
		this.txtape.setEditable(false);
		this.txtape.setColumns(10);
		this.txtape.setBounds(199, 61, 309, 20);
		this.panel.add(this.txtape);
		
		this.txtnom = new JTextField();
		this.txtnom.setEditable(false);
		this.txtnom.setColumns(10);
		this.txtnom.setBounds(199, 86, 309, 20);
		this.panel.add(this.txtnom);
		
		this.txtdireccion = new JTextField();
		this.txtdireccion.setEditable(false);
		this.txtdireccion.setColumns(10);
		this.txtdireccion.setBounds(199, 135, 309, 20);
		this.panel.add(this.txtdireccion);
		
		this.txtmail = new JTextField();
		this.txtmail.setEditable(false);
		this.txtmail.setColumns(10);
		this.txtmail.setBounds(199, 183, 309, 20);
		this.panel.add(this.txtmail);
		
	 
		
		try {
			MaskFormatter mascara1 = new MaskFormatter("##/##/####");
			mascara1.setPlaceholderCharacter('_');
			txtfnaci = new JFormattedTextField(mascara1);
			this.txtfnaci.setEditable(false);
			this.txtfnaci.setBounds(199, 108, 89, 20);
			this.panel.add(this.txtfnaci);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
		
		try {
	
			MaskFormatter mascara ;
			mascara = new MaskFormatter("########");
			mascara.setPlaceholderCharacter('_');
			this.txtdni = new JFormattedTextField(mascara);
			this.txtdni.setEditable(false);
			this.txtdni.setBounds(404, 108, 104, 20);
			this.panel.add(this.txtdni);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			txtf_ingreso = new JFormattedTextField(mascara);
			this.txtf_ingreso.setEditable(false);
			this.txtf_ingreso.setBounds(410, 209, 96, 20);
			this.panel.add(this.txtf_ingreso);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
		
		this.cmbEspecialidad = new JComboBox();
		this.cmbEspecialidad.setEnabled(false);
		this.cmbEspecialidad.setEditable(true);
		this.cmbEspecialidad.setBounds(199, 160, 309, 20);
		this.panel.add(this.cmbEspecialidad);
		
		this.lblSexo = new JLabel("SEXO :");
		this.lblSexo.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblSexo.setBounds(98, 210, 96, 14);
		this.panel.add(this.lblSexo);
		
		this.cmbSexo = new JComboBox();
		this.cmbSexo.setModel(new DefaultComboBoxModel(new String[] {"--SELECCIONAR", "FEMENINO", "MASCULINO"}));
		this.cmbSexo.setEnabled(false);
		this.cmbSexo.setEditable(true);
		this.cmbSexo.setBounds(199, 208, 124, 20);
		this.panel.add(this.cmbSexo);
		
		
		
		this.btnBuscar = new JButton("");
		this.btnBuscar.addActionListener(this);
		this.btnBuscar.setToolTipText("Buscar");
		this.btnBuscar.setBounds(329, 28, 30, 32);
		this.btnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/lupa.png")));
		this.panel.add(this.btnBuscar);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Medicos registrados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(10, 282, 602, 233);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.tablaMedico = new JTable();
		this.tablaMedico.addMouseListener(this);
		this.scrollPane.setViewportView(this.tablaMedico);
		
		this.btnNuevo = new JButton("");
		this.btnNuevo.addActionListener(this);
		this.btnNuevo.setToolTipText("Nuevo");
		this.btnNuevo.setBounds(622, 61, 30, 32);
		this.btnNuevo.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
		getContentPane().add(this.btnNuevo);
		
		this.btnEditar = new JButton("");
		this.btnEditar.addActionListener(this);
		this.btnEditar.setToolTipText("Editar");
		this.btnEditar.setIcon(new ImageIcon(getClass().getResource("/imagenes/ediatr.png")));
		this.btnEditar.setBounds(622, 92, 30, 32);
		getContentPane().add(this.btnEditar);
		
		this.btnGuardar = new JButton("");
		this.btnGuardar.addActionListener(this);
		this.btnGuardar.setEnabled(false);
		this.btnGuardar.setToolTipText("Guardar");
		this.btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));
		this.btnGuardar.setBounds(622, 126, 30, 32);
		getContentPane().add(this.btnGuardar);
		
		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setEnabled(false);
		this.btnCancelar.setToolTipText("Cancelar");
		this.btnCancelar.setBounds(622, 160, 30, 32);
		this.btnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Cancel.png")));
		getContentPane().add(this.btnCancelar);
		
		this.btnEliminar = new JButton("");
		this.btnEliminar.addActionListener(this);
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setBounds(622, 194, 30, 32);
		this.btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
		getContentPane().add(this.btnEliminar);
		
		
		this.btnListar = new JButton("");
		this.btnListar.addActionListener(this);
		this.btnListar.setToolTipText("Listar");
		this.btnListar.setBounds(622, 315, 30, 32);
		this.btnListar.setIcon(new ImageIcon(getClass().getResource("/imagenes/lista.png")));
		getContentPane().add(this.btnListar);
		comboE();
		cargarDatos();
		medN = new MedicoN();
	}
	
	void habilitar(boolean b){
		btnCancelar.setEnabled(b);
		btnEliminar.setEnabled(!b);
		btnGuardar.setEnabled(b);
		btnEditar.setEnabled(!b);
		btnNuevo.setEnabled(!b);
		txtnom.setEditable(b);
		txtape.setEditable(b);
		txtdireccion.setEditable(b);
		txtdni.setEditable(b);
		txtf_ingreso.setEditable(b);
		txtfnaci.setEditable(b);
		txtmail.setEditable(b);
		cmbEspecialidad.setEnabled(b);
		cmbSexo.setEnabled(b);
		txtid.setEditable(false);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnListar) {
			actionPerformedBtnListar(arg0);
		}
		if (arg0.getSource() == this.btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == this.btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == this.btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == this.btnEditar) {
			actionPerformedBtnEditar(arg0);
		}
		if (arg0.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == this.btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		habilitar(true);
		limpiar();
		txtid.setText(medN.generaCodigo());
		txtid.requestFocus();
		operacion ="N";
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() != 0){
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la operacion?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				habilitar(false);
				limpiar();
				operacion ="";
			}else{
				txtape.requestFocus();
			}
		}else{
			habilitar(false);
			limpiar();
			operacion ="";
		}
	}
	void msj(String msj){
		JOptionPane.showMessageDialog(null, msj);
	}
	void limpiar(){
		txtid.setText("");
		txtape.setText("");
		txtdireccion.setText("");
		txtdni.setText("");
		txtf_ingreso.setText("");
		txtfnaci.setText("");
		txtmail.setText("");
		txtnom.setText("");
		cmbEspecialidad.setSelectedIndex(0);
		cmbSexo.setSelectedIndex(0);
	}
	protected void actionPerformedBtnEditar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("No ha seleccionado un paciente.");
		}else{
			habilitar(true);
			txtnom.requestFocus();
			operacion ="E";
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("Seleccione un paciente.");
		}else{
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea elimnar al Medico "+txtnom.getText().trim()+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				if(medN.eliminar(txtid.getText().trim())){
					cargarDatos();
					limpiar();
					JOptionPane.showMessageDialog(null,"Eliminado correctamente.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(null,"Error al eliminar.","Mensaje",JOptionPane.WARNING_MESSAGE);
				}
				
			}else{
				msj("Se descarto la eliminacion.");
				limpiar();
			}
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() < 5  ){
			msj("El dato ingresado no es permitido por el sistema.");
			txtnom.requestFocus();
		}else if(txtid.getText().trim().length() < 5  ){
			msj("El codigo no se genero intente nuevamente.");
			txtnom.requestFocus();
		}else{
			Medico objE = new  Medico();
			objE.setId_medico(txtid.getText());
			objE.setApellido_medico(txtape.getText());
			objE.setNombre_medico(txtnom.getText());
			objE.setF_nacimiento(txtfnaci.getText());
			objE.setN_documento(txtdni.getText());
			objE.setDireccion(txtdireccion.getText());
			objE.setEmail(txtmail.getText());
			objE.setNombre_especialidad(cmbEspecialidad.getSelectedItem().toString());
			objE.setFecha_ingreso(txtf_ingreso.getText());
			objE.setL_activo( cmbSexo.getSelectedItem().toString().charAt(0)+"");
			objE.setC_usuario(usuario);	
			if( operacion=="N"){
				if(medN.agregar(objE)){
					cargarDatos();
					limpiar();
					habilitar(false);
					JOptionPane.showMessageDialog(null,"Registro con exito.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Registro fallido.","Mensaje",JOptionPane.WARNING_MESSAGE);
				}
			}else{
				if(medN.modificar(objE)){
					cargarDatos();
					limpiar();
					habilitar(false);
					JOptionPane.showMessageDialog(null,"Editado correctamente.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Edicion fallida.","Mensaje",JOptionPane.WARNING_MESSAGE);
				}
			}	
		}
	}
	void habilitarBtn(boolean b){
		btnNuevo.setEnabled(b);
		btnCancelar.setEnabled(!b);
		btnEditar.setEnabled(b);
		btnEliminar.setEnabled(b);
		btnGuardar.setEnabled(b);
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		txtid.setEditable(true);
		limpiar();
		txtid.requestFocus();
		habilitarBtn(false);
	}
	protected void actionPerformedBtnListar(ActionEvent arg0) {
		cargarDatos();
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == this.tablaMedico) {
			mouseClickedTablaMedico(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTablaMedico(MouseEvent arg0) {
		int fila = tablaMedico.getSelectedRow();
		String id=(String) tablaMedico.getValueAt(fila, 0);
		Medico pac = medN.Buscar(id);
		txtid.setEditable(false);
		habilitar(false);
		txtid.setText(pac.getId_medico());
		txtape.setText(pac.getApellido_medico());
		txtdireccion.setText(pac.getDireccion());
		txtdni.setText(pac.getN_documento());
		txtf_ingreso.setText(pac.getFecha_ingreso());
		txtfnaci.setText(pac.getF_nacimiento());
		txtmail.setText(pac.getEmail());
		txtnom.setText(pac.getNombre_medico());
		if (pac.getL_activo().trim() == "M") cmbSexo.setSelectedIndex(2);
		else cmbSexo.setSelectedIndex(1);
		cmbEspecialidad.setSelectedItem(pac.getNombre_especialidad());
	}
	
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == this.txtid) {
			keyPressedTxtid(arg0);
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyPressedTxtid(KeyEvent arg0) {
		String txt = txtid.getText();
		if(txt .trim().length() !=0){
			MedicoN objE = new MedicoN();
			ListaE = new ArrayList<>();
			ListaE =objE.Listar(txt);
			Filas = new Object[ListaE.size()][4];	
			for(int i = 0; i < ListaE.size(); i++){
				Filas[i][0] = ListaE.get(i).getId_medico();
				Filas[i][1] = ListaE.get(i).getApellido_medico(); 
				Filas[i][2] = ListaE.get(i).getF_nacimiento(); 
				Filas[i][3] = ListaE.get(i).getNombre_especialidad();
			}
			DefaultTableModel MiModelos = new DefaultTableModel(Filas,Columnas);
			tablaMedico.setModel(MiModelos);
			tablaMedico.getColumnModel().getColumn(0).setMaxWidth(110);  
			tablaMedico.getColumnModel().getColumn(2).setMaxWidth(110);  
			tablaMedico.getColumnModel().getColumn(3).setMaxWidth(110);
		}
	}
}
