package com.laska;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAVA Lab1!");
        Matrix m = new Matrix();
        m.print();
        m.fillMatrixRand();
        m.print();
        System.out.println("Moving minimal element to new position!");
        m.moveMinRead();
        m.print();
    }
}
