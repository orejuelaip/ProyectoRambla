package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
//***
import entidad.Citas;


import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 
/****************************************************/
import negocio.CitasN;
import entidad.Citas;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import negocio.metodosN;
public class FrmCitas extends JInternalFrame implements ActionListener, ItemListener, MouseListener {

	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblMedico;
	private JLabel lblPaciente;
	private JTextField txtid;
	private JLabel lblFechaCita;
	private JLabel lblHoraCita;
	private JFormattedTextField txtfecha;
	private JLabel lblEspecialidad;
	private JFormattedTextField txthora;
	private JComboBox cmbEspecialidad;
	private JComboBox cmbMedico;
	private JComboBox cmbPaciente;
	private JLabel lblDetalle;
	private JTextArea txtdetalle;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnBorrar;
	private JPanel panel_1;
	private JTable tablaCitas;
	private JScrollPane scrollPane;
	private JButton btnListar;
	private JButton btnBuscar;
	private metodosN m;
	/**
	 * Launch the application.
	 */
	
	// ***datos ***
	
	private ArrayList<Citas> ListaE;
	private String Columnas[]={"Codigo","Medico","Paciente","Fecha","Hora"};
	private Object Filas[][];
	private String operacion ;
	public String usuario;
	
	private CitasN citaN;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCitas frame = new FrmCitas();
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
	public FrmCitas() {
		setBounds(100, 100, 682, 552);
		this.setVisible(false);
		this.setClosable(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Citas");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
					(pantalla.height - ventana.height) / 2);
		this.setPreferredSize(new java.awt.Dimension(649, 423));
		getContentPane().setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ingresar cita", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel.setBounds(10, 33, 606, 238);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblNewLabel = new JLabel("CODIGO :");
		this.lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNewLabel.setBounds(40, 29, 105, 14);
		this.panel.add(this.lblNewLabel);
		
		this.lblMedico = new JLabel("MEDICO :");
		this.lblMedico.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblMedico.setBounds(40, 79, 105, 14);
		this.panel.add(this.lblMedico);
		
		this.lblPaciente = new JLabel("PACIENTE :");
		this.lblPaciente.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblPaciente.setBounds(40, 104, 105, 14);
		this.panel.add(this.lblPaciente);
		
		this.txtid = new JTextField();
		this.txtid.setEditable(false);
		this.txtid.setBounds(147, 27, 168, 20);
		this.panel.add(this.txtid);
		this.txtid.setColumns(10);
		
		this.lblFechaCita = new JLabel("FECHA CITA:");
		this.lblFechaCita.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblFechaCita.setBounds(40, 129, 105, 14);
		this.panel.add(this.lblFechaCita);
		
		this.lblHoraCita = new JLabel("HORA CITA :");
		this.lblHoraCita.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblHoraCita.setBounds(321, 131, 105, 14);
		this.panel.add(this.lblHoraCita);
		try {
			MaskFormatter mascara1 = new MaskFormatter("##/##/####");
			mascara1.setPlaceholderCharacter('_');
			this.txtfecha = new JFormattedTextField(mascara1);
			this.txtfecha.setEditable(false);
			this.txtfecha.setBounds(147, 127, 121, 20);
			this.panel.add(this.txtfecha);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
		
		
		
		this.lblEspecialidad = new JLabel("ESPECIALIDAD :");
		this.lblEspecialidad.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblEspecialidad.setBounds(40, 54, 105, 14);
		this.panel.add(this.lblEspecialidad);
		try {
			MaskFormatter mascara1 = new MaskFormatter("##:##");
			mascara1.setPlaceholderCharacter('_');
			this.txthora = new JFormattedTextField(mascara1);
 			this.txthora.setEditable(false);
			this.txthora.setBounds(416, 129, 69, 20);
			this.panel.add(this.txthora);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de Foramteo"+e.getMessage());
		}
		
	
		
		this.cmbEspecialidad = new JComboBox();
		this.cmbEspecialidad.addItemListener(this);
		this.cmbEspecialidad.setEditable(true);
		this.cmbEspecialidad.setEnabled(false);
		this.cmbEspecialidad.setBounds(147, 54, 338, 20);
		this.panel.add(this.cmbEspecialidad);
		
		this.cmbMedico = new JComboBox();
		this.cmbMedico.setModel(new DefaultComboBoxModel(new String[] {"--SELECCIONAR"}));
		this.cmbMedico.setEditable(true);
		this.cmbMedico.setEnabled(false);
		this.cmbMedico.setBounds(147, 77, 338, 20);
		this.panel.add(this.cmbMedico);
		
		this.cmbPaciente = new JComboBox();
		this.cmbPaciente.setEditable(true);
		this.cmbPaciente.setEnabled(false);
		this.cmbPaciente.setBounds(147, 102, 338, 20);
		this.panel.add(this.cmbPaciente);
		
		this.lblDetalle = new JLabel("DETALLE :");
		this.lblDetalle.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblDetalle.setBounds(40, 154, 105, 14);
		this.panel.add(this.lblDetalle);
		
		this.txtdetalle = new JTextArea();
		this.txtdetalle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.txtdetalle.setEditable(false);
		this.txtdetalle.setBounds(147, 154, 338, 73);
		this.panel.add(this.txtdetalle);
		
		this.btnBuscar = new JButton("");
		this.btnBuscar.addActionListener(this);
		this.btnBuscar.setToolTipText("Nuevo");
		this.btnBuscar.setBounds(325, 16, 30, 32);
		this.btnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/lupa.png")));
		this.panel.add(this.btnBuscar);
		
		this.btnNuevo = new JButton("");
		this.btnNuevo.addActionListener(this);
		this.btnNuevo.setToolTipText("Nuevo");
		this.btnNuevo.setBounds(626, 62, 30, 32);
		this.btnNuevo.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
		getContentPane().add(this.btnNuevo);
		
		this.btnEditar = new JButton("");
		this.btnEditar.addActionListener(this);
		this.btnEditar.setToolTipText("Editar");
		this.btnEditar.setBounds(626, 96, 30, 32);
		this.btnEditar.setIcon(new ImageIcon(getClass().getResource("/imagenes/ediatr.png")));
		getContentPane().add(this.btnEditar);
		
		this.btnGuardar = new JButton("");
		this.btnGuardar.addActionListener(this);
		this.btnGuardar.setEnabled(false);
		this.btnGuardar.setToolTipText("Guardar");
		this.btnGuardar.setBounds(626, 128, 30, 32);
		this.btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));
		getContentPane().add(this.btnGuardar);
		
		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setEnabled(false);
		this.btnCancelar.setToolTipText("Cancelar");
		this.btnCancelar.setBounds(626, 162, 30, 32);
		this.btnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Cancel.png")));
		getContentPane().add(this.btnCancelar);
		
