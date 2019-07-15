package com.arteco.eadp.java.java_03;

public class Java03b {
    public static void main(String[] args) {
        int array[] = { 15, 23, 10, 14, 5, 8, 12, 6, 7, 25, 18, 13, 21, 22, 16, 11, 3, 9, 17, 4, 19, 1, 24, 20, 2 };

        reemplazarPrimos_v1(array);
        printArray(array);

        array = new int[] { 15, 23, 10, 14, 5, 8, 12, 6, 7, 25, 18, 13, 21, 22, 16, 11, 3, 9, 17, 4, 19, 1, 24, 20, 2 };

        printArray(reemplazarPrimos_v2(array));
        printArray(reemplazarPrimos_v3(array));
        printArray(reemplazarPrimos_v4(array));
    }

    public static void printArray(int[] array) {
        if (array != null) {
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
    }

    public static void reemplazarPrimos_v1(int array[]) {
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            boolean isPrimo = true;
            for (int j = 2; j < num; j++) {
                if (num % j == 0) {
                    isPrimo = false;
                    break;
                }
            }
            if (isPrimo) {
                array[i] = 0;
            }
        }
    }

    public static int[] reemplazarPrimos_v2(int array[]) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            boolean isPrimo = true;
            for (int j = 2; j < num; j++) {
                if (num % j == 0) {
                    isPrimo = false;
                    break;
                }
            }
            if (isPrimo) {
                num = 0;
            }
            result[i] = num;
        }
        return result;
    }

    public static int[] reemplazarPrimos_v3(int array[]) {
        int[] result;
        if (array != null) {
            result = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                int num = array[i];
                boolean isPrimo = true;
                for (int j = 2; j < num; j++) {
                    if (num % j == 0) {
                        isPrimo = false;
                        break;
                    }
                }
                if (isPrimo) {
                    num = 0;
                }
                result[i] = num;
            }
        } else {
            result = new int[0];
        }
        return result;
    }

    public static int[] reemplazarPrimos_v4(int array[]) {
        int[] result;
        if (array != null) {
            result = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                if (isPrimo(array[i])) {
                    result[i] = 0;
                } else {
                    result[i] = array[i];
                }
            }
        } else {
            result = new int[0];
        }
        return result;
    }

    public static boolean isPrimo(int i) {
        boolean res = true;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                res = false;
                break;
            }
        }
        return res;
    }
}