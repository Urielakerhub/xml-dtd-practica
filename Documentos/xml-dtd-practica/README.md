# Práctica XML y DTD
Cesar Uriel Aguiar Altamirano
Expediente a225204109
## Objetivo

Diseñar documentos XML bien formados, definir DTD internos y externos, utilizar cardinalidades y restricciones de atributos, validar documentos XML y gestionar los cambios mediante Git.

---

# Ejercicio 1: Pedido

## Modelo propuesto

| Información  | Valor identificado             | Elemento XML propuesto |
| ------------ | ------------------------------ | ---------------------- |
| Destinatario | Juan Pérez                     | `<destinatario>`       |
| Artículo     | Computadora portátil           | `<articulo>`           |
| Dirección    | Av. Universidad 123, piso 2, A | `<direccion>`          |
| Fecha        | 2026-10-10                     | `<fecha_entrega>`      |

### Jerarquía propuesta

```text
pedido
├── destinatario
├── articulo
├── direccion
│   ├── calle
│   ├── numero
│   ├── piso
│   └── letra
└── fecha_entrega
```

La dirección se divide en calle, número, piso y letra para poder utilizar cada componente de manera independiente.

## Decisiones de diseño

### 1. ¿Conviene almacenar la dirección como un único texto?

No siempre. Si se necesita procesar cada parte de la dirección, es mejor separarla en diferentes elementos.

### 2. ¿Qué ventajas tendría separar sus componentes?

Permite buscar, modificar y procesar independientemente la calle, número, piso y letra.

### 3. ¿Cómo debería almacenarse una fecha para facilitar su procesamiento?

Se recomienda utilizar un formato normalizado como `AAAA-MM-DD`, por ejemplo:

```text
2026-10-10
```

### 4. ¿Qué información podría ser atributo y cuál elemento?

Los datos principales pueden representarse como elementos. Los atributos pueden utilizarse para características o clasificaciones de un elemento. Por ejemplo, `tipo` puede ser un atributo de `domicilio`.

---

# Ejercicio 2: Nota

## Análisis de la estructura

```text
nota
├── para
├── de
├── titulo
└── contenido
```

### Preguntas

### 1. ¿Cuál es el elemento raíz?

El elemento raíz es `nota`.

### 2. ¿Cuántas veces aparece `para`?

Aparece una vez en la estructura definida.

### 3. ¿El orden de los elementos es significativo?

Sí. El DTD establece el orden:

```text
para → de → titulo → contenido
```

Por lo tanto, debe respetarse para que el documento sea válido.

### 4. ¿Los elementos contienen otros elementos o solamente texto?

Los elementos `para`, `de`, `titulo` y `contenido` contienen solamente texto. Por eso se utiliza `#PCDATA`.

## DTD externo

| Elemento    | Contenido esperado | Declaración DTD                                 |
| ----------- | ------------------ | ----------------------------------------------- |
| `nota`      | elementos          | `<!ELEMENT nota (para, de, titulo, contenido)>` |
| `para`      | texto              | `<!ELEMENT para (#PCDATA)>`                     |
| `de`        | texto              | `<!ELEMENT de (#PCDATA)>`                       |
| `titulo`    | texto              | `<!ELEMENT titulo (#PCDATA)>`                   |
| `contenido` | texto              | `<!ELEMENT contenido (#PCDATA)>`                |

El archivo `nota.xml` utiliza el DTD externo mediante:

```xml
<!DOCTYPE nota SYSTEM "nota.dtd">
```

## DTD interno

En `nota-interno.xml` las declaraciones del DTD se encuentran dentro del propio documento:

```xml
<!DOCTYPE nota [
    <!ELEMENT nota (para, de, titulo, contenido)>
    <!ELEMENT para (#PCDATA)>
    <!ELEMENT de (#PCDATA)>
    <!ELEMENT titulo (#PCDATA)>
    <!ELEMENT contenido (#PCDATA)>
]>
```

## Pruebas negativas

