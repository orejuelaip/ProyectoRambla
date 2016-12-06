package entidad;

public class Paciente {
	private String id_paciente ;
	private String nombre_paciente ;
	private String apellido_paciente ;
	private String fecha_nacimiento ;
	private String numero_documetnp ;
	private String x_direccion_real ;
	private String correo_electronico ;
	private String fecha_modificacion ;
	private String c_usuario ;
	private String l_activo ;
	private String fecha_debaja;
	public Paciente(){}
	public Paciente(String id_paciente, String nombre_paciente, String apellido_paciente, String fecha_nacimiento,
			String numero_documetnp, String x_direccion_real, String correo_electronico, String fecha_modificacion,
			String c_usuario, String l_activo, String fecha_debaja) {
		this.id_paciente = id_paciente;
		this.nombre_paciente = nombre_paciente;
		this.apellido_paciente = apellido_paciente;
		this.fecha_nacimiento = fecha_nacimiento;
		this.numero_documetnp = numero_documetnp;
		this.x_direccion_real = x_direccion_real;
		this.correo_electronico = correo_electronico;
		this.fecha_modificacion = fecha_modificacion;
		this.c_usuario = c_usuario;
		this.l_activo = l_activo;
		this.fecha_debaja = fecha_debaja;
	}

	public String getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(String id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getNombre_paciente() {
		return nombre_paciente;
	}

	public void setNombre_paciente(String nombre_paciente) {
		this.nombre_paciente = nombre_paciente;
	}

	public String getApellido_paciente() {
		return apellido_paciente;
	}

	public void setApellido_paciente(String apellido_paciente) {
		this.apellido_paciente = apellido_paciente;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNumero_documetnp() {
		return numero_documetnp;
	}

	public void setNumero_documetnp(String numero_documetnp) {
		this.numero_documetnp = numero_documetnp;
	}

	public String getX_direccion_real() {
		return x_direccion_real;
	}

	public void setX_direccion_real(String x_direccion_real) {
		this.x_direccion_real = x_direccion_real;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
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
