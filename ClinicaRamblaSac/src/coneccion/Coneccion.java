package coneccion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;;
public class Coneccion {

	public Connection getConn() {
		Connection Conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_rambla","root","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch ( SQLException e) {
			e.printStackTrace();
		}
		return Conn;

	}

}
