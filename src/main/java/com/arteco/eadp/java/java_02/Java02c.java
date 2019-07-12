package com.arteco.eadp.java.java_02;

public class Java02c {
    public static void main(String[] args) {
        int array[] = { 15, 23, 10, 14, 5, 8, 12, 6, 7, 25, 18, 13, 21, 22, 16, 11, 3, 9, 17, 4, 19, 1, 24, 20, 2 };

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }

        for(int i : array){
            System.out.print(" "+i);
        }
    }
}