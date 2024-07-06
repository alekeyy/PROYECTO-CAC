package ar.com.proyectocac24100.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionAdmin {
     public static  Connection conectar() {
        //logica para obtener la conexion
        String url = "jdbc:mysql://127.0.0.1:3306/proyecto-cac?serverTimeZone=UTC&userSSL=false";
        String user = "root";
        String password = "Mendoza2021$";
     
        String driver = "com.mysql.cj.jdbc.Driver";

        //me conecto a la db
        Connection connection = null;

        //como la Conecction puede dar un error try/catch
        try {
            //forName es un metodo estatico: no necesita una instancia para usar dicho metodo
            Class.forName((driver));
            //una vez pasado el driver de coneccion, ya puedo conectarme a la db
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("No se ha podido hacer la conexion con la Db" + e.getMessage());
        }

        return connection;
    }

    public static void desconectar(Connection connection) {        
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }      
    }

}
