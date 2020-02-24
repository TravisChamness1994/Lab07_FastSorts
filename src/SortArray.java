import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class for sorting an array of Comparable objects from smallest to
 * largest.
 * <p/>
 * This code is based on code from Chapter 8 and 9 of
 * Data Structures and Abstractions with Java 4/e by Carrano
 *
 * @author Travis Chamness
 * @version 6/26/2019
 */


public class SortArray
{

    /**************************************************************
     * ITERATIVE SELECTION SORT
     **************************************************************/


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void selectionSort(T[] a, int n)
    {
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
            swap(a, index, indexOfNextSmallest);
            // Assertion: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
        }
    } // end selectionSort


    /**
     * Task: Finds the index of the smallest value in an array.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     * @return the index of the smallest value among
     * a[first], a[first+1], . . . , a[last]
     */
    private static <T extends Comparable<? super T>>
    int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
                // Assertion: min is the smallest of a[first] through a[index].
            }
        }
        return indexOfMin;
    } // end getIndexOfSmallest


    /**
     * Task: Swaps the array elements a[i] and a[j].
     *
     * @param a an array of  objects
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     */
    private static <T>
    void swap(T[] a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap


    /**************************************************************
     * ITERATIVE INSERTION SORT
     **************************************************************/


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int n)
    {
        insertionSort(a, 0, n - 1);
    } // end insertionSort


    /**
     * Task: Iterative insertion sort of the  objects in a range of locations in an array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0
     * @param last  an integer > first and < a.length
     */

    private static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int first, int last)
    {
        T temp;
        boolean foundLocation;
        int loc;

        for (int i = first + 1; i <= last; i++)
        {
            temp = a[i];

            // move values over to make room
            loc = i - 1;  // start with value to the left
            foundLocation = false;
            while (loc >= first && !foundLocation)
            {
                if (a[loc].compareTo(temp) > 0)
                {
                    a[loc + 1] = a[loc];
                    loc--;
                }
                else
                    foundLocation = true;  // found the spot
            }

            // put the value in the right place  
            a[loc + 1] = temp;
        }
    } // end insertionSort


    /**************************************************************
     * ITERATIVE SHELL SORT
     **************************************************************/

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void shellSort(T[] a, int n)
    {
        shellSort(a, 0, n - 1);
    } // end shellSort


    /**
     * Task: Use incremental insertion sort with different increments to
     * sort a range of values in the array
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0
     * @param last  an integer > first and < a.length
     */
    private static <T extends Comparable<? super T>>
    void shellSort(T[] a, int first, int last)
    {
        int n = last - first + 1; // number of array elements
        for (int space = n / 2; space > 0; space = space / 2)
        {
            for (int begin = first; begin < first + space; begin++)
                incrementalInsertionSort(a, begin, last, space);
        }
    } // end shellSort


    /**
     * Task: Sorts equally spaced elements of an array into
     * ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= first and < a.length that is the
     *              index of the last array element to consider
     * @param space the difference between the indices of the
     *              elements to sort
     */
    private static <T extends Comparable<? super T>>
    void incrementalInsertionSort(T[] a, int first, int last, int space)
    {
        int unsorted, index;
        for (unsorted = first + space; unsorted <= last;
             unsorted = unsorted + space)
        {
            T firstUnsorted = a[unsorted];
            for (index = unsorted - space; (index >= first) &&
                    (firstUnsorted.compareTo(a[index]) < 0);
                 index = index - space)
            {
                a[index + space] = a[index];
            }
            a[index + space] = firstUnsorted;
        }
    } // end incrementalInsertionSort


    /**************************************************************
     * MERGE SORT
     **************************************************************/


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void mergeSort(T[] a, int n)
    {
        mergeSort(a, 0, n - 1);
    } // end mergeSort


    /**
     * Task: Merge sort on a portion of an array.  Creates a temporary array
     * then calls the recursive function
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     */
    private static <T extends Comparable<? super T>>
    void mergeSort(T[] a, int first, int last)
    {
        T tempArray[] = (T[]) new Comparable<?>[a.length];
        mergeSort(a, tempArray, first, last);
    } // end mergeSort


    /**
     * Task: recursively merge sort a portion of an array.
     *
     * @param a         an array of Comparable objects
     * @param tempArray an array used by the merge step
     * @param first     an integer >= 0 that is the index of the first
     *                  array element to consider
     * @param last      an integer >= 0 that is the index of the last
     *                  array element to consider
     */
    private static <T extends Comparable<? super T>>
    void mergeSort(T[] a, T[] tempArray, int first, int last)
    {
        if (last > first)        // we have some work to do
        {
            int mid = (last + first) / 2;
            mergeSort(a, tempArray, first, mid);
            mergeSort(a, tempArray, mid + 1, last);
            merge(a, tempArray, first, mid + 1, last);
        }

    } // end mergeSort

    /**
     * Task: Merge the elements in two contiguous sorted sublists
     *
     * @param a         an array of Comparable objects
     * @param tempArray a temporay array used in the merge
     * @param first     an integer >= 0 and < mid
     * @param mid       an integer  <= last
     * @param last      an integer  < a.length
     */
    private static <T extends Comparable<? super T>>
    void merge(T[] a, T[] tempArray, int first, int mid, int last)
    {
        int beginHalf1 = first;
        int endHalf1 = mid - 1;
        int beginHalf2 = mid;
        int endHalf2 = last;
        // While both subarrays are not empty, compare an element in one subarray with
        //an element in the other; then copy the smaller item into the temporary array

        int index = first; //next available location in tempArray
        while ((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2))
        {
            if (a[beginHalf1].compareTo(a[beginHalf2]) < 0)

            {
                tempArray[index] = a[beginHalf1];
                beginHalf1++;
            }
            else
            {
                tempArray[index] = a[beginHalf2];
                beginHalf2++;
            }
            index++;
        }

        //Assertion: One subarray has been completely copied to tempArray.


        // copy any remaining values from the first subarray over to temp
        while (beginHalf1 <= endHalf1)
            tempArray[index++] = a[beginHalf1++];

        // copy any remaining values from the second subarray over to temp
        while (beginHalf2 <= endHalf2)
            tempArray[index++] = a[beginHalf2++];

        // copy values back
        for (int i = first; i <= last; i++)
        {
            a[i] = tempArray[i];
        }
    } // end merge


    /**
     * ***********************************************************
     * QUICK SORT -
     * RECURSIVE
     * MEDIAN OF THREE
     * DOES INSERTION SORT ON SMALL ARRAYS
     * ************************************************************
     */

    private static int MIN_SIZE = 7;
    private static int QUARENTA = 40;

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a, int n)
    {
        quickSort(a, 0, n - 1);
    }


    /**
     * Task: Recursively sorts an array into ascending order. Uses quick sort with
     * median-of-three pivot selection for arrays of at least
     * MIN_SIZE elements, and uses insertion sort for other arrays.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     */
    private static <T extends Comparable<? super T>>
    void quickSort(T[] a, int first, int last)
    {
        if (last - first + 1 < MIN_SIZE)
        {
            insertionSort(a, first, last);
        }
        else
        {
            // Create the partition: Smaller | Pivot | Larger
            int pivotIndex = partition(a, first, last);

            // Sort subarrays Smaller and Larger
            quickSort(a, first, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, last);
        }
    }


    /**
     * Task: Sorts the first, middle, and last elements of an
     * array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element; first >= 0
     * @param mid   the integer index of the middle array element
     * @param last  the integer index of the last array element;
     *              last - first >= 2, last < a.length
     */
    private static <T extends Comparable<? super T>>
    void sortFirstMiddleLast(T[] a, int first, int mid, int last)
    {
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]
        orderTwoItems(a, mid, last); // make a[mid] <= a[last]
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]
    } // end sortFirstMiddleLast


    /**
     * Task: Orders two given array elements into ascending order
     * so that a[i] <= a[j].
     *
     * @param a an array of Comparable objects
     * @param i an integer >= 0 and < array.length
     * @param j an integer >= 0 and < array.length
     */
    private static <T extends Comparable<? super T>>
    void orderTwoItems(T[] a, int i, int j)
    {
        if (a[i].compareTo(a[j]) > 0)
            swap(a, i, j);
    } // end orderTwoItems


    /**
     * Finds recursively indexes of nine pivot candidates
     *
     * @param indexes  indexes of pivot candidates
     * @param a     an array of Comparable objects
     * @param first the integer index  of the first sub-array element
     * @param mid   the integer index of the middle sub-array element
     * @param last  the integer index of the last sub-array element
     * @param size  number of elements in the sub-array
     */
    private static <T extends Comparable<? super T>>
    void findNineCandidatePivotIndexes(ArrayList<Integer> indexes, T[] a, int first, int mid, int last, int size)
    {
        //TODO Project 4 - Done
        // IMPLEMENT findNineCandidatePivotIndexes method RECURSIVELY
        final int NUMBER_OF_SUB_ARRAYS = 8;
        int step = a.length/NUMBER_OF_SUB_ARRAYS;

        if(last - first < step){
            Collections.sort(indexes);
            return;
        }
        if(!indexes.contains(first))
        {
            indexes.add(first);
        }
        if(!indexes.contains(last))
        {
            indexes.add(last);
        }
        findNineCandidatePivotIndexes(indexes, a, first, first+(last-first)/2, mid, mid-first+1);
        findNineCandidatePivotIndexes(indexes, a, mid, mid + (last-mid)/2, last,last-mid+1);


    }
    /**
     * Performs insertion sort on nine pivot candidates
     * @param indexes indexes of pivot candidates
     * @param a       an array of Comparable objects
     */
    private static <T extends Comparable<? super T>>
    void insertionSortNinePivotCandidates(ArrayList<Integer> indexes, T[] a)
    {
        //TODO Project 4 - Insert9PivCand - Done

        // follow insertion sort algorithm
        T temp;
        boolean foundLocation;
        int loc;
        final int FIRST_INDEX_OF_INDEX = 0;
        int first = FIRST_INDEX_OF_INDEX;
        int last = indexes.size() - 1;

        for(int i = first+1; i < last; i++)
        {
            temp = a[indexes.get(i)];
            loc = i - 1;
            foundLocation = false;

            while(loc >= first && !foundLocation)
            {
                if(a[indexes.get(loc)].compareTo(temp) > 0)
                {
                    a[indexes.get(loc+1)] = a[indexes.get(loc)];
                    loc--;
                }
                else foundLocation = true;
            }
            a[indexes.get(loc+1)] = temp;
        }
    }

    /**
     * Task: Partitions an array as part of quick sort into two subarrays
     * called Smaller and Larger that are separated by a single
     * element called the pivot.
     * Elements in Smaller are left of the pivot and <= pivot.
     * Elements in Larger are right of the pivot and >= pivot.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element;
     *              first >= 0
     * @param last  the integer index of the last array element;
     *              last >= first; last < a.length
     * @return the index of the pivot
     */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int first, int last)
    {

        //TODO Project 4
        // modify this code per lab description

        int mid = first + (last - first) / 2;
        sortFirstMiddleLast(a, first, mid, last);

        int pivotIndex = mid;


        if (last - first + 1 > 3)
        {
            // Move pivot to next-to-last position in array
            if(last - first + 1 == MIN_SIZE)
            {
                swap(a, mid, last - 1);
                pivotIndex = last - 1;
            }
            else if(MIN_SIZE < last - first + 1 && last - first + 1 <= QUARENTA)
            {
                sortFirstMiddleLast(a, first, mid, last);
                swap(a, mid, last - 1);
                pivotIndex = last - 1;
            }
            else
                {
                    ArrayList<Integer> Indexes = new ArrayList<>(9);
                    findNineCandidatePivotIndexes(Indexes, a, first, mid, last, last-first+1);
                    insertionSortNinePivotCandidates(Indexes, a);
                    swap(a, mid, last - 1);
                    pivotIndex = last - 1;

                }

        }

        // done choosing pivot
        // at this point pivot is moved to the appropriate spot in the array

        //Sort using chosen pivot
        if (last - first + 1 > 3)
        {
            T pivotValue = a[pivotIndex];

            // Determine subarrays Smaller = a[first..endSmaller]
            // and                 Larger  = a[endSmaller+1..last-1]
            // such that entries in Smaller are <= pivotValue and
            // entries in Larger are >= pivotValue; initially, these subarrays are empty

            int indexFromLeft = first + 1;
            int indexFromRight = last - 2;

            boolean done = false;
            while (!done)
            {
                // Starting at beginning of array, leave entries that are < pivotValue;
                // locate first entry that is >= pivotValue; you will find one,
                // since last entry is >= pivot
                while (a[indexFromLeft].compareTo(pivotValue) < 0)
                    indexFromLeft++;

                // Starting at end of array, leave entries that are > pivot;
                // locate first entry that is <= pivot; you will find one,
                // since first entry is <= pivot

                while (a[indexFromRight].compareTo(pivotValue) > 0)
                    indexFromRight--;

                assert a[indexFromLeft].compareTo(pivotValue) >= 0 &&
                        a[indexFromRight].compareTo(pivotValue) <= 0;

                if (indexFromLeft < indexFromRight)
                {
                    swap(a, indexFromLeft, indexFromRight);
                    indexFromLeft++;
                    indexFromRight--;
                }
                else
                    done = true;
            }

            // Place pivotValue between the subarrays Smaller and Larger
            swap(a, pivotIndex, indexFromLeft);
            pivotIndex = indexFromLeft;

            // Assertion:
            //   Smaller = a[first..pivotIndex-1]
            //   Pivot = a[pivotIndex]
            //   Larger = a[pivotIndex+1..last]
        }
        return pivotIndex;
    } // end partition


    /**************************************************************
     *
     * COUNTING SORT
     *
     **************************************************************/
    /**
     * Making only one pass through the array, counts the number of times
     * each integer occurs in the array. These counts are then used
     * to sort the array
     *
     * @param a        array to be sorted
     * @param maxValue the maximum possible value in the array
     */
    public static void countingSort(int[] a, int maxValue)
    {
        //TODO Project 2 - Counting Sort - Done
        int[] counterArray = new int[maxValue+1]; //counter is blank array with slots 0 - m

        for(int i = 0; i < a.length; i++)
        {
            counterArray[a[i]] = counterArray[a[i]] + 1;
            System.out.println("Val: " + counterArray[a[i]] + " Index: " + a[i]);
        }

        int index = 0;
        for(int i = 0; i < counterArray.length; i++)
        {
            while(counterArray[i]>0)
            {
                a[index] = i;
                index++;
                counterArray[i] = counterArray[i] - 1;
            }
        }




    } // end countingSort


    /**************************************************************
     *
     * BINARY RADIX SORT
     *
     **************************************************************/


    /**
     * Utilizing radix sort algorithm sorts the values based on their internal binary
     * representation
     *
     * @param a         array containing integers to be sorted
     * @param maxValue the largest possible value if the array
     */
    public static void binaryRadixSort(int[] a, int maxValue)
    {
        //TODO Project 3 - BinaryRadixSort - Final transfer of array not successful.

        final int NUMBER_OF_BUCKETS = 2;
        final int ZERO_BUCKET = 0;
        final int ONE_BUCKET = 1;

        int[][] buckets = new int[NUMBER_OF_BUCKETS][a.length];
        int[] pointerArray = a;

        int largestBits = Integer.SIZE - Integer.numberOfLeadingZeros(maxValue);
        int mask = 1;
        int counterBucketOne = 0;
        int counterBucketZero = 0;

        for(int i = 0; i < largestBits; i++)
        {

            for(int j = 0; j < a.length; j++)
            {
                if ((a[j] & mask) == 0)
                {
                    buckets[ZERO_BUCKET][counterBucketZero] = a[j];
                    counterBucketZero++;
                }
                else
                    {
                    buckets[ONE_BUCKET][counterBucketOne] = a[j];
                    counterBucketOne++;
                }
            }


            //Discussed adopted with Arthur Hui. Used multiple methods to make a equal the result array at the end.
            //Pointer of array a was not successfully transfering in methods utilizing
            // a = Arrays.copyOf(buckets[ZERO_BUCKET], buckets[ZERO_BUCKET].length which tracked and worked well
            // Why not transfer over?

            if(maxValue % 2 == 1){
                a = pointerArray;
            }

            for(int l = 0; l < a.length; l++){
                pointerArray[l] = buckets[ZERO_BUCKET][l];
            }
            System.out.println(Arrays.toString(a));
            counterBucketOne = 0;
            counterBucketZero = 0;

            mask = mask << 1;
        }

        //
        // need to utilize bit related constants and methods from Integer class
        //


    }

}// end SortArray
