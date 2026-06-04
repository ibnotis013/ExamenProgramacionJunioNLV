import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    static double salarioLimite = 0;
    final static int PRESUPUESTO_MAXIMO = 10000;
    static ArrayList<Profesional> profesionales = new ArrayList<>();

    static void main() {


        while (true) {

            System.out.println("elije una opcion:" +
                    "\n 1. Contratar Jugador" +
                    "\n 2. Contratar Cuerpo tecnico" +
                    "\n 3. Despedir Profesional" +
                    "\n 4. Ver nominas Totales" +
                    "\n 5. Salir");


            int opcion = sc.nextInt();
            switch (opcion) {

                case 1:
                    try {
                        contratarJugador();
                    } catch (PresupuestoExcedidoException e) {
                        System.err.println("Presupuesto Excedido, el fichaje se cancelará");
                    }
                    break;

            }


        }
    }


    public static void contratarJugador() throws PresupuestoExcedidoException {
        sc.nextLine();
        System.out.println("ingresa el nombre del jugador");
        String nombre = sc.nextLine();
        System.out.println("ingresa el salario del jugador");
        double salario = sc.nextDouble();
        System.out.println("ingresa la posicion del jugador");
        sc.nextLine();
        String posicion = sc.nextLine();
        salarioLimite += salario;
        if (PRESUPUESTO_MAXIMO < salarioLimite) {
            salarioLimite = salarioLimite - salario;
            throw new PresupuestoExcedidoException();
        } else {
            profesionales.add(new Jugador(nombre, salario, Posicion.valueOf(posicion)));
        }
    }

    public static void contratarTecnico() throws PresupuestoExcedidoException {
        sc.nextLine();
        System.out.println("ingresa el nombre del jugador");
        String nombre = sc.nextLine();
        System.out.println("ingresa el salario del jugador");
        double salario = sc.nextDouble();
        System.out.println("ingresa la posicion del jugador");
        sc.nextLine();
        String posicion = sc.nextLine();
        salarioLimite += salario;
        if (PRESUPUESTO_MAXIMO < salarioLimite) {
            salarioLimite = salarioLimite - salario;
            throw new PresupuestoExcedidoException();
        } else {
            profesionales.add(new Jugador(nombre, salario, Posicion.valueOf(posicion)));
        }
    }


}
