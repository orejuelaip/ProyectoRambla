package negocio;
import java.util.ArrayList;

import datos.ConsultaC;
import entidad.Consulta;
public class ConsultaN {
	private ConsultaC C ;
	public ConsultaN(){
		C = new ConsultaC();
	}
	
	public ArrayList<String> ListaNombreMed()
	{
		return C.ListaNombreMed();
	}
	public ArrayList<Consulta> Listar(String id, String fec){
		return C.Listar(id, fec);
	}
	public ArrayList<Consulta> Listar(String fec){
		return C.Listar(fec);
	}
}
