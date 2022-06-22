import java.util.*;
import java.util.stream.Collectors;

public class without_dublicates {

    public static double[] task (double[] a) throws IllegalArgumentException{
        Double b[] = new Double[a.length];

        int j = 0;
        for (int i = a.length-1; i >= 0; i--){
            b[i] = a[j];
            j++;
        }

        LinkedList<Double> arr = new LinkedList<>(Arrays.asList(b));

        boolean cond = arr.stream().anyMatch(o -> o < 0);

        if (cond){
            throw new IllegalArgumentException("Отрицательное число");
        }


        Double [] arr_n = arr.stream().distinct().toArray(Double[]::new);
        double [] res = new double[arr_n.length]; // массив без дубликатов

        j = 0;
        for (int i = arr_n.length-1; i >= 0; i--){
            res[i] = arr_n[j];
            j++;
        }

        return res;
    }

    public static void main(String[] args) {
        double [] a = {1.0, 2.0, 3.0, 1.0};

        double [] b = task(a);

        for (int i = 0; i < b.length; i++){
            System.out.print(b[i] + " ");
        }
    }
}
