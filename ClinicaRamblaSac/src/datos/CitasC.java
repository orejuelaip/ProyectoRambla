package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coneccion.Coneccion;
import entidad.Citas;
import entidad.Medico;

public class CitasC {
	private ArrayList<Citas> Lista ;
	private PreparedStatement Prm = null;
	private Connection conn = null;
	private ResultSet Rs = null;
	
	public ArrayList<Citas> Listar(){
		Lista = new ArrayList<>();
		try {
			String Sql = "Call usp_listarCitas ;";
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while (Rs.next()){
				Citas objE = new Citas();
				 objE.setId_cita(Rs.getString(1));
				 objE.setId_paciente(Rs.getString(2));
				 objE.setId_medico(Rs.getString(3));
				 objE.setFecha_cita(Rs.getString(4));
				 objE.setHora_cita(Rs.getString(5));
				 objE.setFecha_registro(Rs.getString(6));
				Lista.add(objE);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Lista;
	}
	
	public int ValidaCita(String med,String fec,String hora){
		int b=0;
		String [] cmb = med.split(":");
		String [] fch = fec.split("/");
		String fecha  = fch[2]+"-"+fch[1]+"-"+fch[0];
		try {
			String Sql ;
			Sql="call usp_validarCita(?,?)";
			conn = new Coneccion().getConn(); 
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,cmb[0]);
			Prm.setString(2,fecha+" "+hora);
			Rs = Prm.executeQuery();
			if(Rs.next()) b=1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;
	}
	
	public Citas Buscar(String id){
		Citas  objM = new Citas();
		try {
			String Sql = "CALL usp_buscarCita(?);";
			conn = new Coneccion().getConn(); 
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,id);
			Rs = Prm.executeQuery();
			if(Rs.next()){ 
				 objM.setId_cita(Rs.getString(1));
				 objM.setId_paciente(Rs.getString(2));
				 objM.setId_medico(Rs.getString(3));
				 objM.setFecha_cita(Rs.getString(4));
				 objM.setHora_cita(Rs.getString(5));
				 objM.setDetalle(Rs.getString(7));
				 objM.setId_especialidad(Rs.getString(8));
			}else{
				objM =null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objM;
	}
	
	public Boolean modificar(Citas objC){
		Boolean valor = false;
		try {
			String sql  = "Call usp_modificarCita(?,?,?,?,?,?,?,?)";
			String[] med = objC.getId_medico().split(",");
			String[] pac = objC.getId_paciente().split(",");
			String nomM = med[1].trim();
			String apeM = med[0].trim();
			String nomP = pac[1].trim();
			String apeP = pac[0].trim();
			String[] objI = objC.getFecha_cita().split("/");
			String  fechaI = objI[2]+"-"+objI[1]+"-"+objI[0];
			conn = new Coneccion().getConn();
			Prm =  conn.prepareStatement(sql);
			 Prm.setString(1, objC.getId_cita());
			 Prm.setString(2,fechaI+" "+objC.getHora_cita());
			 Prm.setString(3,apeM);
			 Prm.setString(4,nomM);
			 Prm.setString(5, apeP);
			 Prm.setString(6,nomP);
			 Prm.setString(7,objC.getC_usuario());
			 Prm.setString(8,objC.getDetalle());
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
			String sql ="Call deleteCitas(?);";
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

	public Boolean agregar(Citas objE){
		Boolean valor = false;
		try {
			
			String sql ="call agregarCitas(?,?,?,?,?,?);";
			conn = new Coneccion().getConn();
			String[] objI = objE.getFecha_cita().split("/");
			String  fechaI = objI[2]+"-"+objI[1]+"-"+objI[0];
			Prm =  conn.prepareStatement(sql);
			Prm.setString(1, fechaI+" "+objE.getHora_cita());
			Prm.setString(2, objE.getId_medico());
			Prm.setString(3, objE.getId_paciente());
			Prm.setString(4, objE.getC_usuario());
			Prm.setString(5, objE.getId_cita());
			Prm.setString(6, objE.getDetalle());
			if(Prm.executeUpdate()!= 0) valor = true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			valor = false;
		}
		return valor;
	}
	
	public ArrayList<String> ListaNombreMedico(String id)
	{ ArrayList ListaC = new ArrayList<>();
	  String Sql = "Call usp_cmbMedicos(?);";
		try {
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Prm.setString(1,id);
			Rs = Prm.executeQuery();
			while(Rs.next()){
				ListaC.add(Rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	  return ListaC;
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
	
	public ArrayList<String> ListaNombrePaciente()
	{ ArrayList ListaC = new ArrayList<>();
	  String Sql = "Call usp_cmbPaciente();";
		try {
			conn = new Coneccion().getConn();	
			Prm = conn.prepareStatement(Sql);
			Rs = Prm.executeQuery();
			while(Rs.next()){
				ListaC.add(Rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	  return ListaC;
	}
	
	public String generaCodigo(){
		String c="";
		try {
			String sql ="CALL usp_codigoCita();";
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
