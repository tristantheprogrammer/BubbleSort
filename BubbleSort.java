import java.io.*;
import java.util.*;

public class BubbleSort {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Generate an array of random integers and store it in a file");
            System.out.println("2. Read an existing file containing a list of integers, sort it, and store the sorted array in another file");
            System.out.println("3. Exit");
    
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                        int arrayLength;
                        do {
                            System.out.print("Enter the array length (0 - 100): ");
                            arrayLength = scanner.nextInt();
                            if (arrayLength < 0 || arrayLength > 100) {
                                System.out.println("Array length must be between 0 and 100.");
                            }
                        } while (arrayLength < 0 || arrayLength > 100);
    
                        int[] randomArray = createRandomArray(arrayLength);
                        writeArrayToFile(randomArray, "random_array.txt");
                        System.out.println("Random array has been generated and stored in 'random_array.txt'");
                        break;
                    case 2:
                        System.out.print("Enter the input file name: ");
                        String inputFilename = scanner.next();
                        int[] arrayToSort = readFileToArray(inputFilename);
                        bubbleSort(arrayToSort);
                        System.out.print("Enter the output file name: ");
                        String outputFilename = scanner.next();
                        writeArrayToFile(arrayToSort, outputFilename);
                        System.out.println("Array has been sorted and stored in '" + outputFilename + "'");
                        break;
                    case 3:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                scanner.next(); // Consume the non-integer input
            }
        }
    }
    
    public static int[] createRandomArray(int arrayLength) {
        if (arrayLength < 0 || arrayLength > 100) {
            throw new IllegalArgumentException("Array length must be between 0 and 100.");
        }
    
        int[] randomArray = new int[arrayLength];
        Random rand = new Random();
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = rand.nextInt(101);
        }
        return randomArray;
    }
    
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.println(value);
        }
    }
    
    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int value : array) {
                writer.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line);
                arrayList.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}