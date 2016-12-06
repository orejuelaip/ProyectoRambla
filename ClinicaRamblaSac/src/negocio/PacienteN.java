package negocio;
import java.util.ArrayList;

import datos.PacienteC;
import entidad.Paciente;
public class PacienteN {
	private PacienteC objP;
public PacienteN(){
	objP = new PacienteC();
}

public ArrayList<Paciente> Listar(){
	return objP.Listar();
}
public ArrayList<Paciente> Listar(String id){
	return objP.Listar(id);
}
public Boolean modificar(Paciente pac){
	return  objP.modificar(pac);
}

public Boolean  eliminar(String id){
	return  objP.eliminar(id);
}
public Boolean agregar(Paciente Pac){
	return  objP.agregar(Pac);
}
public  Paciente  Buscar(String id){
	return  objP.Buscar(id);
}
public String generaCodigo(){
	return objP.generaCodigo();
}
}
