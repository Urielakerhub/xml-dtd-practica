import java.util.List;
import java.util.Scanner;

public class VistaPrevia {

    public static void mostrar(List<Double> datos) {
        System.out.println("\n Vista Previa de Datos ");
        if (datos.isEmpty()) {
            System.out.println("No hay datos registrados en la sesion actual.");
        } else {
            System.out.println("Cantidad de observaciones: " + datos.size());
            System.out.println("Valores ingresados: " + datos);
        }
    }

    public static void limpiar(List<Double> datos, Scanner scanner) {
        System.out.print("\nDeseas borrar los datos y reiniciar la sesion? (si/no): ");
        String respuesta = scanner.next();

        if (respuesta.equalsIgnoreCase("si")) {
            datos.clear();
            System.out.println("Sesion limpiada con exito. Puedes capturar nuevos datos.");
        } else {
            System.out.println("Se conservan los datos actuales.");
        }
    }
}
