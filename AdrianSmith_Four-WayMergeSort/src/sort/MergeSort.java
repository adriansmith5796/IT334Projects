package sort;

import java.util.Arrays;

public class MergeSort {

    /**
     * The initial call to the sorting methods
     *
     * @param array The array being merge sorted
     */
    public static void mergeSort(Comparable[] array) {
        // Nothing to sort
        if (array.length < 2) return;

        // Array size is negligible so simple sort is okay
        if (array.length < 4) {
            simpleSort(array, 0, array.length - 1);
            return;
        }

        // Temp array for merging
        Comparable[] temp = new Comparable[array.length];
        sort(array, temp, 0, array.length - 1);
    }

    /**
     * @param array
     * @param temp
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] array, Comparable[] temp, int lo, int hi) {
        int numElems = hi - lo + 1;

        if (numElems < 4) {
            simpleSort(array, lo, hi);
            return;
        }


        int[] pointers = new int[5];
        pointers[0] = lo;
        pointers[4] = hi;
        int mid1 = pointers[1] = lo + (numElems / 4);
        int mid2 = pointers[2] = lo + (numElems / 2);
        int mid3 = pointers[3] = lo + (3 * numElems / 4);

        sort(array, temp, lo, mid1);
        sort(array, temp, mid1 + 1, mid2);
        sort(array, temp, mid2 + 1, mid3);
        sort(array, temp, mid3 + 1, hi);
        merge(array, temp, pointers);
    }

    /**
     * Merges four quarters of an array
     *
     * @param array
     */
    public static void merge(Comparable[] array, Comparable[] temp, int[] pointers) {
        for(int i = 0; i < array.length; i++)
            temp[i] = array[i];

        int lo = pointers[0]; // Beginning of first sub-array
        int mid1 = pointers[1] + 1; // Beg of second sub-array
        int mid2 = pointers[2] + 1; // Beg of thrid sub-array
        int mid3 = pointers[3] + 1; // Beg of fourth sub-array
        int hi = pointers[4]; // Last index of array

        for(int i = lo; i <= hi; i++){
            if(lo > pointers[1]){ // if sub1 is done
                if(mid1 > pointers[2]){ // if sub 2 is done
                    if(mid2 > pointers[3]){ // if sub3 is done
                        if(mid3 > pointers[4]){ // if sub4 is done
                            return;
                        }else{ // sub 4 not done
                            array[i] = temp[mid3++];
                        }
                    }else if(mid3 > pointers[4]){ // if 1, 2, 4 are done
                        array[i] = temp[mid2++];
                    }else{ // sub1 and 2 are done but not 3 and 4
                        if(temp[mid2].compareTo(temp[mid3]) < 0)
                            array[i] = temp[mid2++];
                        else
                            array[i] = temp[mid3++];
                    }
                }else if(mid2 > pointers[3]){// Means sub1 and 3 is done but not sub 2
                    if(mid3 > pointers[4]) { // subs 1 3 and 4 are done but not two
                        array[i] = temp[mid1++];
                    }else{ // 2 and 4 not done
                        if(temp[mid1].compareTo(temp[mid3]) < 0)
                            array[i] = temp[mid1++];
                        else
                            array[i] = temp[mid3++];
                    }
                }else if(mid3 > pointers[4]){  // sub 1 and 4 are done
                    if(temp[mid1].compareTo(temp[mid2]) < 0){
                        array[i] = temp[mid1++];
                    }else{
                        array[i] = temp[mid2++];
                    }
                } else{ // 2 3 4 not done
                    if(temp[mid1].compareTo(temp[mid2]) < 0 && temp[mid1].compareTo(temp[mid3]) < 0)
                        array[i] = temp[mid1++];
                    else if(temp[mid2].compareTo(temp[mid3]) < 0)
                        array[i] = temp[mid2++];
                    else
                        array[i] = temp[mid3++];
                }
            }else if(mid1 > pointers[2]){ // 1 not done but 2 is
                if(mid2 > pointers[3]){ // 1 not done but 2 3 is
                    if(mid3 > pointers[4]) // 1 not done but 2 3 4 is
                        array[i] = temp[lo++];
                } else if(mid3 > pointers[4]){ // 1 3 not done but 2 4 is
                    if(temp[mid2].compareTo(temp[lo]) < 0)
                        array[i] = temp[mid2++];
                    else
                        array[i] = temp[lo++];
                }else{ // 1 3 4 not done but 2 is
                    if(temp[mid2].compareTo(temp[lo]) < 0 && temp[mid2].compareTo(temp[mid3]) < 0)
                        array[i] = temp[mid2++];
                    else if(temp[mid3].compareTo(temp[lo]) < 0)
                        array[i] = temp[mid3++];
                    else
                        array[i] = temp[lo++];
                }
            }else if(mid2 > pointers[3]){ // 1 and 2 not done but 3 is
                if(mid3 > pointers[4]){ // 1 2 not done but 3 4 is
                    if(temp[lo].compareTo(temp[mid1]) < 0 )
                        array[i] = temp[lo++];
                    else
                        array[i] = temp[mid1++];
                }else{ // 1 2 4 not done
                    if(temp[mid1].compareTo(temp[lo]) < 0 && temp[mid1].compareTo(temp[mid3]) < 0)
                        array[i] = temp[mid1++];
                    else if(temp[mid3].compareTo(temp[lo]) < 0)
                        array[i] = temp[mid3++];
                    else
                        array[i] = temp[lo++];
                }
            } else if(mid3 > pointers[4]){ // 1 2 and 3 not done but 4 is
                if(temp[mid1].compareTo(temp[lo]) < 0 && temp[mid1].compareTo(temp[mid2]) < 0)
                    array[i] = temp[mid1++];
                else if(temp[mid2].compareTo(temp[lo]) < 0)
                    array[i] = temp[mid2++];
                else    array[i] = temp[lo++];

            } else if(temp[mid1].compareTo(temp[lo]) < 0 && temp[mid1].compareTo(temp[mid2]) < 0 && temp[mid1].compareTo(temp[mid3]) < 0)
                array[i] = temp[mid1++];
            else if(temp[mid2].compareTo(temp[lo]) < 0 && temp[mid2].compareTo(temp[mid3]) < 0)
                array[i] = temp[mid2++];
            else if(temp[mid3].compareTo(temp[lo]) < 0)
                array[i] = temp[mid3++];
            else    array[i] = temp[lo++];
        }




//        for (int current = 0; current < array.length; current++) {
//            // Find the next lowest number and store the index in next
//            next = pointers[0];
//
//            for(int i = 1; i < pointers.length && next == -1; i++){
//                next = pointers[i];
//            }
//
//            for(int i = 0; i < pointers.length; i++){
//                if((pointers[i] < array.length && pointers[i] > -1) && array[next].compareTo(array[pointers[i]]) > 0)
//                    next = pointers[i];
//            }
//
//            for(int i = 0; i < pointers.length; i++){
//                if(pointers[i] == next)
//                    pointers[i]++;
//                if((i < pointers.length - 1) || pointers[i] >= array.length){
//                    pointers[i] = -1;
//                }
//            }
//
//            temp[current] = array[next];
//        }
//
//        for (int i = 0; i < array.length; i++) {
//            array[i] = temp[i];
//        }
    }

    /**
     * @param array
     */
    public static void simpleSort(Comparable[] array, int lo, int hi) {
        // Selection sort since input size is less than 4
        int lowest;
        for (int current = lo; current < hi; current++) {
            lowest = current;
            for (int j = current + 1; j < hi + 1; j++) {
                if (array[j].compareTo(array[lowest]) < 0)
                    lowest = j;
            }

            Comparable temp = array[lowest];
            array[lowest] = array[current];
            array[current] = temp;
        }
    }

}
