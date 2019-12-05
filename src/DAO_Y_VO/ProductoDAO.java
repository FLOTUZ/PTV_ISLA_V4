package DAO_Y_VO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {
    private Connection conector = null;

    public ProductoVO getProductoByID(int idproducto) throws SQLException {
        ProductoVO producto = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "SELECT idproducto, sku,  nombre, enStock, precio_unitario, imagen" +
                        "FROM producto" +
                        "where idproducto = ?;";

        try {
            consulta = conector.prepareStatement(consultaSQL);

            //El 1 es el indice del primer signo de interrogación
            consulta.setInt(1, idproducto);

            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while (resultSet != null && resultSet.next()) {

                //Se crea el objeto con los datos que retorna la BD
                producto = new ProductoVO();
                producto.setId_producto(resultSet.getInt(1));
                producto.setSku(resultSet.getInt(2));
                producto.setNombre(resultSet.getString(3));
                producto.setEnStok(resultSet.getInt(4));
                producto.setPrecio_unitario(resultSet.getDouble(5));
                producto.setImagen(resultSet.getBlob(6));
            }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Hubo un error al leer la BD");
    }
        return producto;
    }

    public int altaProducto(ProductoVO producto){
        PreparedStatement objetoSQL = null;
        ResultSet generatedKeys = null;
        int id = 0;

        String inserta = "INSERT INTO producto (sku, nombre, enStock, precio_unitario, imagen) " +
                "VALUES (?, ?, ?, ?, ?);";

        try{
            conector.setAutoCommit(false);

            objetoSQL = conector.prepareStatement(inserta,PreparedStatement.RETURN_GENERATED_KEYS);

            objetoSQL.setInt(1, producto.getSku());
            objetoSQL.setString(2, producto.getNombre());
            objetoSQL.setInt(3, producto.getEnStok());
            objetoSQL.setDouble(4, producto.getPrecio_unitario());
            objetoSQL.setBlob(5, producto.getImagen());

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
                System.out.print("No se registró el producto");
            }
        }
        return id;
    }

    public ArrayList<ProductoVO> consultaMasiva() {
        ArrayList<ProductoVO> lista_de_productos= new ArrayList<>();

        ProductoVO producto = null;
        //Se encapsula query
        PreparedStatement consulta = null;
        //Se obtiene el set de resultado
        ResultSet resultSet = null;

        //Se crea el query para ponerlo en el objeto PrepareStatement
        String consultaSQL =
                "select idproducto, sku, nombre, enStock, precio_unitario, imagen " +
                        "from producto;";

        try {
            consulta = conector.prepareStatement(consultaSQL);
            resultSet = consulta.executeQuery();

            //Mientras haya registros de la BD se ejecuta este codigo
            while(resultSet != null && resultSet.next()){

                //Se crea el objeto con los datos que retorna la BD
                producto = new ProductoVO();
                producto.setId_producto(resultSet.getInt(1));
                producto.setSku(resultSet.getInt(2));
                producto.setNombre(resultSet.getString(3));
                producto.setEnStok(resultSet.getInt(4));
                producto.setPrecio_unitario(resultSet.getDouble(5));
                producto.setImagen(resultSet.getBlob(6));

                lista_de_productos.add(producto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al hacer la consulta");
        }
        return lista_de_productos;
    }



}
