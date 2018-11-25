package com.laska;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAVA Lab1, by the one and only, ILYA LASKA!");
        Matrix m = new Matrix();
        m.print();
        m.fillMatrix();
        m.print();
        System.out.println("Moving!");
        m.moveMin(1, 1);
        m.print();
    }
}