| Modificación                               | ¿Bien formado? | ¿Válido? | ¿Por qué?                               |
| ------------------------------------------ | -------------- | -------- | --------------------------------------- |
| Sustituir `<para>` por `<destinatario>`    | Sí             | No       | El DTD exige el elemento `para`.        |
| Intercambiar el orden de `<para>` y `<de>` | Sí             | No       | El DTD exige el orden establecido.      |
| Agregar `<telefono>5551234567</telefono>`  | Sí             | No       | `telefono` no está declarado en el DTD. |

### `git diff`

El comando:

```bash
git diff
```

permite observar los cambios realizados en `nota.xml`.

### Restaurar el documento

Después de las pruebas se puede recuperar el documento válido con:

```bash
git restore ejercicio2/nota.xml
```

---

# Comparación entre DTD interno y externo

| Característica                      | DTD interno       | DTD externo             |
| ----------------------------------- | ----------------- | ----------------------- |
| Ubicación                           | Dentro del XML    | Archivo `.dtd` separado |
| Reutilizable entre XML              | No directamente   | Sí                      |
| Archivo adicional                   | No                | Sí                      |
| Conveniente para un único documento | Sí                | Puede ser innecesario   |
| Conveniente para muchos documentos  | Menos conveniente | Sí                      |

---

# Ejercicio 3: Matrícula

## Modelo

```text
matricula
├── personal
│   ├── dni
│   ├── nombre
│   ├── titulacion
│   ├── curso_academico
│   └── domicilios
│       └── domicilio+
└── pago
    └── tipo_matricula
```

## Elementos, atributos y restricciones

| Elemento o atributo | Tipo      | Característica                                   |
| ------------------- | --------- | ------------------------------------------------ |
| `matricula`         | Compuesto | Contiene `personal` y `pago`                     |
| `personal`          | Compuesto | Contiene los datos personales y domicilios       |
| `dni`               | Simple    | Contiene texto                                   |
| `nombre`            | Simple    | Contiene texto                                   |
| `titulacion`        | Simple    | Contiene texto                                   |
| `curso_academico`   | Simple    | Contiene texto                                   |
| `domicilios`        | Compuesto | Contiene uno o más domicilios                    |
| `domicilio`         | Compuesto | Contiene los datos de una dirección              |
| `calle`             | Simple    | Contiene texto                                   |
| `numero`            | Simple    | Contiene texto                                   |
| `ciudad`            | Simple    | Contiene texto                                   |
| `tipo`              | Atributo  | Obligatorio y limitado a `familiar` o `habitual` |
| `pago`              | Compuesto | Contiene `tipo_matricula`                        |
| `tipo_matricula`    | Simple    | Contiene texto                                   |

---

# Cardinalidad

El requisito establece que debe existir **al menos un domicilio**.

| Símbolo | Significado |
| ------- | ----------- |
| `?`     | Cero o uno  |
| `*`     | Cero o más  |
| `+`     | Uno o más   |

Para expresar "al menos uno" se utiliza:

```text
+
```

La declaración DTD correspondiente es:

```dtd
<!ELEMENT domicilios (domicilio+)>
```

## Prueba eliminando todos los domicilios

**¿XML bien formado?**

Sí.

**¿XML válido?**

No.

**¿Por qué?**

Porque el documento conserva una sintaxis XML correcta, pero no cumple el DTD, ya que `domicilio+` exige uno o más domicilios.

---

# Restricción del atributo `tipo`

El atributo `tipo` debe ser obligatorio y únicamente puede aceptar los valores `familiar` o `habitual`.

```dtd
<!ATTLIST domicilio tipo (familiar|habitual) #REQUIRED>
```

## Pruebas

| Caso              | Predicción | Resultado | Explicación                   |
| ----------------- | ---------- | --------- | ----------------------------- |
| `tipo="familiar"` | Válido     | Válido    | `familiar` está permitido.    |
| `tipo="habitual"` | Válido     | Válido    | `habitual` está permitido.    |
| `tipo="temporal"` | No válido  | No válido | `temporal` no está permitido. |
| Sin `tipo`        | No válido  | No válido | El atributo es obligatorio.   |

