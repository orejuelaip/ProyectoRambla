package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coneccion.Coneccion;
import entidad.usuario;

public class sesion {

	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;
	private ArrayList<usuario> Lista ;
	public Boolean ValidarUsuario(usuario objU){
		Boolean valor = false;
		try {
			String Sql = "CALL `usp_validaUser`(?,?);";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,objU.getUsuario());
			Prm.setString(2,objU.getClave());
			Rs = Prm.executeQuery();
			if(Rs.next()) valor = true;
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor= false;
		}
		return valor;
	}
	
	public  usuario  Listar(usuario objU){
		usuario usu = new usuario();
		try {
			String Sql = "CALL `usp_validaUser`(?,?);";
			conn = new Coneccion().getConn(); 
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,objU.getUsuario());
			Prm.setString(2,objU.getClave());
			Rs = Prm.executeQuery();
			if(Rs.next()){
				
				usu.setNombre(Rs.getString(3));
				usu.setClave(Rs.getString(2));
				usu.setUsuario(Rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usu;
	}

}
