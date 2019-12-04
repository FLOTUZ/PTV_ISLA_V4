package DAO_Y_VO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class NotaVentaDAO {
    private Connection conector = null;

    public NotaVentaDAO(Connection conector) {
        this.conector = conector;
    }

    public Nota_VentaVO getNota_ByID(int idCliente){
        Nota_VentaVO nota = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idnotas_folio, hora, mesa, total" +
                        "from notas_venta;";

        try {
            consulta = conector.prepareStatement(consultaSQL);

            //El 1 es el indice del primer signo de interrogación
            consulta.setInt(1, idCliente);

            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                nota = new Nota_VentaVO();
                nota.setId_notas_folio(resultSet.getInt(1));
                nota.setFecha(String.valueOf(resultSet.getDate(2)));
                nota.setHora(String.valueOf(resultSet.getTime(3)));
                nota.setMesa(resultSet.getInt(4));
                nota.setTotal(resultSet.getDouble(5));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }


        return nota;
    }

    public int nuevaNota(Nota_VentaVO nota){
        PreparedStatement objetoSQL = null;
        ResultSet generatedKeys = null;
        int id = 0;

        String inserta =
                "INSERT INTO notas_venta (fecha, hora, mesa, total, clientes_idclientes, mesero_idmesero) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try{
            conector.setAutoCommit(false);

            objetoSQL = conector.prepareStatement(inserta,PreparedStatement.RETURN_GENERATED_KEYS);

            //Se llena el objeto PreparedStatement
            objetoSQL.setDate(1, Date.valueOf(nota.getFecha()));
            objetoSQL.setTime(2, Time.valueOf(nota.getHora()));
            objetoSQL.setInt(3, nota.getMesa());
            objetoSQL.setDouble(4, nota.getTotal());
            objetoSQL.setInt(5, nota.getClientes_idclientes());
            objetoSQL.setInt(5, nota.getMesero_idmesero());

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
                System.out.print("No se registró el nota");
            }
        }
        return id;
    }

    public ArrayList<Nota_VentaVO> consultaMasiva() {
        ArrayList<Nota_VentaVO> lista_de_notas= new ArrayList<>();

        Nota_VentaVO nota = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "SELECT idnotas_folio, fecha, hora, mesa, total, clientes_idclientes, mesero_idmesero " +
                        "FROM notas_venta";
        try {
            consulta = conector.prepareStatement(consultaSQL);
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                nota = new Nota_VentaVO();
                nota.setId_notas_folio(resultSet.getInt(1));
                nota.setFecha(String.valueOf(resultSet.getDate(2)));
                nota.setHora(String.valueOf(resultSet.getTime(3)));
                nota.setMesa(resultSet.getInt(4));
                nota.setTotal(resultSet.getDouble(5));
                nota.setClientes_idclientes(resultSet.getInt(6));
                nota.setClientes_idclientes(resultSet.getInt(7));

                //Se agrega cada objeto nuevo al arraylist
                lista_de_notas.add(nota);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
            System.err.print(ex.toString());
        }
        return lista_de_notas;
    }
}
