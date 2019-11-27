package DAO_Y_VO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection conector = null;

    public ClienteDAO(Connection conector) {
        this.conector = conector;
    }

    public ClienteVO getClienteById(int idCliente){
        ClienteVO cliente = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "SELECT idclientes, nombre, apellidos, RFC, compras FROM clientes " +
                        "where idclientes = ?";

        try {
            consulta = conector.prepareStatement(consultaSQL);

            //El 1 es el indice del primer signo de interrogación
            consulta.setInt(1, idCliente);

            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                cliente = new ClienteVO();
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setNombre_cliente(resultSet.getString(2));
                cliente.setApellidos(resultSet.getString(3));
                cliente.setRFC(resultSet.getString(4));
                cliente.setCompras(resultSet.getInt(5));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }


        return cliente;
    }

    public int altaCliente(ClienteVO cliente){
        PreparedStatement objetoSQL = null;
        ResultSet generatedKeys = null;
        int id = 0;

        String inserta = "INSERT INTO clientes (nombre, apellidos, RFC, compras) " +
                "VALUES (?, ?, ?, ?);";

        try{
            conector.setAutoCommit(false);

            objetoSQL = conector.prepareStatement(inserta,PreparedStatement.RETURN_GENERATED_KEYS);

            objetoSQL.setString(1, cliente.getNombre_cliente());
            objetoSQL.setString(2, cliente.getApellidos());
            objetoSQL.setString(3, cliente.getRFC());
            objetoSQL.setInt(4, cliente.getCompras());

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

    public ArrayList<ClienteVO> consultaMasiva() {
        ArrayList<ClienteVO> lista_de_clientes= new ArrayList<>();

        ClienteVO cliente = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "SELECT idclientes, nombre, apellidos, RFC, compras " +
                "FROM clientes ";

        try {
            consulta = conector.prepareStatement(consultaSQL);
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                cliente = new ClienteVO();
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setNombre_cliente(resultSet.getString(2));
                cliente.setApellidos(resultSet.getString(3));
                cliente.setRFC(resultSet.getString(4));
                cliente.setCompras(resultSet.getInt(5));

                lista_de_clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }
        return lista_de_clientes;
    }


}
