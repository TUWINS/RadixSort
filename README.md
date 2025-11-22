# Radix Sort con Conteo de Buckets Únicos en Java

Este programa implementa el algoritmo **Radix Sort** en Java y añade una característica especial: **cuenta la cantidad de buckets (dígitos de 0 a 9) utilizados al menos una vez en cada iteración del ordenamiento**. Esta estadística se almacena junto con la lista ordenada en un archivo de salida.

---

##  Características Principales

- Ordena números enteros usando **Radix Sort + Counting Sort**  
- Cuenta **buckets únicos utilizados** en cada pasada del algoritmo  
- Lee datos desde un archivo: `entrada_radix.txt`  
- Guarda el resultado ordenado y estadísticas en `salida_radix.txt`  
- Solo ejecuta si el **minuto actual es par** (condición especial)

---

##  Cómo funciona el algoritmo

El **Radix Sort** ordena números enteros **analizando sus dígitos desde el menos significativo hasta el más significativo**.  
Para cada dígito, utiliza **Counting Sort**, adaptado para contar **cuántos buckets (dígitos) se usan** en esa iteración.

### Paso a Paso

 **Leer los datos**  
Se leen los números del archivo `entrada_radix.txt` y se almacenan en un arreglo.  
Ejemplo:
170 45 75 90 802 24 2 66

**Determinar el número máximo de dígitos**  
El número más grande indica cuántas pasadas realizará el algoritmo.  
Ejemplo: 802 → 3 dígitos → 3 iteraciones (unidades, decenas, centenas)

 **Iteraciones por dígitos**  
Cada pasada ordena los números según un dígito específico:

| Iteración | Dígito usado | Ejemplo (802) |
|-----------|--------------|---------------|
| 1         | Unidades     | 2             |
| 2         | Decenas      | 0             |
| 3         | Centenas     | 8             |

 **Counting Sort por dígito**  
- Cuenta cuántos números tienen cada dígito (0-9)  
- Suma los **buckets únicos usados**  
- Ordena los números según ese dígito manteniendo el orden previo (orden estable)  
- Copia el resultado al arreglo original para la siguiente iteración

 **Resultado final**  
- El arreglo queda ordenado  
- Se guarda el número total de buckets usados
- Se  muestra en  `salida_radix.txt`
---

