package negocio;
import java.util.ArrayList;

import datos.MedicoC;
import entidad.Especialidades;
import entidad.Medico;
public class MedicoN {
	private MedicoC objM;
	public MedicoN(){
		objM = new MedicoC();
	}
	
	public String generaCodigo(){
		return objM.generaCodigo();
	}
	public Medico Buscar(String id){
		return objM.Buscar(id);
	}
	
	public Boolean agregar(Medico Med){
		return objM.agregar(Med);
	}
	public Boolean  eliminar(String id){
		return objM.eliminar(id);
	}
	public Boolean modificar(Medico med){
		return objM.modificar(med);
	}
	
	public ArrayList<Medico> Listar(String id){
		return objM.Listar(id);
	}
	public ArrayList<Medico> Listar(){
		return objM.ListarM();
	}
	public ArrayList<String> ListarEspe(){
		return objM.ListaEspe();
	}
}
