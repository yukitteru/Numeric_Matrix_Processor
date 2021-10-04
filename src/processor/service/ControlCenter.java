package processor.service;

import processor.matrix.Matrix;
import processor.matrix.MatrixOperations;

import java.util.Scanner;

/**
 * @author Yukitteru on 18.11.2020
 * @project Numeric_Matrix_Processor
 */
public class ControlCenter {
    private static final Scanner USER_INPUT = new Scanner(System.in);

    public void run() {
        menu();
    }

    private static void menu() {
        Matrix m1, m2;
        Decision decision = null;
        while (decision != Decision.EXIT) {
            decision = input();
            switch (decision) {
                case ADD -> {
                    m1 = create();
                    m2 = create();
                    print(MatrixOperations.add(m1, m2));
                }
                case SCALAR_MULTIPLY -> {
                    m1 = create();
                    System.out.println("Enter multiplier");
                    print(MatrixOperations.scalarMultiply(m1, USER_INPUT.nextInt()));
                }
                case MULTIPLY -> {
                    m1 = create();
                    m2 = create();
                    print(MatrixOperations.multiply(m1, m2));
                }
                case TRANSPOSE -> {
                    System.out.println("\n1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line");
                    System.out.println("Your choice: ");
                    int dec = USER_INPUT.nextInt();
                    m1 = create();
                    switch (dec) {
                        case 1 -> print(MatrixOperations.transposeOverMainDiagonal(m1));
                        case 2 -> print(MatrixOperations.transposeOverSideDiagonal(m1));
                        case 3 -> print(MatrixOperations.transposeByVerticalLine(m1));
                        case 4 -> print(MatrixOperations.transposeByHorizontalLine(m1));
                    }
                }
                case DETERMINANT -> {
                    m1 = create();
                    System.out.println("The result is: \n" + MatrixOperations.determinant(m1));
                }
                case INVERSE -> {
                    m1 = create();
                    print(MatrixOperations.inverse(m1));
                }
            }
            System.out.println();
        }
    }

    private static Matrix create() {
        System.out.print("Enter matrix size: ");
        Matrix matrix = new Matrix(USER_INPUT.nextInt(), USER_INPUT.nextInt());
        System.out.println("Enter matrix: ");
        matrix.iterate((r, c) -> matrix.setMatrix(r, c, USER_INPUT.nextDouble()));
        return matrix;
    }

    private static Decision input() {
        for (Decision decision : Decision.values())
            System.out.println(decision);
        System.out.print("Your choice: ");
        Decision decision = Decision.findById(USER_INPUT.nextInt());
        return decision != null ? decision : Decision.EXIT;
    }

    private static void print(Matrix m) {
        if (m != null) m.iterate((r, c) -> System.out.print(m.getMatrix(r, c) + (c == m.getCols() - 1 ? "\n" : " ")));
        else System.out.println("ERROR");
    }
}
