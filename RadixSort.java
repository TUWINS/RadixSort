import java.util.Arrays;

public class RadixSort {
     /*  Método principal que ordena el arreglo por dígitos y retorna el total de buckets únicos usados*/
    public static int radixSort(int[] arr) {
        int totalBuckets = 0;  /*  Contador único del total de buckets usados en todos los dígitos*/
        int max = Arrays.stream(arr).max().orElse(0);  /*  Encuentra el número máximo para determinar el número de dígitos*/
        for (int exp = 1; max / exp > 0; exp *= 10) {  /*  Bucle para cada posición de dígito (unidades, decenas, etc.)*/
            totalBuckets += countSort(arr, exp);  /*  Ordena por el dígito actual y suma buckets usados*/
        }
        return totalBuckets;  /*  Retorna el total de buckets únicos para estadísticas*/
    }
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