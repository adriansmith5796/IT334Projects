package algorithms;

import java.io.*;
import java.util.Random;

public class RuntimeAnalysis {

    public static void main(String[] args) {

        Random rand = new Random();

        // This try-catch clears the file contents before any new data is written
        try {
            FileWriter clear = new FileWriter("AdrianSmith_RuntimeAnalysis/algorithm1.txt");
            PrintWriter output = new PrintWriter(clear);
            output.close();
            clear = new FileWriter("AdrianSmith_RuntimeAnalysis/algorithm2.txt");
            output = new PrintWriter(clear);
            output.close();
            clear = new FileWriter("AdrianSmith_RuntimeAnalysis/algorithm3.txt");
            output = new PrintWriter(clear);
            output.close();
        }catch(Exception e){}

        System.out.println("STARTING 1");
        for(int i = 1; i <= 1000; i++){
            generatePermutation1(i, rand);
        }
        System.out.println("1 DONE, STARTING 2");
        for(int i = 1; i <= 1000; i++){
            generatePermutation2(i, rand);
        }
        System.out.println("2 DONE, STARTING 3");
        for(int i = 1; i <= 1000; i++){
            generatePermutation3(i, rand);
        }


    }


    public static int[] generatePermutation1(int n, Random rand) {
        int num;
        int count = 0;
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            num = rand.nextInt(n);
            int times = contains(num, array);
            if(times > 1){
                i--;
            }else{
                array[i] = num;
            }
            count += times;
        }

        try {
            File file = new File("AdrianSmith_RuntimeAnalysis/algorithm1.txt");
            FileWriter writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);
            output.println(n + ", " + count);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    /**
     * Returns the number of indexes it searched. If it returns 0, then all indexes were searched
     * and the value wasn't found.
     * @param search
     * @param array
     * @return
     */
    public static int contains(int search, int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] == search){
                return i + 1;
            }
        }

        return 0;
    }

    public static int[] generatePermutation2(int n, Random rand) {
        int num;
        int count = 0;
        int[] array = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            num = rand.nextInt(n);
            if(used[num]){
                i--;
            }else{
                array[i] = num;
                used[i] = true;
            }
            count++;
        }

        try {
            File file = new File("AdrianSmith_RuntimeAnalysis/algorithm2.txt");
            FileWriter writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);
            output.println(n + ", " + count);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    public static int[] generatePermutation3(int n, Random rand) {
        int[] array = new int[n];
        int count = 0;

        for(int i = 0; i < n; i++){
            array[i] = i + 1;
            count++;
        }

        int pos;
        int temp;
        for(int i = 0; i < n; i++){
            pos = rand.nextInt(n);
            temp = array[i];
            array[i] = array[pos];
            array[pos] = temp;
            count++;
        }

        try {
            File file = new File("AdrianSmith_RuntimeAnalysis/algorithm3.txt");

            FileWriter writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);
            output.println(n + ", " + count);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }
}
