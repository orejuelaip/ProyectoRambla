package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import coneccion.Coneccion;
import entidad.Paciente;
 

public class PacienteC {
	private ArrayList<Paciente> Lista ;
	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;
	public ArrayList<Paciente> Listar(){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call usp_listarPaciente();";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery(Sql);
			while (Rs.next()){
				Paciente objE = new Paciente();
				objE.setId_paciente(Rs.getString(1));
				objE.setNombre_paciente(Rs.getString(2));
				objE.setFecha_nacimiento(Rs.getString(3));
				objE.setNumero_documetnp(Rs.getString(4));
				objE.setX_direccion_real(Rs.getString(5));
				objE.setCorreo_electronico(Rs.getString(6));
				Lista.add(objE);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	public ArrayList<Paciente> Listar(String id){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call usp_listaPorID(?);";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,"%"+id+"%");
			Rs = Prm.executeQuery( );
			while (Rs.next()){
				Paciente objE = new Paciente();
				objE.setId_paciente(Rs.getString(1));
				objE.setNombre_paciente(Rs.getString(2));
				objE.setFecha_nacimiento(Rs.getString(3));
				objE.setNumero_documetnp(Rs.getString(4));
				objE.setX_direccion_real(Rs.getString(5));
				objE.setCorreo_electronico(Rs.getString(6));
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
			String sql ="CALL `usp_codigorPaciente`();";
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
	public Boolean modificar(Paciente objP){
		Boolean valor = false;
		try {
			String sql ="Call  usp_updatePaciente(?,?,?,?,?,?,?)";
			conn = new Coneccion().getConn();
			String[] objF = objP.getFecha_nacimiento().split("/");
			String  fecha = objF[2]+"-"+objF[1]+"-"+objF[0];
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1,objP.getId_paciente());
			Prm.setString(2,objP.getNombre_paciente());
			Prm.setString(3,objP.getApellido_paciente());
			Prm.setString(4,fecha);
			Prm.setString(5,objP.getNumero_documetnp());
			Prm.setString(6,objP.getX_direccion_real());
			Prm.setString(7,objP.getCorreo_electronico());
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
			String sql ="Call usp_deletePaciente(?);";
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

	public Boolean agregar(Paciente objP){
		Boolean valor = false;
		try {
			String sql ="call usp_agregarPaciente(?,?,?,?,?,?,?,?);";
			conn = new Coneccion().getConn();
			String[] objF = objP.getFecha_nacimiento().split("/");
			String  fecha = objF[2]+"-"+objF[1]+"-"+objF[0];
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1,objP.getId_paciente());
			Prm.setString(2,objP.getNombre_paciente());
			Prm.setString(3,objP.getApellido_paciente());
			Prm.setString(4,fecha);
			Prm.setString(5,objP.getNumero_documetnp());
			Prm.setString(6,objP.getX_direccion_real());
			Prm.setString(7,objP.getCorreo_electronico());
			Prm.setString(8, objP.getC_usuario());
			if(Prm.executeUpdate()!= 0) valor = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}
		return valor;
	}
 
	public Paciente Buscar (String id){
		Paciente objE = new Paciente();
		try {
			String Sql = "CALL `usp_buscarPaciente`(?);";
			conn = new Coneccion().getConn(); 
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,id);
			Rs = Prm.executeQuery();
			if(Rs.next()){
				objE.setId_paciente(Rs.getString(1));
				objE.setApellido_paciente(Rs.getString(2));
				objE.setNombre_paciente(Rs.getString(3));
				objE.setFecha_nacimiento(Rs.getString(4));
				objE.setNumero_documetnp(Rs.getString(5));
				objE.setX_direccion_real(Rs.getString(6));
				objE.setCorreo_electronico(Rs.getString(7));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objE;
	}
}
