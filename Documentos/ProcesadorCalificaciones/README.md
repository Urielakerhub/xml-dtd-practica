1¿Qué diferencia existe entre lanzar y capturar una excepción?

Lanzar (throw) es el acto de reportar que ocurrió un error creando un objeto de tipo excepción y deteniendo el flujo normal. Capturar (catch) es interceptar ese objeto para manejar el error de forma controlada sin que el programa colapse.

2¿Qué función tiene try?

Delimitar un bloque de código susceptible de generar errores o excepciones durante su ejecución.

3¿Qué función tiene catch?

Atrapar una excepción específica generada en el bloque try para ejecutar un código alternativo o de recuperación.

4¿Cuándo resulta útil finally?

Para asegurar que tareas críticas de limpieza (como cerrar archivos, conexiones de red o bases de datos) se ejecuten sin importar si hubo un error o no.

5¿Qué ventaja tiene try-with-resources?

Simplifica el código al cerrar automáticamente los recursos que implementan AutoCloseable al terminar el bloque, evitando fugas de memoria o de archivos abiertos sin necesidad de un bloque finally manual.

6¿Cuál es la diferencia entre throw y throws?

throw se usa dentro de los métodos para lanzar una instancia de una excepción de manera explícita. throws se coloca en la firma de un método para advertir que este puede propagar una o más excepciones verificadas hacia quien lo invoque.

7¿Por qué conviene utilizar excepciones específicas?

Porque permiten identificar exactamente qué falló, aplicando soluciones o mensajes de error precisos en lugar de tratar todos los problemas por igual.

8¿Cuándo tiene sentido crear una excepción personalizada?

Cuando las excepciones nativas de Java no reflejan con precisión un error de negocio o de lógica específico de nuestra aplicación (como una calificación fuera de rango o falta de datos válidos).

9¿Por qué no se recomienda capturar Throwable?

Porque atrapa absolutamente todo (incluidos errores graves del sistema como OutOfMemoryError o ThreadDeath), lo cual oculta problemas críticos que el código no debería intentar manejar.

10¿Qué efecto tiene ignorar una excepción?

Dificulta drásticamente la depuración (debugging) al ocultar fallos reales, lo que puede provocar que el sistema avance con estados de datos incorrectos o corruptos.

11¿Qué información debería proporcionar un buen mensaje de excepción?

El motivo exacto del error, el contexto (por ejemplo, qué valor específico causó el problema) y una pista sobre cómo solucionarlo.

12¿En qué casos conviene propagar una excepción en lugar de capturarla inmediatamente?

Cuando el método actual no tiene la responsabilidad ni los elementos necesarios para solucionar el error, siendo mejor delegarlo a una capa superior que decida cómo informarle al usuario o al sistema.
