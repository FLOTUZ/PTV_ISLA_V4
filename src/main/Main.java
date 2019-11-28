package main;


import DAO_Y_VO.ClienteDAO;
import DAO_Y_VO.ClienteVO;
import Conector.Conector;
import DAO_Y_VO.Nota_VentaVO;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Conector con = new Conector();

    public static void main(String[] args) {

        altaNota();

    }

    private static void altaNota() {
        Nota_VentaVO nota = new Nota_VentaVO();
        Date fecha;
        Time hora;
        int mesa;
        Double total;


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
