import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Created by: Travis Chamness
//Date: 10/10/19

public class CheckBinaryRadix {
    public static void main(String[] args) {
        int arraySize = 0;
        int maxNumber = 0;


        //Testing
        final int SEED = 101;
        int data[];
        int forTesting[];
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is the size of your array?");
        arraySize = keyboard.nextInt();

        System.out.println("What is the max number of your array?");
        maxNumber = keyboard.nextInt();

        Random generator = new Random(SEED);
        data = new int[arraySize];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = generator.nextInt(maxNumber+1);
        }
        System.out.println("The original array is: ");
        System.out.println(Arrays.toString(data));

        forTesting = Arrays.copyOf(data, data.length);
        Arrays.sort(forTesting);
        SortArray.binaryRadixSort(data, maxNumber);
        System.out.println("The original array sorted with Binary Radix Sort.");
        System.out.println(Arrays.toString(data));
        System.out.println("Expected");
        System.out.println(Arrays.toString(forTesting));
        System.out.println((Arrays.equals(data, forTesting)));



    }
}
