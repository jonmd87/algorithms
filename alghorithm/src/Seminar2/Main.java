package Seminar2;

import Seminar2.Heapsort;

import java.util.*;

public class Main {
    public static void main(String[] args) {
            Random random = new Random();
            int[] arr = createAndFillArray(random.nextInt(10, 50));
            printArray(arr);
            Heapsort heapsort = new Heapsort();
            heapsort.sort(arr);
            printArray(arr);
        }

        public static int[] createAndFillArray(int size) {
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            Collections.shuffle(list);
            return list.stream().mapToInt(Integer::intValue).toArray();
        }

        public static void printArray(int[] arr) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
