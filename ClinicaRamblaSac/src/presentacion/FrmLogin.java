package presentacion;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//daots
import negocio.validarN;
import entidad.usuario;
import javax.swing.JProgressBar;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class FrmLogin extends JFrame implements ActionListener, KeyListener {
	private JPanel panel;
	private JButton btnIniciar;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField txtuser;
	private JPasswordField txtpass;
	private usuario objU;
	private validarN objS ;
	private JProgressBar barraLogeo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmLogin dialog = new FrmLogin();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setType(Type.POPUP);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logo.png")));
		PanelImagen p = new PanelImagen();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 450, 300);
		setContentPane(p);
		p.setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.panel.setOpaque(false);
		this.panel.setBackground(UIManager.getColor("TextPane.inactiveBackground"));
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciar sesion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		this.panel.setBounds(41, 27, 343, 193);
		p.add(this.panel);
		this.panel.setLayout(null);
		
		this.lblUsuario = new JLabel("Usuario");
		this.lblUsuario.setForeground(new Color(0, 0, 128));
		this.lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
		this.lblUsuario.setBounds(32, 47, 94, 19);
		this.panel.add(this.lblUsuario);
		
		this.lblContrasea = new JLabel("Contrase\u00F1a");
		this.lblContrasea.setForeground(new Color(0, 0, 128));
		this.lblContrasea.setFont(new Font("SansSerif", Font.BOLD, 14));
		this.lblContrasea.setBounds(32, 101, 94, 19);
		this.panel.add(this.lblContrasea);
		
		this.txtuser = new JTextField();
		this.txtuser.setSelectionStart(15);
		this.txtuser.setToolTipText("Ingresar usuario");
		this.txtuser.setBounds(136, 48, 151, 20);
		this.panel.add(this.txtuser);
		this.txtuser.setColumns(10);
		
		this.btnIniciar = new JButton("Iniciar sesion");
		this.btnIniciar.setBackground(new Color(0, 191, 255));
		this.btnIniciar.addActionListener(this);
		this.btnIniciar.setForeground(new Color(255, 255, 255));
		this.btnIniciar.setBounds(96, 145, 199, 23);
		this.panel.add(this.btnIniciar);
		
		this.txtpass = new JPasswordField();
		this.txtpass.addKeyListener(this);
		this.txtpass.setToolTipText("Ingresar clave");
		this.txtpass.setBounds(136, 101, 151, 20);
		this.panel.add(this.txtpass);
		
		this.barraLogeo = new JProgressBar(0,100);
		this.barraLogeo.setBackground(new Color(255, 255, 255));
		this.barraLogeo.setForeground(new Color(50, 205, 50));
		this.barraLogeo .setStringPainted(true);
		this.barraLogeo.setVisible(false);
		this.barraLogeo.setBounds(323, 250, 121, 21);
		p.add(this.barraLogeo);
		objS  = new validarN();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnIniciar) {
			actionPerformedBtnIniciar(arg0);
		}
	}
	void cargarBarra( ){
		objU = new usuario();
		objU.setUsuario(txtuser.getText().trim());
		objU.setClave(txtpass.getText().trim());
		usuario objU1 =  objS.Listar(objU);
		
		
		this.dispose();
		FrmPrincipal frmP = new FrmPrincipal();
		frmP.setTitle("Usuario: "+objU1.getNombre());
		frmP.setVisible(true);
		frmP.usuario =objU1.getUsuario();
		frmP.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	protected void actionPerformedBtnIniciar(ActionEvent arg0) {
		ejecutar();
	}
	private void ejecutar(){
		deshabiliar(false);
		if(txtuser.getText().trim().length() != 0 && txtpass.getText().trim().length()!=0){
			objU = new usuario();
			
			objU.setUsuario(txtuser.getText().trim());
			objU.setClave(txtpass.getText().trim());
			if(objS.Validar(objU)){
				this.barraLogeo.setVisible(true);
				new Thread(new Hilo()).start();
			}else{
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecto.","Mensaje",JOptionPane.ERROR_MESSAGE);
				deshabiliar(true);
				txtpass.setText("");
				txtuser.requestFocus();
			}

		}
		else{
			JOptionPane.showMessageDialog(null, "Ingrese usuario y contraseña.","Aviso",JOptionPane.INFORMATION_MESSAGE);
			deshabiliar(true);
			txtuser.requestFocus();
		}
	}
	void deshabiliar(boolean b){
		txtuser.setEditable(b);
		txtpass.setEnabled(b);
		btnIniciar.setEnabled(b);
	}
	public class Hilo   implements   Runnable  {
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					if(i==99) 	cargarBarra();
					
					barraLogeo.setValue(i);
					barraLogeo.repaint();
					Thread.sleep(30);
				} catch (Exception e) {
					System.out.println("Error:"+e.getMessage());
				} 
			}
			
		}
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == this.txtpass) {
			keyReleasedTxtpass(arg0);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedTxtpass(KeyEvent e) {
		if(e.getKeyCode()==10){
			ejecutar();
		}
		
	}
}



 
