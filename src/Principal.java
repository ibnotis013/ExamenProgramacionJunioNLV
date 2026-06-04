import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    static double salarioLimite = 0;
    final static int PRESUPUESTO_MAXIMO = 10000;
    static ArrayList<Profesional> profesionales = new ArrayList<>();

    static void main() {

        boolean bandera = true;
        while (bandera) {

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
                        System.err.println("Presupuesto Excedido, el fichaje del jugador se cancelará");
                    }
                    break;

                case 2:
                    try {
                        contratarTecnico();
                    } catch (PresupuestoExcedidoException e) {
                        System.err.println("Presupuesto Excedido, el fichaje del tecnico se cancelará");
                    }
                    break;


                case 3:
                    try {
                        eliminarProfesional(profesionales);
                    } catch (ProfesionalNoEncontradoException e) {
                        System.err.println("Error al buscar el profesioanl");
                    }
                    break;


                case 4:
                    verNominasTotales(profesionales);
                    break;
                case 5:
                    bandera = false;
                    break;
            }


        }
    }


    public static void contratarJugador() throws PresupuestoExcedidoException {
        limpiar();
        System.out.println("ingresa el nombre del jugador");
        String nombre = sc.nextLine();
        System.out.println("ingresa el salario del jugador");
        double salario = sc.nextDouble();
        System.out.println("ingresa la posicion del jugador");
        limpiar();
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
        limpiar();
        System.out.println("ingresa el nombre del tecnico");
        String nombre = sc.nextLine();
        System.out.println("ingresa el salario del tecnico");
        double salario = sc.nextDouble();
        System.out.println("ingresa el puesto de trabajo del tecnico");
        limpiar();
        String puesto = sc.nextLine();
        salarioLimite += salario;
        if (PRESUPUESTO_MAXIMO < salarioLimite) {
            salarioLimite = salarioLimite - salario;
            throw new PresupuestoExcedidoException();
        } else {
            profesionales.add(new Tecnico(nombre, salario, puesto));
        }
    }

    public static void eliminarProfesional(ArrayList<Profesional> profesionales) {
        limpiar();
        System.out.println("introduce el nombre del profesional que quieras eliminar");
        String nombre = sc.nextLine();
        for (Profesional profesional : profesionales) {
            if (profesional.getNombre().equals(nombre)) {
                profesionales.remove(profesional);
                // aqui da error, al querer eliminar un profesional, si le pasamos el nombre bien, da error por culpa
                // del bucle, ya que al eliminarlo de la posicion lo que hace es
                // mover todo una casilla hacia alante entonces explota ya que se queda buscando en la nada.
            } else {
                throw new ProfesionalNoEncontradoException("El profesional que querias eliminar, no se encuentra en la lista");
            }
        }

    }

    public static void verNominasTotales(ArrayList<Profesional> profesionales) {
        limpiar();
        double totalGastadoPorClub = 0;
        for (Profesional profesional : profesionales) {
            System.out.println("el profesional de nombre " + profesional.getNombre());
            System.out.println("con salario total de " + (profesional.getSalarioBase() + profesional.bonus(profesional.getSalarioBase())));
            totalGastadoPorClub += profesional.getSalarioBase() + profesional.bonus(profesional.getSalarioBase());
            if (profesional.getClass().equals(Jugador.class)) {
                System.out.println("con posicion " + ((Jugador) profesional).getPosicion());
            } else {
                System.out.println("con puesto de trabajo " + ((Tecnico) profesional).getPuesto());
            }
        }
        System.out.println("El club esta gastando el total " + totalGastadoPorClub+"\n" +
                " ");
    }


    public static void limpiar() {
        sc.nextLine();

    }
}
