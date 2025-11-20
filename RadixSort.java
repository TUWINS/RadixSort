import java.util.Arrays;
import java.io.IOException; 
import java.time.LocalTime;
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileReader;  
import java.io.FileWriter; 

public class RadixSort {
  
    public static int radixSort(int[] arr) {
        int totalBuckets = 0;  /*  Contador único del total de buckets usados*/
        int max = Arrays.stream(arr).max().orElse(0);  /*  Encuentra el número máximo para determinar el número de dígitos*/
        for (int exp = 1; max / exp > 0; exp *= 10) {  /*  Bucle para cada posición de dígito (unidades, decenas, etc.)*/
            totalBuckets += countSort(arr, exp);  /*  Ordena por el dígito actual y suma buckets usados*/
        }
        return totalBuckets;  /*  Retorna el total de buckets únicos para estadísticas*/
    }

 /*  Método auxiliar de counting sort para un dígito específico, con contador de buckets únicos*/
    private static int countSort(int[] arr, int exp) {
        int n = arr.length;  /*  Longitud del arreglo*/
        int[] output = new int[n];  /*  Arreglo temporal para la salida ordenada*/
        int[] count = new int[10];  /*  Arreglo contador para dígitos 0-9*/
        Arrays.fill(count, 0);  /*  Inicializa el contador en 0*/
        int bucketsUsed = 0;  /*  Contador único de buckets (dígitos) que se usan al menos una vez*/
        /*  Primera pasada: cuenta ocurrencias y marca buckets usados*/
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;  /*  Obtiene el dígito en la posición exp*/
            count[digit]++;  /*  Incrementa el contador*/ 
            if (count[digit] == 1) bucketsUsed++;  /*  Si es la primera vez, marca como bucket usado (único)*/
        }
           /*  Acumula los conteos para posiciones*/
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];  /*  Suma acumulativa para posiciones estables*/
        }
        /*  Construye el arreglo de salida en orden */
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];  /*  Coloca el elemento en la posición correcta*/
            count[(arr[i] / exp) % 10]--;  /*  Decrementa el contador*/
        }
        /*  Copia el arreglo de salida al arreglo original*/
        System.arraycopy(output, 0, arr, 0, n);
        return bucketsUsed;  /*  Retorna el número de buckets usados en este dígito*/
    }
    public static void main(String[] args) {  /*  Método principal para ejecutar el programa*/
        if (LocalTime.now().getMinute() % 2 != 0) {  /*  Condición única: solo ejecuta si el minuto es par*/
            System.out.println("Modo nocturno: Espera a minuto par.");  /*  Mensaje único para condición*/
            return;  /*  Termina el programa si no cumple*/
        }
        try {
            // Lee datos desde el archivo de entrada
            BufferedReader br = new BufferedReader(new FileReader("input_radix.txt"));
            String line = br.readLine();  /*  Lee la línea con números*/
            String[] parts = line.split(" ");  /*  Divide por espacios*/
            int[] arr = new int[parts.length];  /*  Crea arreglo*/
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);  /*  Convierte a entero*/
            
            }
            br.close();  /*  Cierra el lector*/
             // Ejecuta Radix Sort y obtiene buckets usados
            int buckets = radixSort(arr);
            /* Escribe el resultado ordenado y estadísticas únicas*/
            BufferedWriter bw = new BufferedWriter(new FileWriter("output_radix.txt"));
            for (int num : arr) {
                bw.write(num + " ");  /*     Escribe números ordenados*/
            }
            bw.write("\nBuckets usados: " + buckets);  /*  Agrega estadística única*/
            bw.close();  /*  Cierra el lector*/
            System.out.println("Radix Sort único completado. Resultado en output_radix.txt");
        } catch (IOException e) {  /*  Maneja errores de archivo*/
            e.printStackTrace();  /*  Imprime traza de error*/
        }
    }
}