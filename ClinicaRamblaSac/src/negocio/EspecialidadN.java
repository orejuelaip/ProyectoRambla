package negocio;
import java.util.ArrayList;

import datos.EspecialidadC;
import entidad.Especialidades;
public class EspecialidadN {
	
	private EspecialidadC objE ;

	
	public EspecialidadN() {
		objE = new EspecialidadC();
	}
	
	public ArrayList<Especialidades> Listar(){
		return objE.Listar();
	}
	public Boolean modificar(Especialidades datoE){
		return objE.modificar(datoE);
	}
	public Boolean  eliminar(String id){
		return objE.eliminar(id);
	}
	
	public Boolean agregar(Especialidades datoE){
		return objE.agregar(datoE);
	}
	public String generaCodigo(){
		return objE.generaCodigo();
	}
}
