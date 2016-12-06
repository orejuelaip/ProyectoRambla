package entidad;

public class Medico  extends Especialidades{
 	private String id_medico ;
	private String nombre_medico ;
	private String apellido_medico;
	private String f_nacimiento;
	private String email;
	private String n_documento;
	private String direccion;
	private String fecha_ingreso ;
	private String fecha_modificacion ;
	private String id_especialidad ;
	private String c_usuario ;
	private String l_activo ;
	private String fecha_debaja ;
	
	public Medico(){
		
	}

	public Medico(String id_medico, String nombre_medico, String apellido_medico, String f_nacimiento, String email,
			String n_documento, String direccion, String fecha_ingreso, String fecha_modificacion,
			String id_especialidad, String c_usuario, String l_activo, String fecha_debaja) {
		super();
		this.id_medico = id_medico;
		this.nombre_medico = nombre_medico;
		this.apellido_medico = apellido_medico;
		this.f_nacimiento = f_nacimiento;
		this.email = email;
		this.n_documento = n_documento;
		this.direccion = direccion;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_modificacion = fecha_modificacion;
		this.id_especialidad = id_especialidad;
		this.c_usuario = c_usuario;
		this.l_activo = l_activo;
		this.fecha_debaja = fecha_debaja;
	}

	public String getId_medico() {
		return id_medico;
	}

	public void setId_medico(String id_medico) {
		this.id_medico = id_medico;
	}

	public String getNombre_medico() {
		return nombre_medico;
	}

	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}

	public String getApellido_medico() {
		return apellido_medico;
	}

	public void setApellido_medico(String apellido_medico) {
		this.apellido_medico = apellido_medico;
	}

	public String getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getN_documento() {
		return n_documento;
	}

	public void setN_documento(String n_documento) {
		this.n_documento = n_documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public String getId_especialidad() {
		return id_especialidad;
	}

	public void setId_especialidad(String id_especialidad) {
		this.id_especialidad = id_especialidad;
	}

	public String getC_usuario() {
		return c_usuario;
	}

	public void setC_usuario(String c_usuario) {
		this.c_usuario = c_usuario;
	}

	public String getL_activo() {
		return l_activo;
	}

	public void setL_activo(String l_activo) {
		this.l_activo = l_activo;
	}

	public String getFecha_debaja() {
		return fecha_debaja;
	}

	public void setFecha_debaja(String fecha_debaja) {
		this.fecha_debaja = fecha_debaja;
	}
	
}
