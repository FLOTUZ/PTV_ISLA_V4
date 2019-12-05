package DAO_Y_VO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RenglonesDAO {
    private Connection conector = null;

    public RenglonesDAO(Connection conector) {
        this.conector = conector;
    }

    public RenglonesVO getRenglonById(int idRenglon){
    RenglonesVO renglon = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idcompra, cantidad, producto_idproducto, subtotal, notas_venta_idnotas_folio, notas_venta_clientes_idclientes" +
                        "from renglones" +
                        "where notas_venta_idnotas_folio = ?;";

        try {
            consulta = conector.prepareStatement(consultaSQL);

            //El 1 es el indice del primer signo de interrogación
            consulta.setInt(1, idRenglon);

            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                renglon = new RenglonesVO();
                renglon.setIdcompra(resultSet.getInt(1));
                renglon.setCantidad(resultSet.getInt(2));
                renglon.setProducto_idProducto(resultSet.getInt(3));
                renglon.setSubtotal(resultSet.getDouble(4));
                renglon.setNotas_venta_idnotas_folio(resultSet.getInt(5));
                renglon.setNotas_venta_clientes_idclientes(resultSet.getInt(6));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }


        return renglon;
    }

    public int altaRenglon(RenglonesVO renglon){
        PreparedStatement objetoSQL = null;
        ResultSet generatedKeys = null;
        int id = 0;

        String inserta = "INSERT INTO renglones (cantidad, producto_idproducto, subtotal, notas_venta_idnotas_folio, notas_venta_clientes_idclientes) " +
                "VALUES (?, ?, ?, ?, ?);";

        try{
            conector.setAutoCommit(false);

            objetoSQL = conector.prepareStatement(inserta,PreparedStatement.RETURN_GENERATED_KEYS);

            objetoSQL.setInt(1, renglon.getCantidad());
            objetoSQL.setInt(2, renglon.getProducto_idProducto());
            objetoSQL.setDouble(3, renglon.getSubtotal());
            objetoSQL.setInt(4, renglon.getNotas_venta_idnotas_folio());
            objetoSQL.setInt(5,renglon.getNotas_venta_clientes_idclientes());

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

    public ArrayList<RenglonesVO> consultaMasiva() {
        ArrayList<RenglonesVO> lista_de_renglones= new ArrayList<>();

        RenglonesVO renglon;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idcompra, cantidad, producto_idproducto, subtotal, notas_venta_idnotas_folio, notas_venta_clientes_idclientes " +
                        "from renglones";
        try {
            consulta = conector.prepareStatement(consultaSQL);
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                renglon = new RenglonesVO();
                renglon.setIdcompra(resultSet.getInt(1));
                renglon.setCantidad(resultSet.getInt(2));
                renglon.setProducto_idProducto(resultSet.getInt(3));
                renglon.setSubtotal(resultSet.getDouble(4));
                renglon.setNotas_venta_idnotas_folio(resultSet.getInt(5));
                renglon.setNotas_venta_clientes_idclientes(resultSet.getInt(6));

                lista_de_renglones.add(renglon);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
        }
        return lista_de_renglones;
    }

}
