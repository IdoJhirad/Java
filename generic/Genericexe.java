package generic;

import java.util.Arrays;
import java.util.List;

public class Genericexe {
    public  static <T> void printArray(T[] array){
        for (T e : array){
            System.out.println(""+e);
        }
    }

}

class Test {
    public static void main(String[] args){
        Integer[] array = { 1 ,2,3,4};
        String[] arr = { "bsdjhbiud", "fufhboiuf"};
        Double[] drr ={ 151.4,325648.5,3.15};

        Genericexe.printArray(array);
        Genericexe.printArray(arr);
        Genericexe.printArray(drr);
    }
}
