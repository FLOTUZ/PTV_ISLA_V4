package DAO_Y_VO;

public class RenglonesVO {
    private int idcompra;
    private int cantidad;
    private int producto_idProducto;
    private double subtotal;
    private int notas_venta_idnotas_folio;
    private int notas_venta_clientes_idclientes;

    public RenglonesVO() {
    }

    public RenglonesVO(int idcompra, int cantidad, int producto_idProducto, double subtotal, int notas_venta_idnotas_folio, int notas_venta_clientes_idclientes) {
        this.idcompra = idcompra;
        this.cantidad = cantidad;
        this.producto_idProducto = producto_idProducto;
        this.subtotal = subtotal;
        this.notas_venta_idnotas_folio = notas_venta_idnotas_folio;
        this.notas_venta_clientes_idclientes = notas_venta_clientes_idclientes;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProducto_idProducto() {
        return producto_idProducto;
    }

    public void setProducto_idProducto(int producto_idProducto) {
        this.producto_idProducto = producto_idProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getNotas_venta_idnotas_folio() {
        return notas_venta_idnotas_folio;
    }

    public void setNotas_venta_idnotas_folio(int notas_venta_idnotas_folio) {
        this.notas_venta_idnotas_folio = notas_venta_idnotas_folio;
    }

    public int getNotas_venta_clientes_idclientes() {
        return notas_venta_clientes_idclientes;
    }

    public void setNotas_venta_clientes_idclientes(int notas_venta_clientes_idclientes) {
        this.notas_venta_clientes_idclientes = notas_venta_clientes_idclientes;
    }

    @Override
    public String toString() {
        return  "\nidcompra " + idcompra +
                "\ncantidad " + cantidad +
                "\nsubtotal " + subtotal;
    }

}
