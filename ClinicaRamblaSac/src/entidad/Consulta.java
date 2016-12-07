package entidad;

import java.util.Date;

public class Consulta {
	private String paciente;
	private String fechaC;
	private String horaC;
	private String usuario;
	private String f_registro;
	public Consulta(){
		
	}
	public Consulta(String paciente, String fechaC, String horaC, String usuario, String f_registro) {
		this.paciente = paciente;
		this.fechaC = fechaC;
		this.horaC = horaC;
		this.usuario = usuario;
		this.f_registro = f_registro;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getFechaC() {
		return fechaC;
	}
	public void setFechaC(String fechaC) {
		this.fechaC = fechaC;
	}
	public String getHoraC() {
		return horaC;
	}
	public void setHoraC(String horaC) {
		this.horaC = horaC;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getF_registro() {
		return f_registro;
	}
	public void setF_registro(String f_registro) {
		this.f_registro = f_registro;
	}
	
	
	
}
