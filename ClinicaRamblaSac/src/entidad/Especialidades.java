package entidad;

public class Especialidades {
	private String id_especialidades ;
	private String nombre_especialidad;
	private String c_usuario ;
	public Especialidades(){
		
	}
	public Especialidades(String id_especialidades, String nombre_especialidad, String c_usuario) {

		this.id_especialidades = id_especialidades;
		this.nombre_especialidad = nombre_especialidad;
		this.c_usuario = c_usuario;
	}
	public String getId_especialidades() {
		return id_especialidades;
	}
	public void setId_especialidades(String id_especialidades) {
		this.id_especialidades = id_especialidades;
	}
	public String getNombre_especialidad() {
		return nombre_especialidad;
	}
	public void setNombre_especialidad(String nombre_especialidad) {
		this.nombre_especialidad = nombre_especialidad;
	}
	public String getC_usuario() {
		return c_usuario;
	}
	public void setC_usuario(String c_usuario) {
		this.c_usuario = c_usuario;
	}

}
