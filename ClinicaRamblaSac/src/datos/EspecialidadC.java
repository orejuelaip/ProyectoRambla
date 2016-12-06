package datos;
import java.util.ArrayList;
import java.sql.*;

import coneccion.Coneccion;
import entidad.Especialidades;
public class EspecialidadC {
	private ArrayList<Especialidades> Lista ;
	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;
	public ArrayList<Especialidades> Listar(){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call ListarEspecialidad();";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Especialidades objE = new Especialidades();
				objE.setId_especialidades(Rs.getString(1));
				objE.setNombre_especialidad(Rs.getString(2));
				Lista.add(objE);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}

	public String generaCodigo(){
		String c="";
		try {
			String sql ="CALL `usp_generarCodigo`();";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(sql);
			Rs = Prm.executeQuery();
			if (Rs.next()) 
				c=Rs.getString(1);
 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
	public Boolean modificar(Especialidades objE){
		Boolean valor = false;
		try {
			String sql ="Call updateEspecialidad(?,?);";
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1, objE.getId_especialidades());
			Prm.setString(2, objE.getNombre_especialidad());
			if(Prm.executeUpdate()!= 0)
				valor = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}

		return valor;
	}
	public Boolean  eliminar(String id){
		Boolean valor = false;
		try {
			String sql ="Call deleteEspecialidad(?);";
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1,id);
			if(Prm.executeUpdate()!= 0)
				valor = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}
		return valor;
	}

	public Boolean agregar(Especialidades objE){
		Boolean valor = false;
		try {
			String sql ="call agregarEspecialidad(?,?,?);";
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1, objE.getNombre_especialidad());
			Prm.setString(2, objE.getC_usuario());
			Prm.setString(3, objE.getId_especialidades());
			if(Prm.executeUpdate()!= 0)
				valor = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}
		return valor;
	}
}
