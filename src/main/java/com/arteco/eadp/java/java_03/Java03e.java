package com.arteco.eadp.java.java_03;

public class Java03e {
    public static void main(String args[]) {

        int[] array = new int[] { 1, 3, 3, 0, 5, 7 };
        imprimirMediaArray(array);

        array = new int[] { 1, 3, 3, -3, 5, 7 };
        imprimirMediaArray(array);
    }

    public static void imprimirMediaArray(int[] array) {
        try {
            float media = media(array);
            System.out.println("La media es " + media);
        } catch (ValorNegativoException e) {
            System.out.println("El array contiene valores negativos");
        }
    }

    public static float media(int[] array) throws ValorNegativoException {
        float acumulado = 0;
        int contador = 0;
        if (array != null) {
            for (int i : array) {
                if (i < 0) {
                    throw new ValorNegativoException();
                }
                acumulado += i;
                contador++;
            }
        }
        return acumulado / contador;
    }
}