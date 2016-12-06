package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.DebugGraphics;
import javax.swing.JMenu;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class FrmPrincipal extends JFrame implements WindowStateListener, ActionListener {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	public String usuario;
	 private int alt , anch;
	 private JMenu mnMantenimiento;
	 private JMenu mnConsultas;
	 private JMenu mnReportes;
	 private JMenuItem mntmCerrarSesion;
	 private JMenuItem mntmPacientes;
	 private JMenuItem mntmCitas;
	 private JMenuItem mntmMedicos;
	 private JMenuItem mntmEspecialidades;
	 private JMenuItem mntmConsultaPorMedico;
	 private JMenuItem mntmConsultarCitasPor;
	 private JMenuItem mntmCitas_1;
	 private FrmEspecialidades frm = new FrmEspecialidades();
	 private FrmPaciente frPac = new FrmPaciente();
	 private FrmMedico frmM = new FrmMedico();
	 private FrmCitas frmC = new FrmCitas();
	 private FrmConsultaMedico frmConsu = new FrmConsultaMedico();
	 private FrmBuscarPorFechas frmConsuFecha = new FrmBuscarPorFechas();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
 				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logo.png")));
		
		addWindowStateListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 430);
		
		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);
		
		this.mnMantenimiento = new JMenu("Administracion");
		this.menuBar.add(this.mnMantenimiento);
		
		this.mntmCitas = new JMenuItem("Citas");
		this.mntmCitas.addActionListener(this);
		this.mntmCitas.setIcon(new ImageIcon(getClass().getResource("/imagenes/calendar.png")));
		this.mnMantenimiento.add(this.mntmCitas);
		
		this.mntmEspecialidades = new JMenuItem("Especialidades");
		this.mntmEspecialidades.addActionListener(this);
		this.mntmEspecialidades.setIcon(new ImageIcon(getClass().getResource("/imagenes/especialidad.png")));
		this.mnMantenimiento.add(this.mntmEspecialidades);
		
		this.mntmMedicos = new JMenuItem("Medicos");
		this.mntmMedicos.addActionListener(this);
		this.mntmMedicos.setIcon(new ImageIcon(getClass().getResource("/imagenes/doctor.png")));
		this.mnMantenimiento.add(this.mntmMedicos);
		
		this.mntmPacientes = new JMenuItem("Pacientes");
		this.mntmPacientes.addActionListener(this);
		this.mntmPacientes.setIcon(new ImageIcon(getClass().getResource("/imagenes/17-32.png")));
		this.mnMantenimiento.add(this.mntmPacientes);
		
		this.mnConsultas = new JMenu("Consultas");
		this.menuBar.add(this.mnConsultas);
		
		this.mntmConsultaPorMedico = new JMenuItem("Consulta citas por Medico");
		this.mntmConsultaPorMedico.addActionListener(this);
		this.mntmConsultaPorMedico.setIcon(new ImageIcon(getClass().getResource("/imagenes/medicoSearch.png")));
		this.mnConsultas.add(this.mntmConsultaPorMedico);
		
		this.mntmConsultarCitasPor = new JMenuItem("Consultar citas por fecha");
		this.mntmConsultarCitasPor.addActionListener(this);
		this.mntmConsultarCitasPor.setIcon(new ImageIcon(getClass().getResource("/imagenes/xfecha.png")));
		this.mnConsultas.add(this.mntmConsultarCitasPor);
		
		this.mnReportes = new JMenu("Reportes");
		this.menuBar.add(this.mnReportes);
		
		this.mntmCitas_1 = new JMenuItem("Citas");
		this.mnReportes.add(this.mntmCitas_1);
		//menu
		this.mnArchivo = new JMenu("Salir");
		this.menuBar.add(this.mnArchivo);
		
		this.mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		this.mntmCerrarSesion.addActionListener(this);
		this.mntmCerrarSesion.setIcon(new ImageIcon(getClass().getResource("/imagenes/sesion.png")));
		this.mnArchivo.add(this.mntmCerrarSesion);
		//contenedor
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		//desktop
		this.desktopPane = new JDesktopPane();
		this.desktopPane.setAutoscrolls(true);
		this.desktopPane.setBackground(Color.GRAY);
		this.desktopPane.setBounds(0, 0,600, 400);
		desktopPane.setSize(600, 400);
		this.contentPane.add(this.desktopPane);
		desktopPane.add(frm);
		desktopPane.add(frPac);
		desktopPane.add(frmM);
		desktopPane.add(frmC);
		desktopPane.add(frmConsu);
		desktopPane.add(frmConsuFecha);
		 
	}
	public void windowStateChanged(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowStateChangedThis(arg0);
		}
	}

	protected void windowStateChangedThis(WindowEvent arg0) {
		anch = (int) this.getSize().getWidth();
		alt = (int) this.getSize().getHeight();
		this.desktopPane.setBounds(0, 0,anch,alt);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.mntmConsultarCitasPor) {
			frmConsuFecha.setVisible(true);
		 
		}
		if (arg0.getSource() == this.mntmConsultaPorMedico) {
			frmConsu.setVisible(true);
			frmConsu.usuario = this.usuario;
		}
		if (arg0.getSource() == this.mntmCitas) {
			frmC.setVisible(true);
			frmC.usuario = this.usuario;
		}
		if (arg0.getSource() == this.mntmMedicos) {
			frmM.setVisible(true);	
			frmM.usuario = this.usuario;
		}
		if (arg0.getSource() == this.mntmPacientes) {
			frPac.setVisible(true);
			frPac.usuario = this.usuario;
		}
		if (arg0.getSource() == this.mntmCerrarSesion) {
			this.setVisible(false);
			FrmLogin frml = new FrmLogin();
			frml.setVisible(true);
		}
		if (arg0.getSource() == this.mntmEspecialidades) {
			frm.setVisible(true);	
			frm.usuario = this.usuario;
		}
	}
 
 
}
