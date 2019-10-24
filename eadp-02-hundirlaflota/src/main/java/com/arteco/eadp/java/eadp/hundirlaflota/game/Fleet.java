package com.arteco.eadp.java.eadp.hundirlaflota.game;

public class Fleet {
    private final String name;
    private final int size;
    private final int quantity;

    public Fleet(String name, int size, int quantity) {
        this.name = name;
        this.size = size;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }
}
