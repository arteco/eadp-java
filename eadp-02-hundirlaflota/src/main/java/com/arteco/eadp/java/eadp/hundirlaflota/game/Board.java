package com.arteco.eadp.java.eadp.hundirlaflota.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Board {
    private final int N;
    private final List<Boat> boats = new ArrayList<>();
    private BoardCell[][] matrix;

    public Board(int N) {
        this.N = N;
        init(N);
    }

    public void clear() {
        init(N);
    }

    private void init(int n) {
        this.matrix = new BoardCell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = new BoardCell();
            }
        }
    }

    public void place(List<Fleet> fleets) {
        this.boats.clear();
        for (Fleet flota : fleets) {
            place(flota);
        }
    }

    private void place(Fleet fleet) {
        for (int i = 0; i < fleet.getQuantity(); i++) {
            Boat boat = new Boat(fleet, i);
            boats.add(boat);
            place(boat);
        }
    }

    private void place(Boat boat) {
        // se realizará una serie de intentos para colocar los barcos
        // si no hay manera de encajarlos se lanza un error.
        // puede ser debido a que haya demasiados barcos o la matriz sea muy pequeña
        for (int i = 0; i < 10000; i++) {
            int x = (int) (Math.random() * N);
            int y = (int) (Math.random() * N);
            Pos pos = new Pos(x, y);
            boolean horizontal = Math.random() > 0.5;
            if (isFree(pos, horizontal, boat.getSize())) {
                alloc(pos, horizontal, boat);
                return;
            }
        }

        throw new IllegalArgumentException("No caben más barcos!");
    }

    private void alloc(Pos pos, boolean horizontal, Boat boat) {
        for (int i = 0; i < boat.getSize(); i++) {
            int incX = horizontal ? i : 0;
            int incY = !horizontal ? i : 0;
            matrix[pos.y + incY][pos.x + incX].alloc(boat, i);
        }
    }

    private boolean isFree(Pos pos, boolean horizontal, int size) {
        if (!isValid(pos, N) || !isValid(pos.toFinal(horizontal, size), N)) {
            return false;
        }
        try {
            for (int i = 0; i < size; i++) {
                int incX = horizontal ? i : 0;
                int incY = !horizontal ? i : 0;
                if (matrix[pos.y + incY][pos.x + incX].isBusy()) {
                    return false;
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            return false;
        }
    }

    private boolean isValid(Pos pos, int size) {
        return pos != null && pos.x >= 0 && pos.x < size && pos.y >= 0 && pos.y < size;
    }

    public String print() {
        StringBuilder stb = new StringBuilder();

        stb.append("   ");
        for (int x = 0; x < N; x++) {
            stb.append(String.format("%02d", x)).append(" ");
        }
        stb.append("\n");

        for (int y = 0; y < N; y++) {
            stb.append(String.format("%02d", y)).append(" ");
            for (int x = 0; x < N; x++) {
                BoardCell c = matrix[y][x];

                String cell = c.isBusy() ? c.getBoat().getName().charAt(0) + c.getIdx().toString() : "··";
                if (c.isImpact()) {
                    cell = "XX";
                }
                stb.append(cell);
                stb.append(" ");
            }
            stb.append("\n");
        }
        return stb.toString();
    }

    public Impact evaluateImpact(Pos pos) {
        ImpactType type;
        if (isValid(pos, N)) {
            BoardCell c = matrix[pos.y][pos.x];
            type = c.evaluateImpact();
        } else {
            type = ImpactType.OUT;
        }
        return new Impact(pos, type);
    }

    public boolean isAlive() {
        for (Boat b : boats) {
            if (b.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
