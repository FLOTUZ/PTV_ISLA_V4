package DAO_Y_VO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeseroDAO {

    private Connection conector = null;

    public MeseroDAO(Connection conector) {
        this.conector = conector;
    }

    public MeseroVO getMeseroById(int idMesero){
        MeseroVO mesero = new MeseroVO();
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idmesero, nombre, atendidos" +
                        "from mesero" +
                        "where idmesero = ?";

        try {
            consulta = conector.prepareStatement(consultaSQL);

            //El 1 es el indice del primer signo de interrogación
            consulta.setInt(1, idMesero);

            //Se ejecuta el query
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                mesero = new MeseroVO();
                mesero.setIdmesero(resultSet.getInt(1));
                mesero.setNombre(resultSet.getString(2));
                mesero.setAntendidos(resultSet.getInt(3));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }


        return mesero;
    }

    public int altaMesero(MeseroVO mesero){
        PreparedStatement objetoSQL = null;
        ResultSet generatedKeys = null;
        int id = 0;

        String inserta = "INSERT INTO mesero (nombre, atendidos) " +
                "VALUES (?, ?);";

        try{
            conector.setAutoCommit(false);

            objetoSQL = conector.prepareStatement(inserta,PreparedStatement.RETURN_GENERATED_KEYS);

            objetoSQL.setString(1, mesero.getNombre());
            objetoSQL.setInt(2, mesero.getAntendidos());

            //Se ejecuta la sentencia
            objetoSQL.executeUpdate();

            //Se recogen llaves generadas
            generatedKeys = objetoSQL.getGeneratedKeys();

            if (generatedKeys.next()) {
                id = generatedKeys.getConcurrency();
            }
            conector.commit();

        }catch(SQLException e){
            try{
                conector.rollback();
            }catch(SQLException ex1){
                System.out.println("Error en la transacción ");
                System.out.print("No se registró el cliente");
            }
        }
        return id;
    }

    public ArrayList<MeseroVO> consultaMasiva() {
        ArrayList<MeseroVO> lista_de_clientes= new ArrayList<>();

        MeseroVO mesero = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idmesero, nombre, atendidos " +
                        "from mesero; ";

        try {
            consulta = conector.prepareStatement(consultaSQL);
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                mesero = new MeseroVO();
                mesero.setIdmesero(resultSet.getInt(1));
                mesero.setNombre(resultSet.getString(2));
                mesero.setAntendidos(resultSet.getInt(3));

                lista_de_clientes.add(mesero);
            }

        } catch (SQLException ex) {
            //Imprime error en grafico
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD" +
                    "Cheque error en consola");
            //Imprime error en consola
            System.err.print(ex.toString());
        }
        return lista_de_clientes;
    }
}