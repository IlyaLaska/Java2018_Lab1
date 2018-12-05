package com.laska;

import java.util.Scanner;

class Matrix {
    private byte[][] matrix;

    Matrix() {
        byte size = 0;
        System.out.print("Please enter desired Matrix size: ");
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            if (scan.hasNextByte()) {
                size = scan.nextByte();
                break;
            } else {
                System.out.println("Please enter a Byte!");
                scan.next();
            }
        }
//        scan.close();//TODO why does this prevent scanner in fillMatrix from working????
        System.out.println("The size will be: " + size);
        matrix = new byte[size][size];
    }

    /**
     * prints the matrix to console
     */
    void print() {
        System.out.println("The matrix is:");
        for (byte[] matrixRow : matrix) {
            for (byte b : matrixRow) {
                System.out.print(b + "  ");
            }
            System.out.println();
        }
    }

    /**
     * fill Matrix with values from keyboard
     */
    void fillMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please fill the matrix with values:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Please enter element " + (i + 1) + " " + (j + 1) + ":");
                while (scanner.hasNext()) {
                    if (scanner.hasNextByte()) {
                        matrix[i][j] = scanner.nextByte();
                        scanner.nextLine();//TODONE how to flush rest of input
                        break;
                    } else {
                        System.out.println("Please enter a Byte!");
                        scanner.next();
                    }
                }
            }
        }
        scanner.close();
    }

    /**
     * fill Matrix with random values
     */
    void fillMatrixRand() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (byte) ((Math.random() * 256) - 128);
            }
        }
    }

    /**
     * Lab task - puts min element in desired position
     * (arguments start AT 0)!
     *
     * @param Y vertical desired position for min element
     * @param X horizontal desired position for min element
     */
    void moveMin(int Y, int X) {
        if (Y >= matrix.length || X >= matrix.length) {
            System.out.println("invalid arguments");
            return;
        }
        int min = 127;
        int minY = 0;
        int minX = 0;
        //finding min value and its X & Y position
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minY = i;
                    minX = j;
                }
            }
        }
        //moving horizontal lines
        if (Y != minY) {//on different lines
            if (minY < Y) {
                while (minY < Y) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[minY][i];
                        matrix[minY][i] = matrix[Y][i];
                        matrix[Y][i] = temp;
                    }
                    minY++;
                }
            } else {//Y < minY
                while (Y < minY) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[Y][i];
                        matrix[Y][i] = matrix[minY][i];
                        matrix[minY][i] = temp;
                    }
                    minY++;
                }
            }
        }
        //moving vertical lines
        if (X != minX) {//on different lines
            if (minX < X) {
                while (minX < X) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[i][minX];
                        matrix[i][minX] = matrix[i][X];
                        matrix[i][X] = temp;
                    }
                    minX++;
                }
            } else {//X < minX
                while (X < minX) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[i][X];
                        matrix[i][X] = matrix[i][minX];
                        matrix[i][minX] = temp;
                    }
                    minX++;
                }
            }
        }
    }
}
