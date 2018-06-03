// Aaron Melhaff
// Nolan Medina
// Adrian Smith

public class BasicSort {
    public static void main(String[] args){
        int[] array = new int[10];

        for(int i = 0; i < 10; i++){
            int newVal = (int) Math.ceil(Math.random() * 20);
            array[i] = newVal;
        }

        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }

        sort(array);
        System.out.println();

        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }

    }

    public static void sort(int[] input){
        int smallest;


        for(int i = 0; i < input.length - 1; i++){
            smallest = i;
            for(int j = i; j < input.length; j++){
                if(input[smallest] > input[j]){
                    smallest = j;
                }
            }
            int temp = input[i];
            input[i] = input[smallest];
            input[smallest] = temp;
        }
    }
}
