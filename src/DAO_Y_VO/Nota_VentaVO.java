package DAO_Y_VO;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Nota_VentaVO {
    private int id_notas_folio;
    private String fecha;
    private String hora;
    private int mesa;
    private Double total;
    private int clientes_idclientes;
    private int mesero_idmesero;

    public Nota_VentaVO() {
        clientes_idclientes = 1;
        mesero_idmesero =1;
        Date date = new Date();
        //Obtener la hora y formatearlo:
        DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
        hora = horaFormat.format(date);

        //Obtener la hora y formatearlo:
        DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha = fechaFormat.format(date);
    }

    public Nota_VentaVO(int id_notas_folio, String fecha, String hora, int mesa, Double total, int clientes_idclientes, int mesero_idmesero) {
        this.id_notas_folio = id_notas_folio;
        this.fecha = fecha;
        this.hora = hora;
        this.mesa = mesa;
        this.total = total;
        this.clientes_idclientes = clientes_idclientes;
        this.mesero_idmesero = mesero_idmesero;
    }

    public int getId_notas_folio() {
        return id_notas_folio;
    }

    public void setId_notas_folio(int id_notas_folio) {
        this.id_notas_folio = id_notas_folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getClientes_idclientes() {
        return clientes_idclientes;
    }

    public void setClientes_idclientes(int clientes_idclientes) {
        this.clientes_idclientes = clientes_idclientes;
    }

    public int getMesero_idmesero() {
        return mesero_idmesero;
    }

    public void setMesero_idmesero(int mesero_idmesero) {
        this.mesero_idmesero = mesero_idmesero;
    }

    @Override
    public String toString() {
        return
                "Folio:" + id_notas_folio + " " +
                "Fecha:" + fecha + " " +
                "Hora: "+ hora + " " +
                "Total: "+ total + " " +
                "Clientes_id_clientes: " +clientes_idclientes + " " +
                "Total: " + total + " " +
                "Mesero: " + mesero_idmesero
                ;
    }
}
