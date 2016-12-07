package negocio;
import java.util.ArrayList;

import datos.CitasC;
import entidad.Citas;
public class CitasN {
	private CitasC objC ;
	
	public CitasN(){
		objC = new CitasC();
	}
	public ArrayList<String> ListaEspe()
	{
		return objC.ListaEspe();
	}
	public ArrayList<String> ListaNombrePaciente()
	{
		return objC.ListaNombrePaciente();
	}
	public ArrayList<String> ListaNombreMedico(String id)
	{
		return objC.ListaNombreMedico(id);
	}
	public String generaCodigo(){
		return objC.generaCodigo();
	}
	public ArrayList<Citas> Listar(){
		return objC.Listar();
	}
	public Boolean agregar(Citas objE){
		return objC.agregar(objE);
	}
	public Boolean modificar(Citas objE){
		return objC.modificar(objE);
	}
	public Boolean  eliminar(String id){
		return objC.eliminar(id);
	}
	public Citas Buscar(String id){
		return objC.Buscar(id);
	}
	public int ValidaCita(String med,String fec,String hora){
		return objC.ValidaCita(med, fec,hora);
	}
}
