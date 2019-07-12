package com.arteco.eadp.java.java_02;

public class Java02a {
    public static void main(String[] args) {
        int array[] = new int[100];
        // inicializar el array
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        // generar la media
        int acumulado = 0;
        for (int num : array){
            acumulado += num;
        }
        float media = (float) acumulado / array.length;
        System.out.println("La media es de "+media);
    }
}