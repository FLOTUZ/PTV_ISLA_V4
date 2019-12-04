package main;


import DAO_Y_VO.ClienteDAO;
import DAO_Y_VO.ClienteVO;
import Conector.Conector;
import DAO_Y_VO.NotaVentaDAO;
import DAO_Y_VO.Nota_VentaVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static Conector con = new Conector();

    public static void main(String[] args) {

        //altaNota();

    }

    private static void altaNota(int cliente ,int mesa, Double total) {
        Nota_VentaVO nota = new Nota_VentaVO();
        NotaVentaDAO inserta_en_bd = null;
        String fecha;
        String hora;

        Date date = new Date();
    //Obtener la hora y formatearlo:
        DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
        hora = horaFormat.format(date);

    //Obtener la hora y formatearlo:
        DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha = fechaFormat.format(date);

        nota.setClientes_idclientes(cliente);
        nota.setTotal(total);
        nota.setMesa(mesa);
        nota.setFecha(fecha);
        nota.setHora(hora);
        
        inserta_en_bd.nuevaNota(nota);

    }

    //Se da de alta cliente en la BD
    static void altaCliente(String nombre, String apellido, String RFC) {
        ClienteVO cliente = new ClienteVO();
        ClienteDAO cliente_insertar;
        int llave;

            cliente.setNombre_cliente(nombre);
            cliente.setApellidos(apellido);
            cliente.setRFC(RFC);

        cliente_insertar = new ClienteDAO(con.conectarMySQL());

        llave = cliente_insertar.altaCliente(cliente);

        System.out.println("Se dió de alta cliente con ID ---> " + llave);
        con.cerrar();
    }

    //Se consultan clientes de BD por id
    static void consulta(int idCliente) {
        ClienteDAO modelo = new ClienteDAO(con.conectarMySQL());

        ClienteVO cliente= modelo.getClienteById(idCliente);

        System.out.println(cliente);

        con.cerrar();
    }

    //Se hace consulta masiva
    static void consultaMasiva(){
        ClienteDAO modelo = new ClienteDAO(con.conectarMySQL());
        ArrayList<ClienteVO> listado = modelo.consultaMasiva();
        int cont = 20;

        System.out.println("id | nombre_cliente | apellidos \t| RFC \t\t| compras");
        for (ClienteVO cliente: listado){
            System.out.print(cliente.getId_cliente()+ " \t\t");
            System.out.print(cliente.getNombre_cliente() + "\t\t");
            System.out.print(cliente.getApellidos() + "\t\t");
            System.out.print(cliente.getRFC() + "      ");
            System.out.print(cliente.getCompras() + " \n");

            if (cliente.getId_cliente() > cont) {
                Scanner leer = new Scanner(System.in);
                System.out.print("Presione C para continuar --> ");


                if (!leer.next().equalsIgnoreCase("C")){
                    break;
                }else {cont += 20;}
            }
        }

    }
}
