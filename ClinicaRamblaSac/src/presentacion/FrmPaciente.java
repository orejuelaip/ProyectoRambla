package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//datos
import entidad.Paciente;
import negocio.PacienteN;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class FrmPaciente extends JInternalFrame implements ActionListener, MouseListener, KeyListener {
	private JPanel panel;
	private JLabel lblCodigo;
	private JLabel lblApellidos;
	private JLabel lblFNacimiento;
	private JLabel lblNDocumento;
	private JLabel lblDireccion;
	private JLabel lblEmail;
	private JLabel lblNombres;
	private JTextField txtnom;
	private JTextField txtape;
	private JFormattedTextField txtfnacimiento;
	private JFormattedTextField txtdocumento;
	private JTextField txtdireccion;
	private JTextField txtemail;
	private JTable tablaPaciente;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnNuevo;
	private JButton btnGuardar;
	//***************************************************
	
	private ArrayList<Paciente> ListaE;
	private String Columnas[]={"Codigo","Apellidos y nombres","F. Nacimiento","DNI"};
	private Object Filas[][];

	private String operacion ;
	public String usuario;
	private PacienteN pacN;
	private JButton btnBuscar;
	private JTextField txtid;
	private JButton btnListar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPaciente frame = new FrmPaciente();
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
	
	void cargarDatos(){
		PacienteN objE = new PacienteN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar();
		Filas = new Object[ListaE.size()][4];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getId_paciente();
			Filas[i][1] = ListaE.get(i).getNombre_paciente(); 
			Filas[i][2] = ListaE.get(i).getFecha_nacimiento(); 
			Filas[i][3] = ListaE.get(i).getNumero_documetnp(); 
 
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaPaciente.setModel(MiModelo);
		tablaPaciente.getColumnModel().getColumn(0).setMaxWidth(110);  
		tablaPaciente.getColumnModel().getColumn(2).setMaxWidth(110);  
		tablaPaciente.getColumnModel().getColumn(3).setMaxWidth(110);  
 
	}
	public FrmPaciente() {
		setBounds(100, 100, 613, 489);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Pacientes");
		getContentPane().setLayout(null);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
					(pantalla.height - ventana.height) / 2);
		this.setClosable(true);
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Ingresar datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel.setBounds(10, 11, 534, 240);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblCodigo = new JLabel("CODIGO :");
		this.lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblCodigo.setBounds(77, 33, 98, 14);
		this.panel.add(this.lblCodigo);
		
		this.lblApellidos = new JLabel("APELLIDOS :");
		this.lblApellidos.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblApellidos.setBounds(77, 82, 98, 14);
		this.panel.add(this.lblApellidos);
		
		this.lblFNacimiento = new JLabel("F. NACIMIENTO :");
		this.lblFNacimiento.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblFNacimiento.setBounds(77, 107, 98, 14);
		this.panel.add(this.lblFNacimiento);
		
		this.lblNDocumento = new JLabel("N\u00B0 DOCUMENTO");
		this.lblNDocumento.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNDocumento.setBounds(77, 136, 98, 14);
		this.panel.add(this.lblNDocumento);
		
		this.lblDireccion = new JLabel("DIRECCION");
		this.lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblDireccion.setBounds(77, 161, 98, 14);
		this.panel.add(this.lblDireccion);
		
		this.lblEmail = new JLabel("E-MAIL");
		this.lblEmail.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblEmail.setBounds(77, 186, 98, 14);
		this.panel.add(this.lblEmail);
		
		this.lblNombres = new JLabel("NOMBRES :");
		this.lblNombres.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNombres.setBounds(77, 58, 98, 14);
		this.panel.add(this.lblNombres);
		
		this.txtnom = new JTextField();
		this.txtnom.addKeyListener(this);
		this.txtnom.setEditable(false);
		this.txtnom.setColumns(10);
		this.txtnom.setBounds(185, 58, 275, 20);
		this.panel.add(this.txtnom);
		
		this.txtape = new JTextField();
		this.txtape.setEditable(false);
		this.txtape.setColumns(10);
		this.txtape.setBounds(185, 82, 275, 20);
		this.panel.add(this.txtape);
 		
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			txtfnacimiento = new JFormattedTextField(mascara);
			this.txtfnacimiento.setEditable(false);
			mascara = new MaskFormatter("########");
			mascara.setPlaceholderCharacter('_');
			this.txtdocumento = new JFormattedTextField(mascara);
			this.txtdocumento.setEditable(false);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		this.txtdocumento.setBounds(185, 136, 98, 20);
		txtfnacimiento.setBounds(185, 107, 98, 20);
 
		this.panel.add(txtfnacimiento);
		this.panel.add(this.txtdocumento);
		this.txtdireccion = new JTextField();
		this.txtdireccion.setEditable(false);
		this.txtdireccion.setBounds(185, 161, 275, 20);
		this.panel.add(this.txtdireccion);
		this.txtdireccion.setColumns(10);
		
		this.txtemail = new JTextField();
		this.txtemail.setEditable(false);
		this.txtemail.setBounds(185, 186, 275, 20);
		this.panel.add(this.txtemail);
		this.txtemail.setColumns(10);
		
		this.btnBuscar = new JButton("");
		this.btnBuscar.addKeyListener(this);
		this.btnBuscar.addActionListener(this);
		this.btnBuscar.setBackground(Color.WHITE);
		this.btnBuscar.setToolTipText("Buscar");
		this.btnBuscar.setBounds(309, 23, 32, 30);
		this.btnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/lupa.png")));
		this.panel.add(this.btnBuscar);
		
		this.txtid = new JTextField();
		this.txtid.addKeyListener(this);
		this.txtid.setEditable(false);
		this.txtid.setBounds(186, 31, 113, 20);
		this.panel.add(this.txtid);
		this.txtid.setColumns(10);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes registrados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(10, 262, 545, 186);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.tablaPaciente = new JTable();
		this.tablaPaciente.addMouseListener(this);
		this.scrollPane.setViewportView(this.tablaPaciente);
		
		this.btnEliminar = new JButton("");
		this.btnEliminar.addActionListener(this);
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setBounds(554, 157, 32, 30);
		this.btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
		getContentPane().add(this.btnEliminar);
		
		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setToolTipText("Cancelar");
		this.btnCancelar.setEnabled(false);
		this.btnCancelar.setBounds(554, 127, 32, 30);
		this.btnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Cancel.png")));
		getContentPane().add(this.btnCancelar);
		
		this.btnModificar = new JButton("");
		this.btnModificar.addActionListener(this);
		this.btnModificar.setToolTipText("Modificar");
		this.btnModificar.setBounds(554, 66, 32, 30);
		this.btnModificar.setIcon(new ImageIcon(getClass().getResource("/imagenes/ediatr.png")));
		getContentPane().add(this.btnModificar);
		
		this.btnNuevo = new JButton("");
		this.btnNuevo.addActionListener(this);
		this.btnNuevo.setToolTipText("Nuevo");
		this.btnNuevo.setBackground(Color.WHITE);
		this.btnNuevo.setBounds(554, 36, 32, 30);
		this.btnNuevo.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
		getContentPane().add(this.btnNuevo);
		
		this.btnGuardar = new JButton("");
		this.btnGuardar.addActionListener(this);
		this.btnGuardar.setToolTipText("Guardar");
		this.btnGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		this.btnGuardar.setEnabled(false);
		this.btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));
		this.btnGuardar.setBounds(554, 96, 32, 30);
		getContentPane().add(this.btnGuardar);
		
		this.btnListar = new JButton("");
		this.btnListar.addActionListener(this);
		this.btnListar.setToolTipText("Listar");
		this.btnListar.setBounds(554, 283, 32, 30);
		this.btnListar.setIcon(new ImageIcon(getClass().getResource("/imagenes/refresh.png")));
		getContentPane().add(this.btnListar);
		cargarDatos();
		pacN = new PacienteN();
		//PacE = new Paciente();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnEliminar) 	actionPerformedBtnEliminar(arg0);
		 
		if (arg0.getSource() == this.btnListar)  	actionPerformedBtnListar(arg0);
 
		if (arg0.getSource() == this.btnCancelar)  	actionPerformedBtnCancelar(arg0);
		 
		if (arg0.getSource() == this.btnBuscar) 	actionPerformedbtnBuscar(arg0);
		 
		if (arg0.getSource() == this.btnGuardar)  	actionPerformedBtnGuardar(arg0);
		 
		if (arg0.getSource() == this.btnModificar)	actionPerformedBtnModificar(arg0);
		 
		if (arg0.getSource() == this.btnNuevo) 		actionPerformedButton_3(arg0);
		 
	}
	
	void habilitar(boolean b){

		txtape.setEditable(b);
		txtdireccion.setEditable(b);
		txtdocumento.setEditable(b);
		txtemail.setEditable(b);
		txtfnacimiento.setEditable(b);
		txtnom.setEditable(b);
		btnCancelar.setEnabled(b);
		btnGuardar.setEnabled(b);
		btnNuevo.setEnabled(!b);
		btnEliminar.setEnabled(!b);
		btnModificar.setEnabled(!b);
		btnBuscar.setEnabled(!b);
	}
	void msj(String msj){
		JOptionPane.showMessageDialog(null, msj,"Advertencia",JOptionPane.INFORMATION_MESSAGE);
	}
	void limpiar(){
		txtid.setText("");
		txtape.setText("");
		txtdireccion.setText("");
		txtdocumento.setText("");
		txtemail.setText("");
		txtfnacimiento.setText("");
		txtnom.setText("");
	}
	protected void actionPerformedButton_3(ActionEvent arg0) {
		habilitar(true);
		limpiar();
		txtid.setText(pacN.generaCodigo());
		txtnom.requestFocus();
		operacion ="N";
	}
	void habilitarBtn(boolean b){
		btnCancelar.setEnabled(b);
		btnGuardar.setEnabled(b);
		btnNuevo.setEnabled(b);
		btnEliminar.setEnabled(b);
		btnModificar.setEnabled(b);
	}
	protected void actionPerformedbtnBuscar(ActionEvent arg0){
	 
		txtid.setEditable(true);
		limpiar();
		txtid.requestFocus();
		habilitarBtn(false);
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("No ha seleccionado un paciente.");
		}else{
			habilitar(true);
			txtnom.requestFocus();
			operacion ="E";
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		//Boton guardar
		
		if(txtnom.getText().trim().length() < 5  ){
			msj("El dato ingresado no es permitido por el sistema.");
			txtnom.requestFocus();
		}else if(txtid.getText().trim().length() < 5  ){
			msj("El codigo no se genero intente nuevamente.");
			txtnom.requestFocus();
		}else{
			Paciente objE = new  Paciente();
			objE.setId_paciente(txtid.getText());
			objE.setApellido_paciente(txtape.getText());
			objE.setNombre_paciente(txtnom.getText());
			objE.setFecha_nacimiento(txtfnacimiento.getText());
			objE.setNumero_documetnp(txtdocumento.getText());
			objE.setX_direccion_real(txtdireccion.getText());
			objE.setCorreo_electronico(txtemail.getText());
			objE.setC_usuario(usuario);	
			if( operacion=="N"){
				if(pacN.agregar(objE)){
					cargarDatos();
					limpiar();
					habilitar(false);
					JOptionPane.showMessageDialog(null,"Registro con exito.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Registro fallido.","Mensaje",JOptionPane.WARNING_MESSAGE);
				}
			}else{
				if(pacN.modificar(objE)){
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
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == this.tablaPaciente)  mouseClickedTablaPaciente(arg0);
		 
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTablaPaciente(MouseEvent arg0) { 
		int fila = tablaPaciente.getSelectedRow();
		String id=(String) tablaPaciente.getValueAt(fila, 0);
		Paciente pac = pacN.Buscar(id);
		txtid.setEditable(false);
		habilitar(false);
		txtid.setText(pac.getId_paciente());
		txtnom.setText(pac.getNombre_paciente());
		txtape.setText(pac.getApellido_paciente());
		txtfnacimiento.setText(pac.getFecha_nacimiento());
		txtdireccion.setText(pac.getX_direccion_real());
		txtdocumento.setText(pac.getNumero_documetnp());
		txtemail.setText(pac.getCorreo_electronico());
	}
 
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == this.txtid) keyPressedTxtid(arg0);
		 
		if (arg0.getSource() == this.txtnom)   keyPressedTxtnom(arg0);
		 
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyPressedTxtnom(KeyEvent arg0) {
	}
	protected void keyPressedTxtid(KeyEvent arg0) {
		String txt = txtid.getText();
		if(txt .trim().length() !=0){
			PacienteN objE = new PacienteN();
			ListaE = new ArrayList<>();
			ListaE =objE.Listar(txt);
			Filas = new Object[ListaE.size()][4];	
			for(int i = 0; i < ListaE.size(); i++){
				Filas[i][0] = ListaE.get(i).getId_paciente();
				Filas[i][1] = ListaE.get(i).getNombre_paciente(); 
				Filas[i][2] = ListaE.get(i).getFecha_nacimiento(); 
				Filas[i][3] = ListaE.get(i).getNumero_documetnp(); 
			}
			DefaultTableModel MiModelos = new DefaultTableModel(Filas,Columnas);
			tablaPaciente.setModel(MiModelos);
			tablaPaciente.getColumnModel().getColumn(0).setMaxWidth(110);  
			tablaPaciente.getColumnModel().getColumn(2).setMaxWidth(110);  
			tablaPaciente.getColumnModel().getColumn(3).setMaxWidth(110);
		}

	}
	protected void actionPerformedBtnListar(ActionEvent arg0) {
		cargarDatos();
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("Seleccione un medico.");
		}else{
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea elimnar al medico "+txtnom.getText().trim()+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				if(pacN.eliminar(txtid.getText().trim())){
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
}
