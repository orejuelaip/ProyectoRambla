package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//importando datos 
import entidad.Especialidades;
import negocio.EspecialidadN;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmEspecialidades extends JInternalFrame implements ActionListener, MouseListener {
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txtid;
	private JLabel lblNombre;
	private JTextField txtnom;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JPanel panel_1;
	private JTable table;
	private JScrollPane scrollPane;
	//Datos de tablas
	private ArrayList<Especialidades> ListaE;
	private String Columnas[]={"Codigo","Especialidad"};
	private Object Filas[][];
	private EspecialidadN objEN;
	private String operacion ;
	public String usuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEspecialidades frame = new FrmEspecialidades();
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
		EspecialidadN objE = new EspecialidadN();
		ListaE = new ArrayList<>();
		ListaE =objE.Listar();
		Filas = new Object[ListaE.size()][2];	
		for(int i = 0; i < ListaE.size(); i++){
			Filas[i][0] = ListaE.get(i).getId_especialidades();
			Filas[i][1] = ListaE.get(i).getNombre_especialidad(); 
		}
		DefaultTableModel MiModelo = new DefaultTableModel(Filas,Columnas);
		table.setModel(MiModelo);
		table.getColumnModel().getColumn(0).setMaxWidth(130);  
	}
	public FrmEspecialidades() {
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Especialidades");
		this.setBounds(0, 0, 632, 511);
		this.setClosable(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = getSize();
		setLocation((pantalla.width - ventana.width) / 2,
					(pantalla.height - ventana.height) / 2);
		this.setPreferredSize(new java.awt.Dimension(649, 423));
		getContentPane().setLayout(null);
		this.panel = new JPanel();
		this.panel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ingresar especialidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		this.panel.setBounds(20, 22, 548, 142);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		this.lblNewLabel = new JLabel("CODIGO :");
		this.lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNewLabel.setBounds(66, 41, 108, 14);
		this.panel.add(this.lblNewLabel);
		this.txtid = new JTextField();
		this.txtid.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		this.txtid.setToolTipText("Codigo");
		this.txtid.setEditable(false);
		this.txtid.setBounds(194, 38, 126, 20);
		this.panel.add(this.txtid);
		this.txtid.setColumns(10);
		this.lblNombre = new JLabel("ESPECIALIDAD :");
		this.lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.lblNombre.setBounds(66, 66, 118, 30);
		this.panel.add(this.lblNombre);
		this.txtnom = new JTextField();
		this.txtnom.setEditable(false);
		this.txtnom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		this.txtnom.setToolTipText("Ingresar la especialidad");
		this.txtnom.setBounds(194, 73, 313, 20);
		this.panel.add(this.txtnom);
		this.txtnom.setColumns(10);
		this.panel_1 = new JPanel();
		this.panel_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.panel_1.setBorder(new TitledBorder(null, "Lista de Especialidades", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		this.panel_1.setBounds(20, 175, 548, 295);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.table = new JTable();
		this.table.addMouseListener(this);
		this.scrollPane.setViewportView(this.table);
		
		this.btnNuevo = new JButton("");
		this.btnNuevo.addActionListener(this);
		this.btnNuevo.setBounds(578, 28, 32, 30);
		getContentPane().add(this.btnNuevo);
		this.btnNuevo.setToolTipText("Nuevo");
		this.btnNuevo.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
		this.btnNuevo.setBackground(Color.WHITE);
		
		this.btnGuardar = new JButton("");
		this.btnGuardar.addActionListener(this);
		this.btnGuardar.setEnabled(false);
		this.btnGuardar.setBounds(578, 88, 32, 30);
		getContentPane().add(this.btnGuardar);
		this.btnGuardar.setToolTipText("Guardar");
		this.btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/guardar.png")));
		this.btnGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		this.btnModificar = new JButton("");
		this.btnModificar.addActionListener(this);
		this.btnModificar.setBounds(578, 58, 32, 30);
		getContentPane().add(this.btnModificar);
		this.btnModificar.setToolTipText("Modificar");
		this.btnModificar.setIcon(new ImageIcon(getClass().getResource("/imagenes/ediatr.png")));
		
		this.btnCancelar = new JButton("");
		this.btnCancelar.setEnabled(false);
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setBounds(578, 119, 32, 30);
		getContentPane().add(this.btnCancelar);
		this.btnCancelar.setToolTipText("Cancelar");
		this.btnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Cancel.png")));
		
		this.btnEliminar = new JButton("");
		this.btnEliminar.addActionListener(this);
		this.btnEliminar.setBounds(578, 149, 32, 30);
		getContentPane().add(this.btnEliminar);
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
		cargarDatos();
		objEN = new EspecialidadN();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == this.btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == this.btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == this.btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		//boton nuevo
		habilitar(true);
		operacion ="N";
		txtid.setText(objEN.generaCodigo());
		txtnom.setText("");
		txtnom.requestFocus();
	}
	void habilitar(boolean b){
		btnCancelar.setEnabled(b);
		btnEliminar.setEnabled(!b);
		btnGuardar.setEnabled(b);
		btnModificar.setEnabled(!b);
		btnNuevo.setEnabled(!b);
		txtnom.setEditable(b);
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() != 0){
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la operacion?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				habilitar(false);
				txtnom.setText("");
				txtid.setText("");
				operacion ="";
			}else{
				txtnom.requestFocus();
			}
		}else{
			habilitar(false);
			txtnom.setText("");
			txtid.setText("");
			operacion ="";
		}
	}
	void msj(String msj){
		JOptionPane.showMessageDialog(null, msj,"Advertencia",JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		//boton editar
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("No ha seleccionado una especialidad.");
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
				Especialidades objE = new Especialidades();
				objE.setC_usuario(usuario);
				objE.setId_especialidades(txtid.getText().trim());
				objE.setNombre_especialidad(txtnom.getText().trim());
				if( operacion=="N"){
					if(objEN.agregar(objE)){
						cargarDatos();
						txtid.setText("");
						txtnom.setText("");
						habilitar(false);
						JOptionPane.showMessageDialog(null,"Registro con exito.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"Registro fallido.","Mensaje",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					if(objEN.modificar(objE)){
						cargarDatos();
						txtid.setText("");
						txtnom.setText("");
						habilitar(false);
						JOptionPane.showMessageDialog(null,"Editado correctamente.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"Edicion fallida.","Mensaje",JOptionPane.WARNING_MESSAGE);
					}
				}
				
				
			}
	  
		
	}
	
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		if(txtnom.getText().trim().length() ==0 && txtid.getText().trim().length() == 0){
			msj("No ha seleccionado una especialidad.");
		}else{
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea elimnar la especialida "+txtnom.getText().trim()+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(resp == 0){
				if(objEN.eliminar(txtid.getText().trim())){
					cargarDatos();
					txtid.setText("");
					txtnom.setText("");
					JOptionPane.showMessageDialog(null,"Elimnado correctamente.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(null,"Error al eliminar.","Mensaje",JOptionPane.WARNING_MESSAGE);
				}
				
			}else{
				msj("Se descarto la eliminacion.");
				txtid.setText("");
				txtnom.setText("");
			}
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == this.table) {
			mouseClickedTable(arg0);
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
	protected void mouseClickedTable(MouseEvent arg0) {
		int fila = table.getSelectedRow();
		String id = (String) table.getValueAt(fila, 0);
		String nombre = (String)  table.getValueAt(fila, 1);
		txtid.setText(id);
		txtnom.setText(nombre);
	}
}
