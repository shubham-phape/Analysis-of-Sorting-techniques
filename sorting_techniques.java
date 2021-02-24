import java.lang.*;
import java.util.*;


public class phape_project {
    public static void main(String[] args) {
        int n;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of elements");
        n = s.nextInt();
        int[] array = new int[n];
        int[] backup_array = new int[n];
        System.out.println("Enter all the elements");
        for (int i = 0; i < n; i++) {
            array[i] = s.nextInt();
            backup_array[i] = array[i];
        }
		//creating variable to exit loop
        Boolean exit=false;
        int choice;
		// to save the system time 
        long initial, last;
		
        do {
            System.out.println("Enter your choice for sorting\n 1. Bubble Sort\n 2. Insertion Sort\n 3. Quick Sort\n 4. MergeSort \n 5. Heap Sort \n 6. EXIT");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    array = backup_array.clone();
                    initial = System.nanoTime();
                    bubble_sort(array, array.length);
                    last = System.nanoTime();
                    System.out.println("Execution Time is: " + (last - initial) + " ns");
                    System.out.println("The Sorted array is");
                    print_array(array, array.length);

                    break;
                case 2:
                    array = backup_array.clone();
                    initial = System.nanoTime();
                    insertion_sort(array, array.length);
                    last = System.nanoTime();
                    System.out.println("Execution Time is: " + (last - initial) + " ns");
                    System.out.println("The Sorted array is");
                    print_array(array, array.length);
                    break;
                case 3:
                    array = backup_array.clone();
                    initial = System.nanoTime();
                    quick_sort(array, 0, array.length - 1);
                    last = System.nanoTime();
                    System.out.println("Execution Time is: " + (last - initial) + " ns");
                    System.out.println("The Sorted array is");
                    print_array(array, array.length);
                    break;
                case 4:

                    array = backup_array.clone();
                    initial = System.nanoTime();
                    merge_sort(array, 0, array.length - 1);
                    last = System.nanoTime();

                    System.out.println("Execution Time is: " + (last - initial) + " ns");
                    System.out.println("The Sorted array is");
                    print_array(array, array.length);
                    break;
                case 5:
                    array = backup_array.clone();
                    initial = System.nanoTime();
                    heap_sort(array);
                    last = System.nanoTime();
                    System.out.println("Execution Time is: " + (last - initial) + " ns");
                    System.out.println("The Sorted array is");
                    print_array(array, array.length);
                    break;
                case 6:
                        exit=true;
                        break;
                default:
                    System.out.println("Invalid Input.");
            }
        }while (!exit);
    }

    //Bubble sort function..
    public static void bubble_sort(int[] a, int n) {
        int f = 1;
        while (f == 1) {
            f = 0;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    f = 1;
                }
            }
        }

    }

    //function to sort elements by insertion sort
    public static void insertion_sort(int[] a, int n) {
        int key, j;
        for (int i = 1; i < n; i++) {
            key = a[i];
            j = i - 1;
			//for all elements to the left of the key it compares
            while (j >= 0 && key < a[j]) {
				
                a[j + 1] = a[j];
                j--;
            }
			//substituting key to its right position
            a[j + 1] = key;
        }
    }

    //function to sort elements by quicksort  using three median

    public static void quick_sort(int[] a, int lo, int hi) {
        if (lo < hi) {
			//calling function median_partition to get the partition condition 			
            int p = median_partition(a, lo, hi);
			//recursive calls to itself with different start and end indexes
            quick_sort(a, lo, p - 1);
            quick_sort(a, p + 1, hi);
        }
    }

    public static int median_partition(int[] a, int lo, int hi) {
        int pivot;
		//calculating the median index 
        int p = (lo + hi) / 2;
		// applying if conditions to find the median and put tha smallest element in the pivot, median at a[p] and hightest at a[p]
        if (a[p] < a[lo]) {
            swap(a, lo, p);
        }

        if (a[hi] < a[lo]) {

            swap(a, lo, hi);
        }

        if (a[p] > a[hi]) {

            swap(a, p, hi);
        }

        swap(a, p, hi);
//getting the median as pivot element
        pivot = a[hi];

        int i = lo - 1;

        for (int j = lo; j <= hi; j++)

            if (a[j] <= pivot) {

                i++;
                swap(a, i, j);
            }
//returning the partition index to quicksort
        return i;

    }

    //function to sort elements by merge sort;
    public static void merge_sort(int[] a, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            merge_sort(a, start, middle);
            merge_sort(a, middle + 1, end);
            merge_arrays(a, start, middle, end);
        }

    }

    public static void merge_arrays(int[] a, int lo, int m, int hi) {
        //no of elements in first part
        int n1 = m - lo;
        //no of elements in second part
        int n2 = hi - m;
        int max = Integer.MAX_VALUE;

        int[] first_array = new int[n1 + 2];
        int[] second_array = new int[n2 + 1];
        int i = 0, j = lo;
        //filling first part of array from lo to m
        while (j <= m) {
            first_array[i] = a[j];
            j++;
            i++;
        }
        // setting delimiter to mark end of array
        first_array[i] = max;
        i = 0;
        j = m + 1;
        //filling second part of array
        while (j <= hi) {
            second_array[i] = a[j];
            j++;
            i++;
        }
        //setting delimiter to mark end of array
        second_array[i] = max;
        i = 0;
        j = 0;
        int k = lo;
        //comparing both parts and substituting the smaller among them in our result array a
        while (first_array[i] != max && second_array[j] != max) {
            if (first_array[i] < second_array[j]) {
                a[k] = first_array[i];
                i++;
            } else {
                a[k] = second_array[j];
                j++;
            }
            k++;
        }
        //filling remaining elements of first array part if there are any in result array a
        while (first_array[i] != max) {
            a[k] = first_array[i];
            k++;
            i++;
        }

        //filling remaining elements of second array part if there are any in result array a
        while (second_array[j] != max) {
            a[k] = second_array[j];
            k++;
            j++;
        }

    }

    //function to sort elements by heap_sort
    public static void heap_sort(int[] a) {

        int a_len = a.length - 1;
		// building the heap 
        build_heap(a);
		//for all elements swapping with rootnode and the balancing the heap
        for (int i = a_len; i >= 0; i--) {
            swap(a, 0, i);
            heapify(a, 0, i);
        }
    }

    public static void heapify(int[] a, int i, int n) {
        int l, r;
        l = 2 * i + 1;
        r = 2 * i + 2;
		//finding the max
        int max = i;
        if (l < n && a[l] > a[i])
            max = l;
        if (r < n && (a[r] > a[max]))
            max = r;
        if (max != i) {
            swap(a, i, max);
            heapify(a, max, n);
        }
    }

    public static void build_heap(int[] a) {
        int n = a.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(a, i, n);
        }
    }

    public static void swap(int[] a, int z, int y) {
        int temp = a[z];
        a[z] = a[y];
        a[y] = temp;

    }

    //function to print any array
    public static void print_array(int[] a, int n) {
        String s;
        s = Arrays.toString(a);
        System.out.println(s);

    }
}
