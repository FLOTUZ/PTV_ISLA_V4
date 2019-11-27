package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {

    // Librería de MySQL
    private String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    private String database = "ptv_isla";

    // Host
    private String hostname = "localhost";

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";


    // Nombre de usuario
    private String username = "root";

    // Clave de usuario
    private String password = "sintezoide";

    private Connection conn = null;

    public Connection conectarMySQL() {

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("\nConexion exitosa");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Algo no funcionó");
        }

        return conn;
    }

    public void cerrar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error en BD");
        }
    }
}
