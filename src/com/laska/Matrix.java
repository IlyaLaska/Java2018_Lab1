package com.laska;

import java.util.Scanner;

class Matrix {
    private byte[][] matrix;

    Matrix() {
        byte size = readSize();
        matrix = new byte[size][size];
    }

    private byte readSize() {
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
        return size;
    }

    /**
     * prints the matrix to console
     */
    void print() {
        System.out.println("The matrix is:");
        for (byte[] matrixRow : matrix) {
            for (byte b : matrixRow) {
                System.out.format("%5d", b);
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

    private void swapRows(int Y, int minY) {//minY - our row, Y - where to move it
//        System.out.println("Y: " + Y + "  minY: " + minY);
        if (Y != minY) {//on different lines
            if (minY < Y) {//have to move down
                while (minY < Y) {//TODO try while(lower++)
//                    System.out.println("minY < Y  " + "Y: " + Y + "  minY: " + minY);
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[minY][i];
                        matrix[minY][i] = matrix[minY+1][i];
                        matrix[minY+1][i] = temp;
                    }
                    minY++;
                }
            } else {//Y < minY//have to move up
                while (Y < minY) {//TODO try while(lower++)
//                    System.out.println("Y < minY  " + "Y: " + Y + "  minY: " + minY);
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[minY][i];
                        matrix[minY][i] = matrix[minY-1][i];
                        matrix[minY-1][i] = temp;
                    }
                    minY--;
                }
            }
        }
    }

    private void swapCols(int X, int minX) {
        if (X != minX) {//on different lines
            if (minX < X) {
                while (minX < X) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {//go through all values in column
                        byte temp = matrix[i][minX];
                        matrix[i][minX] = matrix[i][minX+1];
                        matrix[i][minX+1] = temp;
                    }
                    minX++;
                }
            } else {//X < minX
                while (X < minX) {//TODO try while(lower++)
                    for (int i = 0; i < matrix.length; i++) {
                        byte temp = matrix[i][minX];
                        matrix[i][minX] = matrix[i][minX-1];
                        matrix[i][minX-1] = temp;
                    }
                    minX--;
                }
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
        byte min = 127;//Max Byte value
        int minY = 0;
        int minX = 0;
        //finding min value and its X & Y position
        for (byte i = 0; i < matrix.length; i++) {
            for (byte j = 0; j < matrix.length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minY = i;
                    minX = j;
                }
            }
        }
        System.out.println("Moving " + matrix[minY][minX] + " to pos: " + Y + "  " + X);
        //moving horizontal lines
        swapRows(Y, minY);
        //moving vertical lines
        swapCols(X, minX);
    }

    /**
     * moves min element to the place read from user input
     */
    void moveMinRead() {
        int[] pos = {0, 0};//Y, X positions
        Scanner scanner = new Scanner(System.in);
        a:
        for (int i = 0; i < pos.length; i++) {
            if (i == 0) System.out.print("Please enter Y pos: ");
            else if (i == 1) System.out.print("Please enter X pos: ");
//            b:
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    pos[i] = scanner.nextInt();
                    if(pos[i] > (matrix.length-1)) {
                        System.out.println("Please enter a valid Integer!");
                        i--;
//                        break b;
                        continue a;
                    }
                    scanner.nextLine();//TODONE how to flush rest of input
                    break;
                } else {
                    System.out.println("Please enter an Integer!");
                    scanner.next();
                }
            }
        }
//        System.out.println(pos[0] + "     " + pos[1]);
        moveMin(pos[0], pos[1]);
    }
}
