package negocio;
import java.util.ArrayList;

import datos.sesion;
import entidad.usuario;
public class validarN {
	private  sesion objS; 
	public validarN (){
		objS = new sesion();
	}
	public boolean Validar(usuario objU){
		return objS.ValidarUsuario(objU);
	}
	public  usuario  Listar(usuario objU){
		return objS.Listar(objU);
	}
}
