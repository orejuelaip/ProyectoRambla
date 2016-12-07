package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import coneccion.Coneccion;
import entidad.Citas;
import entidad.Consulta;
public class ConsultaC {
	private ArrayList<Consulta> Lista ;
	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;

	public ArrayList<String> ListaNombreMed()
	{ ArrayList ListaM = new ArrayList<>();
	  String Sql = "Select concat(id_medico,':', apellido_medico,' ',nombre_medico) as nom from medicos where l_activo = 'S' order by nom ;";
		try {
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while(Rs.next()){
				ListaM.add(Rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	  return ListaM;
	}
	//
	public ArrayList<Consulta> Listar( String fec){
		Lista = new ArrayList<>();
		try {
			String [] fch = fec.split("/");
			String fecha = fch[2]+"-"+fch[1]+"-"+fch[0];
			String Sql = "Call usp_consulta2(?);";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1, fecha);
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Consulta objE = new Consulta();
				 objE.setPaciente(Rs.getString(3));
				 objE.setHoraC(Rs.getString(1));
				 objE.setF_registro(Rs.getString(4));
				 objE.setUsuario(Rs.getString(2));
				 
				Lista.add(objE);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	public ArrayList<Consulta> Listar(String id, String fec){
		Lista = new ArrayList<>();
		try {
			String [] cmb = id.split(":");
			String [] fch = fec.split("/");
			String fecha = fch[2]+"-"+fch[1]+"-"+fch[0];
			String Sql = "Call usp_listarConsulta1(?,?);";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1, cmb[0]);
			Prm.setString(2, fecha);
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Consulta objE = new Consulta();
				 objE.setPaciente(Rs.getString(3));
				 objE.setHoraC(Rs.getString(1));
				 objE.setF_registro(Rs.getString(4));
				 objE.setUsuario(Rs.getString(2));
				 
				Lista.add(objE);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	
}
