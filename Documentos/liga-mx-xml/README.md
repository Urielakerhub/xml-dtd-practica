# Práctica guiada: Diseño y validación de resultados de Liga MX con XML y DTD
Cesar Uriel Aguiar Altamirano 
Expediente: a225204109

## 3. Actividad 1 - Analizar la información

### Preguntas de análisis

1. **¿Cuál debería ser el elemento raíz?**  
   `liga`

2. **¿Una jornada puede contener varios partidos?**  
   Sí, una jornada puede contener uno o más partidos.

3. **¿Cada partido debe contener exactamente dos equipos?**  
   Sí, un equipo local y un equipo visitante.

4. **¿Cómo distinguirían al equipo local del visitante?**  
   Mediante los elementos `equipoLocal` y `equipoVisitante`.

5. **¿El marcador debe representarse como un solo dato o separar los goles?**  
   Es mejor separar los goles de cada equipo.

6. **¿Las estadísticas pertenecen al partido o a cada equipo?**  
   Las estadísticas pertenecen a cada equipo dentro del partido.

7. **¿Qué datos son obligatorios?**  
   Jornada, partido, equipos, marcador y fecha.

8. **¿Cuáles podrían ser opcionales?**  
   Estadio, estado y estadísticas específicas.

---

## 4. Actividad 2 - Diseñar el modelo conceptual

| Información | Elemento/Atributo | Justificación |
|---|---|---|
| Jornada | Elemento | Puede contener varios partidos. |
| Fecha | Atributo | Identifica la fecha de la jornada o partido. |
| ID del partido | Atributo | Identifica de forma única cada partido. |
| Equipo local | Elemento | Contiene la información del equipo local. |
| Equipo visitante | Elemento | Contiene la información del equipo visitante. |
| Goles | Elemento | Representa los goles de cada equipo. |
| Estadio | Atributo | Describe el lugar donde se juega el partido. |
| Estado del partido | Atributo | Indica el estado del partido. |
| Posesión | Elemento | Representa una estadística del equipo. |
| Tarjetas | Elemento | Permite representar las tarjetas de los equipos. |

---

## 7. Actividad 5 - Diseñar el DTD

| Regla | Expresión DTD |
|---|---|
| Una liga contiene una o más jornadas | `jornada+` |
| Una jornada contiene uno o más partidos | `partido+` |
| Un partido tiene exactamente un local | `equipoLocal` |
| Un partido tiene exactamente un visitante | `equipoVisitante` |
| Una estadística opcional | `estadisticas?` |
| Puede haber cero o más tarjetas | `tarjeta*` |

---

## 8. Actividad 6 - Definir atributos

### Pregunta

**Si cada partido tiene un identificador `P001`, `P002`, etc., ¿qué ventaja tendría declararlo como `ID` en lugar de `CDATA`?**

`ID` garantiza que el identificador sea único dentro del documento XML y permite identificar cada partido de forma individual.

---

## 7. Actividad 7 - Asociar XML y DTD

| Dato | Respuesta |
|---|---|
| Archivo XML | `xml/resultados.xml` |
| Archivo DTD | `dtd/resultados.dtd` |
| Ruta relativa desde XML al DTD | `../dtd/resultados.dtd` |

---

## 10. Actividad 8 - Pruebas negativas

| Prueba | ¿Bien formado? | ¿Válido? | Error detectado |
|---|---|---|---|
| Falta visitante | Sí | No | Falta el elemento `equipoVisitante`. |
| Dos locales | Sí | No | Se repite un elemento que debe aparecer una sola vez. |
| Orden incorrecto | Sí | No | Los elementos no siguen el orden establecido por el DTD. |
| Falta atributo obligatorio | Sí | No | Falta un atributo declarado como `#REQUIRED`. |
| ID duplicado | Sí | No | El valor de `ID` debe ser único. |
| Elemento desconocido | Sí | No | El elemento no está declarado en el DTD. |

> **XML bien formado ≠ XML válido**

---

## 9. Actividad 9 - Jornada solicitada

| Fecha | Partido | Marcador |
|---|---|---|
| 27 de septiembre de 2026 | Pumas vs Atlético San Luis | 2 - 3 |
| 27 de septiembre de 2026 | Tigres vs Puebla | 1 - 0 |
| 27 de septiembre de 2026 | Santos Laguna vs Pachuca | 1 - 0 |
