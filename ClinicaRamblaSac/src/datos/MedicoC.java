package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//*****datos****
import coneccion.Coneccion;
import entidad.Medico;
 

public class MedicoC   {
	private ArrayList<Medico> Lista ;
	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;
	
	
	public ArrayList<Medico> ListarM(){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call usp_listarMedico();";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Medico objM = new Medico();
				 objM .setId_medico(Rs.getString(1));
				 objM .setApellido_medico(Rs.getString(2));
				 objM .setF_nacimiento(Rs.getString(3));
				 objM .setEmail(Rs.getString(4));
				 objM .setN_documento(Rs.getString(5));
				 objM .setDireccion(Rs.getString(6));
				 objM .setId_especialidad(Rs.getString(7));
				 objM.setNombre_especialidad(Rs.getString(8));
				Lista.add(objM);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	
	public ArrayList<String> ListaEspe()
	{ ArrayList ListaC = new ArrayList<>();
	  String Sql = "Call ListarEspecialidad();";
		try {
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while(Rs.next()){
				ListaC.add(Rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	  return ListaC;
	}
	public ArrayList<Medico> Listar(String id){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call usp_listarMedicoPorId(?);";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,"%"+id+"%");
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Medico objM = new Medico();
				 objM .setId_medico(Rs.getString(1));
				 objM .setNombre_medico(Rs.getString(2));
				 objM .setApellido_medico(Rs.getString(3));
				 objM .setF_nacimiento(Rs.getString(4));
				 objM .setEmail(Rs.getString(5));
				 objM .setN_documento(Rs.getString(6));
				 objM .setDireccion(Rs.getString(7));
 				 objM.setNombre_especialidad(Rs.getString(8));
 				 objM.setL_activo(Rs.getString(9));
 				 objM.setFecha_ingreso(Rs.getString(10));
				 Lista.add(objM);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	public Boolean modificar(Medico objM){
		Boolean valor = false;
		try {
			String sql ="Call usp_modificarMedico(?,?,?,?,?,?,?,?,?,?)";
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			String[] objF = objM.getF_nacimiento().split("/");
			String  fechan = objF[2]+"-"+objF[1]+"-"+objF[0];
			String[] objI = objM.getFecha_ingreso().split("/");
			String  fechaI = objI[2]+"-"+objI[1]+"-"+objI[0];
			Prm.setString(1, objM.getId_medico ());
			Prm.setString(2, objM.getNombre_medico());
			Prm.setString(3, objM.getApellido_medico());
			Prm.setString(4, fechan);
			Prm.setString(5, objM.getEmail());
			Prm.setString(6, objM.getN_documento());
			Prm.setString(7, objM.getDireccion());
			Prm.setString(8, objM.getNombre_especialidad());
			Prm.setString(9, fechaI);
			Prm.setString(10, objM.getL_activo());
		 	if(Prm.executeUpdate()!= 0) valor = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}

		return valor;
	}
	public Boolean  eliminar(String id){
		Boolean valor = false;
		try {
			String sql ="Call usp_eliminarMedico(?);";
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

	public Boolean agregar(Medico objM){
		Boolean valor = false;
		try {
			String sql ="call usp_agregarMedico(?,?,?,?,?,?,?,?,?,?,?);";
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			String[] objF = objM.getF_nacimiento().split("/");
			String  fechan = objF[2]+"-"+objF[1]+"-"+objF[0];
			String[] objI = objM.getFecha_ingreso().split("/");
			String  fechaI = objI[2]+"-"+objI[1]+"-"+objI[0];
			Prm.setString(1, objM.getId_medico ());
			Prm.setString(2, objM.getNombre_medico());
			Prm.setString(3, objM.getApellido_medico());
			Prm.setString(4,  fechan );
			Prm.setString(5, objM.getEmail());
			Prm.setString(6, objM.getN_documento());
			Prm.setString(7, objM.getDireccion());
			Prm.setString(8, objM.getNombre_especialidad());
			Prm.setString(9, objM.getC_usuario());
			Prm.setString(10, fechaI);
			Prm.setString(11,objM.getL_activo());
			if(Prm.executeUpdate()!= 0)
				valor = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}
		return valor;
	}
	
	public Medico Buscar(String id){
		Medico  objM = new Medico ();
		try {
			String Sql = "CALL usp_listarMedicoPorId(?);";
			conn = new Coneccion().getConn(); 
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,id);
			Rs = Prm.executeQuery();
			if(Rs.next()){
				 objM .setId_medico(Rs.getString(1));
				 objM .setNombre_medico(Rs.getString(2));
				 objM .setApellido_medico(Rs.getString(3));
				 objM .setF_nacimiento(Rs.getString(4));
				 objM .setEmail(Rs.getString(5));
				 objM .setN_documento(Rs.getString(6));
				 objM .setDireccion(Rs.getString(7));
				 objM .setNombre_especialidad(Rs.getString(8));
				 objM.setL_activo(Rs.getString(9));
				 objM.setFecha_ingreso(Rs.getString(10));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objM;
	}
	
	public String generaCodigo(){
		String c="";
		try {
			String sql ="CALL usp_codigoMedico();";
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
	
}
