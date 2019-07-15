package com.arteco.eadp.java.java_03;

public class Java03a {
    public static void main(String[] args) {
        int array[] = { 15, 23, 10, 14, 5, 8, 12, 6, 7, 25, 18, 13, 21, 22, 16, 11, 3, 9, 17, 4, 19, 1, 24, 20, 2 };

        System.out.println(max_v1(array));
        System.out.println(max_v2(array));
        System.out.println(max_v2(array));

    }

    public static int max_v1(int[] enteros) {
        int max = 0;
        for (int i : enteros) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int max_v2(int[] enteros) {
        if (enteros == null) {
            return 0;
        }
        int max = 0;
        for (int i : enteros) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int max_v3(int[] enteros) {
        int max = 0;
        if (enteros != null) {
            for (int i : enteros) {
                if (i > max) {
                    max = i;
                }
            }
        }
        return max;
    }
}