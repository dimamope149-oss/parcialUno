import java.util.Scanner;
public class pacialUno {

    public static void main(String[]args){
        Scanner nb=new Scanner(System.in);
        Scanner gl=new Scanner(System.in);
        Scanner vgl=new Scanner(System.in);
        Scanner cb=new Scanner(System.in);
        System.out.print("nombre del operador: ");
        String noembre=nb.nextLine();
        System.out.print("tipo de combustible C-E-A: ");
        String combustible=cb.nextLine();
        System.out.print("cantidad de galones: ");
        Float cantidad=gl.nextFloat();
        System.out.print("presio por galon: ");
        Float presio=vgl.nextFloat();
        Float subtotal=cantidad*presio;
        Float iva = 0.19f * subtotal;
        Float total=iva*subtotal;
        Float promedio=total/cantidad;
        System.out.print(
                            "=================================\n"+
                            "            Recibo               \n"+
                            "=================================\n"+
                            "Nombre: "+ noembre +"\n"+
                            "Topo Combustible: " + combustible + "\n"+
                            "cantidad de galones: " + cantidad + "\n"+
                            "presio por galon: " + presio + "\n"+
                            "promedio por galon: " + promedio
                        );

    }
}