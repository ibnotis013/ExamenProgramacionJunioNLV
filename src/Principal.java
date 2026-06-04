import java.util.Scanner;

public class Principal {
    static void main() {


        Scanner sc = new Scanner(System.in);
        while (true) {
            int opcion = sc.nextInt();
            switch (opcion) {

                case 1:
                    sc.nextLine();
                    System.out.println("ingresa el nombre del jugador");
                    String nombre = sc.nextLine();
                    System.out.println("ingresa el salario del jugador");
                    double salario = sc.nextDouble();
                    System.out.println("ingresa la posicion del jugador");
                    String posicion = sc.nextLine();
                    Jugador jugador=new Jugador(nombre,)
            }


        }
    }


}
