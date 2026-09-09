import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Double> listaDatos = new ArrayList<>();
        int opcion = 0;

        System.out.println("Calculadora Desviacion Estandar");

        while (opcion != 4) {
            System.out.println("\n1. Ingrese los numeros");
            System.out.println("2. Ver vista previa de datos (HU-08)");
            System.out.println("3. Limpiar datos y reiniciar (HU-10)");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();

                if (opcion == 1) {
                    System.out.print("\nCuantos numeros vas a meter?: ");
                    int cantidad = teclado.nextInt();

                    for (int i = 0; i < cantidad; i++) {
                        System.out.print("Escribe el numero " + (i + 1) + ": ");

                        if (teclado.hasNextDouble()) {
                            double valor = teclado.nextDouble();
                            listaDatos.add(valor);
                        } else {
                            System.out.println("Eso no es un numero valido. Intenta de nuevo.");
                            teclado.next();
                            i--;
                        }
                    }
                    System.out.println("Listo. Se guardaron " + listaDatos.size() + " numeros.");

                } else if (opcion == 2) {
                    VistaPrevia.mostrar(listaDatos);

                } else if (opcion == 3) {
                    VistaPrevia.limpiar(listaDatos, teclado);

                } else if (opcion == 4) {
                    System.out.println("Saliendo del programa. Adios!");

                } else {
                    System.out.println("Opcion no valida.");
                }

            } else {
                System.out.println("Por favor escribe un numero entero.");
                teclado.next();
            }
        }
        teclado.close();
    }
}
