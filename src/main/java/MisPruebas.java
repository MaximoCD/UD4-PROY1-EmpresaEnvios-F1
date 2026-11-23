/**
 * @author Maxi
 */
public class MisPruebas {

    public static void main(String[] args){
        Envio vacio = new Envio("PX00000002R", "36202");
        System.out.println(vacio);
        System.out.println(vacio.getPaqueteMasPesado());

        Envio empate = new Envio("PX00000003R", "36202");
        Paquete a = new Paquete(10, 10, 10, 5);
        Paquete b = new Paquete(10, 10, 10, 5);
        empate.addPaquete(a);
        empate.addPaquete(b);
        System.out.println(empate.getPaqueteMasPesado() == a);

        Paquete pesado = new Paquete(10, 10, 10, 20);
        System.out.println(pesado);
    }
}