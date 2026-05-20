[READMI.md](https://github.com/user-attachments/files/28036904/READMI.md)
# Tarificador TaxiYopal S.A.S. — Parcial Condicionales

## Datos del estudiante

| Campo | Información |
|---|---|
| **Nombre completo** | _(escribe tu nombre completo aquí)_ |
| **Código estudiantil** | _(escribe tu código aquí)_ |
| **Programa** | Ingeniería de Sistemas |
| **Semestre** | _(escribe tu semestre aquí)_ |
| **Curso** | Algoritmos I — Uniremington Yopal |
| **Lenguaje** | Java 21 (OpenJDK 21) |

---

## Descripción

Programa que calcula automáticamente la tarifa de un viaje para la empresa **TaxiYopal S.A.S.**, aplicando cuatro reglas de negocio en orden:

1. **Regla 1** — Tarifa base por kilómetro según tipo de vehículo; si el resultado es menor a la tarifa mínima, se ajusta a ella.
2. **Regla 2** — Recargos acumulativos almacenados como decimales: nocturno (0.20), domingo/festivo (0.15), lluvia fuerte (0.10), viaje rural (0.25).
3. **Regla 3** — Un único descuento según el tipo de pasajero; si se indica adulto mayor pero la edad es menor a 60, se muestra aviso y se reasigna a ocasional.
4. **Regla 4** — Tarifa solidaria: ningún viaje urbano puede quedar por debajo de la tarifa mínima del vehículo.

Desarrollado **exclusivamente con estructuras condicionales** (`if`, `if-else`, `else if`, anidadas, `&&`, `||`, `!`). Sin ciclos, arreglos, listas ni `switch`.

---

## Instrucciones para compilar y ejecutar

**Requisito:** Java JDK 11 o superior. Verificar con:
```bash
java -version
```

**Compilar:**
```bash
cd src
javac Tarficador.java
```

**Ejecutar:**
```bash
java Tarficador
```

El programa solicita los 8 datos del viaje de forma interactiva en consola.

---

## Casos de prueba (Sección 7)

### Caso 1 — Viaje sin recargos ni descuento
**Entrada:** V=2, km=10, hora=14, D=N, L=N, R=N, P=4, edad=35

```
--- RECIBO DE VIAJE ---
Vehículo tipo 2, km recorridos: 10.0
Subtotal base: $20000
Recargo total: 0% → $0
Valor con recargos: $20000
Pasajero tipo 4, descuento: 0% → $0
TOTAL A PAGAR: $20000
```
✅ **Total esperado: $20.000**

---

### Caso 2 — Ajuste a tarifa mínima (Regla 1)
**Entrada:** V=1, km=2, hora=10, D=N, L=N, R=N, P=4, edad=30

> El cálculo da $2.400 (2 km × $1.200), pero el mínimo del Motocarro es $5.000, por lo que se ajusta automáticamente.

```
--- RECIBO DE VIAJE ---
Vehículo tipo 1, km recorridos: 2.0
Subtotal base: $5000
Se aplicó tarifa mínima en Regla 1
Recargo total: 0% → $0
Valor con recargos: $5000
Pasajero tipo 4, descuento: 0% → $0
TOTAL A PAGAR: $5000
```
✅ **Total esperado: $5.000**

---

### Caso 3 — Recargos nocturno + dominical + rural; descuento frecuente
**Entrada:** V=3, km=15, hora=23, D=S, L=N, R=S, P=1, edad=45

> Subtotal: $42.000 | Recargo 60% (+$25.200) = $67.200 | Descuento 10% (-$6.720) = $60.480

```
--- RECIBO DE VIAJE ---
Vehículo tipo 3, km recorridos: 15.0
Subtotal base: $42000
Recargo total: 60% → $25200
Valor con recargos: $67200
Pasajero tipo 1, descuento: 10% → $6720
TOTAL A PAGAR: $60480
```
✅ **Total esperado: $60.480**

---

### Caso 4 — Recargo nocturno + lluvia; descuento adulto mayor
**Entrada:** V=2, km=8, hora=4, D=N, L=S, R=N, P=3, edad=68

> Subtotal: $16.000 | Recargo 30% (+$4.800) = $20.800 | Descuento 12% (-$2.496) = $18.304

```
--- RECIBO DE VIAJE ---
Vehículo tipo 2, km recorridos: 8.0
Subtotal base: $16000
Recargo total: 30% → $4800
Valor con recargos: $20800
Pasajero tipo 3, descuento: 12% → $2496
TOTAL A PAGAR: $18304
```
✅ **Total esperado: $18.304**

---

### Caso 5 — Tipo de vehículo inválido
**Entrada:** V=4, (resto de datos cualquiera)

```
Tipo de vehículo no válido
```
✅ El programa muestra el error y termina sin realizar ningún cálculo.

---

### Caso 6 — Kilómetros inválidos
**Entrada:** V=2, km=-5, (resto de datos cualquiera)

```
Distancia inválida
```
✅ El programa muestra el error y termina sin realizar ningún cálculo.

---

## Análisis de requisitos técnicos

| # | Requisito | Cómo se cumple en el código |
|---|---|---|
| 9 | `if-else` simple | Regla 1: `if (subtotal < tarifaMinima)` ajusta el subtotal y activa `aplicoMinima` |
| 10 | Cadena `if-else if-else` ≥ 3 ramas | Selección de vehículo (V==1, V==2, else para V==3) y tipo de pasajero (P==1..4) |
| 11 | Condicional anidada | Regla 3: dentro de `else if (P == 3)` hay un `if (edad >= 60)` con su propio `else` |
| 12 | Operador `\|\|` | Hora nocturna: `hora >= 22 \|\| hora < 5`; también en validaciones S/N |
| 12 | Operador `&&` | Regla 4: `R == 'N' && totalFinal < tarifaMinima` |
| 13 | Variable booleana intermedia | `boolean aplicoMinima = false` registra si se usó la tarifa mínima para mostrarlo en el recibo |
| 14 | Sin ciclos, arreglos ni switch | Cumplido — todo el flujo usa únicamente condicionales |
| 15 | Indentado, nombres descriptivos, comentarios | Bloques comentados: `ENTRADA DE DATOS`, `VALIDACIONES OBLIGATORIAS`, `REGLA 1` a `REGLA 4`, `RECIBO FINAL` |

---

## Nota técnica — Diferencia en los recargos

Los porcentajes de recargo se almacenan como decimales directos (`0.20`, `0.15`, etc.) en lugar de enteros (`20`, `15`). Esto simplifica el cálculo: `valorRecargo = subtotal * porcentajeRecargo` sin necesidad de dividir entre 100. Al mostrarse en el recibo se multiplica por 100 con `(int)(porcentajeRecargo*100)` para que aparezca como porcentaje legible.

---

## Reflexión final

Al desarrollar este tarificador comprendí que estructurar bien las validaciones al inicio del programa es tan importante como la lógica de negocio en sí, porque un dato inválido que llega al cálculo puede producir resultados sin sentido sin generar ningún error de compilación. También aprendí que los operadores lógicos `&&` y `||` permiten expresar condiciones compuestas en una sola línea, lo que hace el código más compacto y fácil de leer, como en la condición nocturna `hora >= 22 || hora < 5` que cubre dos rangos distintos con una sola comparación. El uso de una variable booleana intermedia como `aplicoMinima` fue clave para separar la decisión de cuándo imprimir el aviso en el recibo, evitando repetir la condición en un punto distinto del código. La condicional anidada del adulto mayor mostró cómo manejar reglas que dependen de más de una variable al mismo tiempo. Finalmente, almacenar los recargos como decimales en lugar de enteros fue una decisión de diseño que simplificó las operaciones aritméticas y redujo la posibilidad de errores por divisiones.

---

## Estructura del repositorio

```
parcial-condicionales-APELLIDO/
├── README.md
├── .gitignore
└── src/
    └── Tarficador.java
```

---

## Historial de commits

```
feat: agrega estructura base del proyecto y .gitignore para Java
feat: agrega lectura de datos y validaciones obligatorias
feat: implementa Regla 1 tarifa base y Regla 2 recargos acumulativos
feat: implementa Regla 3 descuentos, Regla 4 tarifa solidaria y recibo final
```

