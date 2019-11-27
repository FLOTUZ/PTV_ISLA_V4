package DAO_Y_VO;

import java.sql.Blob;

public class ProductoVO {
    private  int id_producto;
    private int sku;
    private  String enStok;
    private Double precio_unitario;
    private Blob imagen;

    ProductoVO() {
    }

    public ProductoVO(int id_producto, int sku, String enStok, Double precio_unitario, Blob imagen) {
        this.id_producto = id_producto;
        this.sku = sku;
        this.enStok = enStok;
        this.precio_unitario = precio_unitario;
        this.imagen = imagen;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getEnStok() {
        return enStok;
    }

    public void setEnStok(String enStok) {
        this.enStok = enStok;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        String prod = null;
        prod =
                "Id Producto: " + id_producto + "" +
                        "SKU: " + sku + "" +
                        "En Stok: " + enStok + "" +
                        "Precio unitario: " + precio_unitario + "" +
                        "Imagen: Te la quedo a deber krnal ;)";
        return prod;
    }
}
