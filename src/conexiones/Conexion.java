package conexiones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class Conexion {
	
    public Conexion() {
    	
    }
    
    public Connection getConexion() throws SQLException,Exception{
    	
    	String url="jdbc:oracle:thin:VS2DAW26/VS2DAW26@80.28.158.14:1521:oradai";  //instancia azarquiel
    	//String url="jdbc:oracle:thin:VS2DAW26/VS2DAW26@10.0.1.12:1521:oradai";
      	//String url="jdbc:oracle:thin:VS2DAW26/VS2DAW26@localhost:1521:orcl";  //instancia local
        Connection con; 
        OracleDataSource ods;
        DataSource ds;
        
        try{
        	// Conexion tradicional sin uso del pool de conexiones
        
        	   ods=new OracleDataSource();
               ods.setURL(url);
               con=ods.getConnection();
             
      	   //Conexion usando el pool de conexiones definido en glassfish
      	   // asociado al nombre logico: jdbc/Biblioteca
           //haciendo uso de la interfaz JNDI para localizar el recurso.
         	/*
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("jdbc/Biblioteca");
            con=ds.getConnection();
           */
            DatabaseMetaData meta = con.getMetaData();
            //gets driver info:
            System.out.println("JDBC driver version is " + meta.getDriverVersion());
            System.out.println("Data Source definido y conexion establecida");
        }
        catch(SQLException sqle){
        	System.out.println(sqle.toString());
            throw sqle;
        }
        catch(Exception e){
        	System.out.println(e.toString());
            throw e;
        }
        return con;
    }


}
