package com.arteco.eadp.java.java_03;

public class Java03d {
    public static void main(String[] args) {
        final int[][] matriz = new int[][] { 
            { 0, 0, 1, 1, 1 }, 
            { 1, 0, 0, 1, 1 }, 
            { 1, 1, 0, 0, 1 }, 
            { 1, 1, 1, 0, 1 },
            { 1, 1, 1, 0, 0 } };

        int[] posInicial = new int[] { 0, 0 };

        int[] pos = posInicial;
        while (!isPosFinal(matriz, pos)) {
            int[] nuevaPosDer = avanzarDerecha(pos);
            if (isLibre(matriz, nuevaPosDer)) {
                pos = nuevaPosDer;
                System.out.println("derecha");
            } else {
                int[] nuevaPosAbj = avanzarAbajo(pos);
                if (isLibre(matriz, nuevaPosAbj)) {
                    pos = nuevaPosAbj;
                    System.out.println("abajo");
                }

            }
        }
        System.out.println("Llegado al final");
    }

    private static int[] avanzarDerecha(int[] pos) {
        return new int[] { pos[0], pos[1] + 1 };
    }

    private static int[] avanzarAbajo(int[] pos) {
        return new int[] { pos[0]+1, pos[1] };
    }

    private static boolean isLibre(int[][] matriz, int[] pos) {
        boolean result = false;
        try{
            result =  matriz[pos[0]][pos[1]] == 0;
        } catch (ArrayIndexOutOfBoundsException aiobe){
            result = false;
        }
        return result;
    }

    public static boolean isPosFinal(int[][] matriz, int[] pos) {
        return pos != null && pos.length == 2 && pos[0] == pos[1] && pos[0] == matriz.length - 1;
    }

}