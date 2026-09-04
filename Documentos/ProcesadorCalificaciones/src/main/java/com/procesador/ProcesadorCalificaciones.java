package com.procesador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesadorCalificaciones {

    public static void main(String[] args) {
        try {
            double promedio = calcularPromedio("calificaciones.txt");
            System.out.printf("Promedio de valores validos: %.2f%n", promedio);
        } catch (IOException e) {
            System.err.println("No fue posible procesar el archivo: " + e.getMessage());
        }
    }

    public static double calcularPromedio(String archivo) throws IOException {
        double suma = 0;
        int contador = 0;

        try (
            BufferedReader lector = new BufferedReader(new FileReader(archivo))
        ) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                try {
                    int calificacion = Integer.parseInt(linea.trim());
                    validarCalificacion(calificacion);

                    System.out.println("Calificación valida: " + calificacion);
                    suma += calificacion;
                    contador++;

                } catch (NumberFormatException e) {
                    System.err.println("Dato no numerico: " + linea);
                } catch (CalificacionInvalidaException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        if (contador == 0) {
            System.out.println("No se encontraron calificaciones validas para promediar.");
            return 0;
        }

        return suma / contador;
    }

    public static void validarCalificacion(int calificacion) throws CalificacionInvalidaException {
        if (calificacion < 0 || calificacion > 100) {
            throw new CalificacionInvalidaException(
                "Calificación fuera de rango: " + calificacion
            );
        }
    }
}
