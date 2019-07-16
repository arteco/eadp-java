package com.arteco.eadp.java.java_03;

public class Java03c {
    public static void main(String[] args) {
        int[] array = new int[] { 1, 3, 3, 0, 5, 7 };
        System.out.println("máximo " + maximo(array));
        System.out.println("pares " + contarPares(array));
        System.out.println("media " + media(array));
    }

    public static int maximo(int[] array) {
        int result = 0;
        if (array != null) {
            for (int i : array) {
                if (i > result) {
                    result = i;
                }
            }
        }
        return result;
    }

    public static int contarPares(int[] array) {
        int result = 0;
        if (array != null) {
            for (int i : array) {
                if (i % 2 == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static float media(int[] array) {
        float acumulado = 0;
        int contador = 0;
        if (array != null) {
            for (int i : array) {
                acumulado += i;
                contador++;
            }
        }
        return acumulado / contador;
    }
}