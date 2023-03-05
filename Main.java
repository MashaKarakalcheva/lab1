import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    public static Employee sequentialSearch(Employee[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getName().equals(key)) {
                return arr[i];
            }
        }
        return null;
    }

    public static Employee binarySearch(Employee[] arr, String key) {
        int first = 0;
        int last = arr.length - 1;

        while (first <= last) {
            int mid = (first + last) / 2;

            if (arr[mid].getName().compareTo(key) < 0) {
                first = mid + 1;
            } else if (arr[mid].getName().compareTo(key) > 0) {
                last = mid - 1;
            } else {
                return arr[mid];
            }
        }
        return null;
    }

   /* public static Employee interpolationSearch(Employee[] arr, String key) {
        int left = 0;
        int right = arr.length - 1;
        int pos = 1;
        while (pos < right && pos > left && key.compareTo(arr[left].getName()) > 0 && key.compareTo(arr[right].getName()) < 0) {
            pos  = left + ((key.compareTo(arr[left].getName()) * (right - left)) / (arr[right].getName().compareTo(arr[left].getName())));
            System.out.println("Pos" + pos);
            int cmp = key.compareTo(arr[pos].getName());
            System.out.println(cmp);
            System.out.println("Right" + right);
            System.out.println("Left" + left);
            if (cmp < 0) {
                right = pos - 1;
            } else if (cmp > 0) {
                left = pos + 1;
            } else {
                return arr[pos];
            }
        }

        return null;
    }*/
    public static Employee fibonacciSearch(Employee[] arr, String key) {
        int n = arr.length;
        int fib2 = 0; // (m-2)'th Fibonacci No.
        int fib1 = 1; // (m-1)'th Fibonacci No.
        int fib = fib2 + fib1; // m'th Fibonacci


        while (fib < n) {
            fib2 = fib1;
            fib1 = fib;
            fib = fib2 + fib1;
        }

        int offset = -1; // Initially, offset is 0

        while (fib > 1) {
            int i = Math.min(offset + fib2, n - 1);

            if (arr[i].getName().compareTo(key) < 0) {
                fib = fib1;
                fib1 = fib2;
                fib2 = fib - fib1;
                offset = i;
            } else if (arr[i].getName().compareTo(key) > 0) {
                fib = fib2;
                fib1 = fib1 - fib2;
                fib2 = fib - fib1;
            } else {
                return arr[i];
            }
        }

        if (fib1 == 1 && arr[offset + 1].getName().equals(key)) {
            return arr[offset + 1];
        }

        return null;
    }

    public static void main(String[] args) {

        Employee[] productionworkers = new Employee[50];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name to search: ");
        String key = scanner.nextLine();

        try {
            FileReader fileReader = new FileReader("C:\\Users\\Kozochka\\IdeaProjects\\lab0_ASDC\\src\\employee.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; i < productionworkers.length; i++) {
                productionworkers[i] = new Employee();
                productionworkers[i].readingFile(bufferedReader);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }

        System.out.println("Unsorted Array:");
        Formatter form = new Formatter(System.out);
        for (int i = 0; i < productionworkers.length; i++) {
            productionworkers[i].writeTable(form);
        }

        Arrays.sort(productionworkers); // Sorting the array for binary search
        System.out.println("Sorted Array:");
        for (Employee employee : productionworkers) {
            employee.writeTable(form);
        }



        long startTime = System.nanoTime();
        Employee result = sequentialSearch(productionworkers, key);
        long endTime = System.nanoTime();

        System.out.println("Result of sequential search: ");
        if (result != null) {
            result.writeElement();
        } else {
            System.out.println("No matching employee found.");
        }

        System.out.println("Time taken for sequential search: " + (endTime - startTime) + " ns.");
        startTime = System.nanoTime();
        result = binarySearch(productionworkers, key);
        endTime = System.nanoTime();

        System.out.println("Result of binary search: ");
        if (result != null) {
            result.writeElement();
        } else {
            System.out.println("No matching employee found.");
        }

        System.out.println("Time taken for binary search: " + (endTime - startTime) + " ns.");


      /*  startTime = System.nanoTime();

        endTime = System.nanoTime();

        System.out.println("Result of interpolationSearch: ");
        result = interpolationSearch(productionworkers, key);
        if (result != null) {
            result.writeElement();
        } else {
            System.out.println("No matching employee found.");
        }

        System.out.println("Time taken for interpolationSearch: " + (endTime - startTime) + " ns.");*/

        startTime = System.nanoTime();
        result = fibonacciSearch(productionworkers, key);
        endTime = System.nanoTime();

        System.out.println("Result of Fibonacci : ");
        if (result != null) {
            result.writeElement();
        } else {
            System.out.println("No matching employee found.");
        }

        System.out.println("Time taken for Fibonacci: " + (endTime - startTime) + " ns.");

    }
}