---

# DTD externo

El archivo `matricula.dtd` define:

* La estructura de `matricula`.
* Los elementos de `personal`.
* Los elementos de `pago`.
* La cardinalidad `domicilio+`.
* El atributo obligatorio `tipo`.
* Los valores permitidos `familiar` y `habitual`.

---

# DTD interno

El archivo `matricula-interno.xml` contiene las declaraciones del DTD dentro del mismo documento XML.

La estructura y las restricciones son equivalentes a las del DTD externo.

---

# Git para desarrollar una variante

Se crea una nueva rama:

```bash
git switch -c dtd-interno-matricula
```

Se agrega el archivo:

```bash
git add ejercicio3/matricula-interno.xml
```

Se realiza el commit:

```bash
git commit -m "Implementar DTD interno para matrícula"
```

Para revisar el historial:

```bash
git log --oneline --graph --all
```

Después se vuelve a la rama principal:

```bash
git switch main
```

Y se integra la rama:

```bash
git merge dtd-interno-matricula
```

Si la rama principal se llama `master`, se utiliza `master` en lugar de `main`.

---

# Publicar el repositorio

El repositorio de GitHub debe llamarse:

```text
xml-dtd-practica
```

Se puede asociar el repositorio local mediante:

```bash
git remote add origin URL_DEL_REPOSITORIO
```

Después:

```bash
git branch -M main
git push -u origin main
```

El repositorio debe mostrar los tres ejercicios y el historial de commits.

---

# Conclusiones

## 1. ¿Cuál es la diferencia entre XML bien formado y XML válido?

Un XML bien formado cumple las reglas básicas de sintaxis XML. Un XML válido, además, cumple las reglas establecidas por su DTD.

## 2. ¿Qué función cumple un DTD?

El DTD define la estructura que debe cumplir un documento XML, incluyendo elementos, orden, cardinalidad y atributos.

## 3. ¿Qué diferencia existe entre DTD interno y externo?

El DTD interno se encuentra dentro del propio documento XML. El DTD externo se encuentra en un archivo separado y puede reutilizarse en diferentes documentos.

## 4. ¿Cómo se expresa cardinalidad en DTD?

Mediante los siguientes símbolos:

* `?` → cero o uno.
* `*` → cero o más.
* `+` → uno o más.

## 5. ¿Cómo puede restringirse un atributo a determinados valores?

Utilizando una enumeración, por ejemplo:

```dtd
<!ATTLIST domicilio tipo (familiar|habitual) #REQUIRED>
```

Esto permite únicamente los valores `familiar` y `habitual` y obliga a que el atributo esté presente.

## 6. ¿Qué ventaja proporcionó Git durante las pruebas?

Git permitió guardar diferentes versiones de los archivos, registrar los cambios y recuperar versiones anteriores.

## 7. ¿Qué utilidad tuvieron `git diff` y `git restore`?

`git diff` permitió visualizar los cambios realizados en un archivo.

`git restore` permitió regresar el archivo a su versión anterior y recuperar el documento válido.

## 8. ¿Qué ventaja proporcionó una rama para desarrollar una solución alternativa?

La rama permitió desarrollar el DTD interno de matrícula de manera independiente de la rama principal. Después se pudo integrar mediante `merge`.

---

# Estructura final esperada

```text
xml-dtd-practica/
├── README.md
├── ejercicio1/
│   └── pedido.xml
├── ejercicio2/
│   ├── nota.xml
│   ├── nota.dtd
│   └── nota-interno.xml
└── ejercicio3/
    ├── matricula.xml
    ├── matricula.dtd
    └── matricula-interno.xml
```

# Entregables

1. URL del repositorio de GitHub.
2. `pedido.xml`.
3. `nota.xml`.
4. `nota.dtd`.
5. `nota-interno.xml`.
6. `matricula.xml`.
7. `matricula.dtd`.
8. `matricula-interno.xml`.
9. `README.md` con análisis, tablas, pruebas y conclusiones.

El historial de Git forma parte de la evidencia del proceso de construcción.