		this.btnBorrar = new JButton("");
		this.btnBorrar.addActionListener(this);
		this.btnBorrar.setToolTipText("Borrar");
		this.btnBorrar.setBounds(626, 196, 30, 32);
		this.btnBorrar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
		getContentPane().add(this.btnBorrar);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de citas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(0, 282, 616, 229);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.tablaCitas = new JTable();
		this.tablaCitas.addMouseListener(this);
		this.scrollPane.setViewportView(this.tablaCitas);
		
		this.btnListar = new JButton("");
		this.btnListar.addActionListener(this);
		this.btnListar.setToolTipText("Nuevo");
		this.btnListar.setBounds(626, 293, 30, 32);
		this.btnListar.setIcon(new ImageIcon(getClass().getResource("/imagenes/lista.png")));
		getContentPane().add(this.btnListar);
		
		citaN = new CitasN();
		comboE();
		comboPac();
		cargarDatos();
		m = new metodosN();
	}
	private ArrayList<String> Especialidad;
	private ArrayList<String> Medicos;
	private ArrayList<String> Paciente;
	void cargarDatos(){
		CitasN objE = new CitasN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar();
		Filas = new Object[ListaE.size()][5];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getId_cita();
			Filas[i][1] = ListaE.get(i).getId_medico(); 
			Filas[i][2] = ListaE.get(i).getId_paciente(); 
			Filas[i][3] = ListaE.get(i).getFecha_cita(); 
			Filas[i][4] = ListaE.get(i).getHora_cita(); 
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		tablaCitas.setModel(MiModelo);
		tablaCitas.getColumnModel().getColumn(0).setMaxWidth(110);  
		tablaCitas.getColumnModel().getColumn(3).setMaxWidth(110);  
		tablaCitas.getColumnModel().getColumn(4).setMaxWidth(110);  
 
	}
	void comboE(){
		citaN  = new CitasN();
		Especialidad = new ArrayList<>();
		Especialidad = citaN .ListaEspe();
		cmbEspecialidad.addItem("--SELECCIONAR");
		for (String x : Especialidad) {
			cmbEspecialidad.addItem(x);
		}
	}
	void comboPac(){
		citaN  = new CitasN();
		 Paciente = new ArrayList<>();
		 Paciente = citaN .ListaNombrePaciente();
		cmbPaciente.addItem("--SELECCIONAR");
		for (String x :  Paciente) {
			cmbPaciente.addItem(x);
		}
	}
	void comboMed(String id){
		citaN  = new CitasN();
		cmbMedico.removeAllItems();
		Medicos = new ArrayList<>();
		Medicos= citaN .ListaNombreMedico(id);
		cmbMedico.addItem("--SELECCIONAR");
		for (String x : Medicos) {
			cmbMedico.addItem(x);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == this.btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == this.btnEditar) {
			actionPerformedBtnEditar(arg0);
		}
		if (arg0.getSource() == this.btnListar) {
			actionPerformedBtnListar(arg0);
		}
		if (arg0.getSource() == this.btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == this.btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}
	void msj(String msj){
		JOptionPane.showMessageDialog(null, msj);
	}
	void habilitar(boolean b){
		txtid.setEditable(false);
		txtdetalle.setEditable(b);
		txtfecha.setEditable(b);
		txthora.setEditable(b);
		cmbEspecialidad.setEnabled(b);
		cmbMedico.setEnabled(b);
		cmbPaciente.setEnabled(b);
		btnCancelar.setEnabled(b);
		btnBorrar.setEnabled(!b);
		btnEditar.setEnabled(!b);
		btnGuardar.setEnabled(b);
		btnBuscar.setEnabled(!b);
		btnNuevo.setEnabled(!b);
	}
	void habilitarBtn(boolean b){
		btnNuevo.setEnabled(b);
		btnCancelar.setEnabled(!b);
		btnEditar.setEnabled(b);
		btnBorrar.setEnabled(b);
		btnGuardar.setEnabled(b);
	}
	void limpiar(){
		txtid.setText("");
		txtfecha.setText("");
		txthora.setText("");
		txtdetalle.setText("");
		cmbEspecialidad.setSelectedIndex(0);
		cmbMedico.setSelectedIndex(0);
		cmbPaciente.setSelectedIndex(0);
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		habilitar(true);
		limpiar();
		txtid.setText(citaN.generaCodigo());
		operacion ="N";
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		if(txtdetalle.getText().trim().length() != 0){
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la operacion?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				habilitar(false);
				limpiar();
				operacion ="";
			}else{
				txtdetalle.requestFocus();
			}
		}else{
			habilitar(false);
			limpiar();
			operacion ="";
		}
	}
	private int busca = 1;
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		if(!txtid.isEditable() && busca==1 ){
			txtid.setEditable(true);
			limpiar();
			txtid.requestFocus();
			habilitarBtn(false);
			busca=2;
		}
		else if( txtid.isEditable() && busca==2  ){
			Citas  c = citaN.Buscar(txtid.getText());
			if(c != null){
				devuelDatos(c);
				busca=1;
				txtid.setEditable(false);
				habilitar(false);
			}else{
				msj("No se encontraron datos.");
				busca=1;
				txtid.setEditable(false);
				txtid.setText("");
				habilitar(false);
			}
		}
	}
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == this.cmbEspecialidad) {
			itemStateChangedCmbEspecialidad(arg0);
		}
	}
	protected void itemStateChangedCmbEspecialidad(ItemEvent arg0) {
		comboMed(cmbEspecialidad.getSelectedItem().toString());
	}
	protected void actionPerformedBtnListar(ActionEvent arg0) {
		cargarDatos();
	}
	protected void actionPerformedBtnEditar(ActionEvent arg0) {
		if(cmbEspecialidad.getSelectedIndex()   == 0 && txtid.getText().trim().length() == 0){
			msj("No ha seleccionado una cita.");
		}else{
			habilitar(true);
			txtdetalle .requestFocus();
			operacion ="E";
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		//boton guardar
		 if(txtid.getText().trim().length() < 5  ){
			msj("El codigo no se genero intente nuevamente.");
		}else if(cmbEspecialidad.getSelectedIndex()==0){
			msj("Seleccione una especialidad");
		}else if(cmbMedico.getSelectedIndex()==0){
			msj("Seleccione medico");
		}else if(cmbPaciente.getSelectedIndex()==0){
			msj("Seleccione Paciente");
		}else if( !m.ValidarFecha(txtfecha.getText()) ){
			msj("Ingrese una fecha valida.");
		}else if(!m.fechaMayorHoy(txtfecha.getText())){
			msj("La reservas deden tener como minimo un dia de anticipación.");
		}else if(m.nombreDia(txtfecha.getText()) == "Domingo" || m.nombreDia(txtfecha.getText()) == "Sabado"  ){
			msj("Solo se Reservan Citas de Lunes a Viernes.");
		}else if(m.nombreDia(txtfecha.getText()) == "Domingo" || m.nombreDia(txtfecha.getText()) == "Sabado"  ){
			msj("Solo se Reservan Citas de Lunes a Viernes.");
		}else if( !m.horaValida(txthora.getText()) ){
			msj("La hora ingresada no es valida.");
		}else if( txtdetalle.getText().trim().length() < 5 ){
			msj("Indicar el detalle de la consulta.");
			txtdetalle .requestFocus();
		}else{
			guardar();
		}
		//ValidaCita(
		
   }
	
	private void guardar(){
		 
			Citas  objE = new  Citas();
			objE.setId_cita(txtid.getText());
			objE.setFecha_cita(txtfecha.getText());
			objE.setHora_cita(txthora.getText());
			objE.setId_medico(cmbMedico.getSelectedItem().toString());
			objE.setId_paciente(cmbPaciente.getSelectedItem().toString());
			objE.setDetalle(txtdetalle.getText());
			objE.setC_usuario(usuario);	
			if(citaN.ValidaCita(cmbMedico.getSelectedItem().toString(), txtfecha.getText(),txthora.getText())==0){
				if( operacion=="N"){
					if(citaN.agregar(objE)){
						cargarDatos();
						limpiar();
						habilitar(false);
						JOptionPane.showMessageDialog(null,"Registro con exito.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"Registro fallido.","Mensaje",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					if(citaN.modificar(objE)){
						cargarDatos();
						limpiar();
						habilitar(false);
						JOptionPane.showMessageDialog(null,"Editado correctamente.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"Edicion fallida.","Mensaje",JOptionPane.WARNING_MESSAGE);
					}	
				}
			}else{
				msj("Existe una cita registrada con la misma hora para el Medico.");
			}
 
	}
	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		if(txtid.getText().trim().length() ==0 && cmbMedico.getSelectedIndex() == 0){
			msj("Seleccione una cita.");
		}else{
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea elimnar la cita Nº :"+txtid.getText().trim()+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				if(citaN.eliminar(txtid.getText().trim())){
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
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == this.tablaCitas) {
			mouseClickedTablaCitas(arg0);
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
	protected void mouseClickedTablaCitas(MouseEvent arg0) {
		habilitar(false);
		busca = 1;
		int fila = tablaCitas.getSelectedRow();
		String id=(String) tablaCitas.getValueAt(fila, 0);
		Citas cita = citaN.Buscar(id);
		devuelDatos(cita);

	}
	
	void devuelDatos(Citas cita){
		txtid.setText(cita.getId_cita());
		txtdetalle.setText(cita.getDetalle());
		cmbSeleccion(cita.getId_especialidad(),cmbEspecialidad);
		cmbSeleccion(cita.getId_medico(),cmbMedico);
		cmbSeleccion(cita.getId_paciente(),cmbPaciente);
		txtfecha.setText(cita.getFecha_cita());
		txthora.setText(cita.getHora_cita());
	}
	 
	void cmbSeleccion(String id,JComboBox cmb){
		for (int i = 0; i <   cmb.getItemCount() ;i++) {
			 if (id.equals( cmb.getItemAt(i))) {
				 cmb.setSelectedIndex(i);
			   }
		}
	}
}
