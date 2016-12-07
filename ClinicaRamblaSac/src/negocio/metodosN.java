package negocio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class metodosN {
	
	public  String  fechaHoy(){
		Date fecha = new Date();
		SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		String F =FormatoFecha.format(fecha).toString();
		return F;
		
	}
	public boolean ValidarFecha(String f){
		boolean b= false;
		try {
			String[] objI = f.split("/");
			if( Integer.parseInt(objI[0]) > 0 && Integer.parseInt(objI[0]) < 31 ){
				 if( Integer.parseInt(objI[1]) > 0 && Integer.parseInt(objI[1]) < 13 ){
					 if( Integer.parseInt(objI[2]) > 1900 && Integer.parseInt(objI[2]) < 2020 ){
						 b = true;
					 }else b= false;
				 }else b= false;
			}else b= false;
			
		} catch (Exception e) {
			b= false;
		}
		return b;
	}
	
	
	public boolean fechaMayorHoy(String f){
		boolean b= false;
		String[] objI = f.split("/");
		String[] objH = fechaHoy().split("/");
		if(  Integer.parseInt(objI[2]) >= Integer.parseInt(objH[2]) ){
			if(Integer.parseInt(objI[1]) > Integer.parseInt(objH[1]) ){
				b= true	;
			}else if(Integer.parseInt(objI[1]) == Integer.parseInt(objH[1]) ){
				if(Integer.parseInt(objI[0]) > Integer.parseInt(objH[0]) ){
					b= true;
				}else{
					b= false;
				}
			}else{
				b= false;
			}
		}else{
			b= false;
		}
			
		return b;
	}
	public boolean horaValida(String f){
		boolean b= false;
		try {
			String[] objI = f.split(":");
			if(Integer.parseInt(objI[0]) < 0 || Integer.parseInt(objI[0]) > 24  ){
				b = false;
			}else if(Integer.parseInt(objI[1]) < 0 || Integer.parseInt(objI[1]) > 59 ){
				b = false;
			}else{
				b = true;
			}
		} catch (Exception e) {
			b = false;
		}	
		return b;
	
	}
	public String nombreDia(String fecha){
		try {
			String  nombre;
			Calendar fechaCalendario = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaActual = null;
			fechaActual = df.parse(fecha);
			fechaCalendario.setTime(fechaActual);
			int diaSemana = fechaCalendario.get(Calendar.DAY_OF_WEEK);
			
		
	 		
	 		
			String[] strDays = new String[]{
					"Domingo",
					"Lunes",
					"Martes",
					"Miercoles",
					"Jueves",
					"Viernes",
					"Sabado"};
			nombre =strDays[diaSemana - 1];  
			return nombre;
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	
 		
		
	}
}
