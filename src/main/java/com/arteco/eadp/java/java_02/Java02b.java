package com.arteco.eadp.java.java_02;

public class Java02b {
    public static void main(String[] args) {
        final int MAX = 1000;
        for (int i = 1; i < MAX; i++) {
            boolean divisible = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    divisible = true;
                    break;
                }
            }
            if (!divisible) {
                System.out.print(" " + i);
            }
        }
    }
}