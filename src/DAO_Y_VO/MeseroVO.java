package DAO_Y_VO;

public class MeseroVO {
    int idmesero;
    String nombre;
    int antendidos;

    MeseroVO() {
    }

    MeseroVO(int idmesero, String nombre, int antendidos) {
        this.idmesero = idmesero;
        this.nombre = nombre;
        this.antendidos = antendidos;
    }

    public int getIdmesero() {
        return idmesero;
    }

    public void setIdmesero(int idmesero) {
        this.idmesero = idmesero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAntendidos() {
        return antendidos;
    }

    public void setAntendidos(int antendidos) {
        this.antendidos = antendidos;
    }

    @Override
    public String toString() {
        return "id_mesero " + idmesero +
               "Nombre='" + nombre +
               "Antendidos=" + antendidos;
    }
}