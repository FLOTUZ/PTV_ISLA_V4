package DAO_Y_VO;

public class ClienteVO {

    private int id_cliente;
    private String nombre_cliente;
    private String apellidos;
    private String RFC;
    private int compras;

    public ClienteVO() {
    }

    public ClienteVO(int id_cliente, String nombre_cliente, String apellidos, String RFC, int compras) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellidos = apellidos;
        this.RFC = RFC;
        this.compras = compras;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public int getCompras() {
        return compras;
    }

    public void setCompras(int compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "id_cliente | nombre_cliente | apellidos | RFC | compras\n" +
                id_cliente +" | " + nombre_cliente +" | " + apellidos +" | " + RFC +" | " + compras;

    }



}
