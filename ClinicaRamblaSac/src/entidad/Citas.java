package entidad;

public class Citas {
	private String id_cita ;
	private String fecha_cita ;
	private String hora_cita ;
	private String id_medico ;
	private String id_paciente ;
	private String fecha_registro ;
	private String fecha_modificacion ;
	private String c_usuario ;
	private String l_activo ;
	private String fecha_debaja ;
	private String detalle ;
	private String id_especialidad;
	

	public Citas(){	}
	public Citas(String id_cita, String fecha_cita, String hora_cita, String id_medico, String id_paciente,
			String fecha_registro, String fecha_modificacion, String c_usuario, String l_activo, String fecha_debaja,
			String detalle,String id_especialidad) {
		this.id_cita = id_cita;
		this.fecha_cita = fecha_cita;
		this.hora_cita = hora_cita;
		this.id_medico = id_medico;
		this.id_paciente = id_paciente;
		this.fecha_registro = fecha_registro;
		this.fecha_modificacion = fecha_modificacion;
		this.c_usuario = c_usuario;
		this.l_activo = l_activo;
		this.fecha_debaja = fecha_debaja;
		this.detalle = detalle;
		this.id_especialidad = id_especialidad;
	}

	public String getId_cita() {
		return id_cita;
	}
	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}
	public String getFecha_cita() {
		return fecha_cita;
	}
	public void setFecha_cita(String fecha_cita) {
		this.fecha_cita = fecha_cita;
	}
	public String getHora_cita() {
		return hora_cita;
	}
	public void setHora_cita(String hora_cita) {
		this.hora_cita = hora_cita;
	}
	public String getId_medico() {
		return id_medico;
	}
	public void setId_medico(String id_medico) {
		this.id_medico = id_medico;
	}
	public String getId_paciente() {
		return id_paciente;
	}
	public void setId_paciente(String id_paciente) {
		this.id_paciente = id_paciente;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getId_especialidad() {
		return id_especialidad;
	}
	public void setId_especialidad(String id_especialidad) {
		this.id_especialidad = id_especialidad;
	}

}
