package processor.matrix;

import java.util.function.BiConsumer;
import java.util.function.ToDoubleBiFunction;

/**
 * @author Yukitteru on 18.11.2020
 * @project Numeric_Matrix_Processor
 */
public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    public Matrix(int rows, int cols, ToDoubleBiFunction<Integer, Integer> function) {
        this(rows, cols);
        iterate((r, c) -> matrix[r][c] = function.applyAsDouble(r, c));
    }

    public void iterate(BiConsumer<Integer, Integer> consumer) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                consumer.accept(r, c);
            }
        }
    }

    public double getMatrix(int r, int c) {
        return matrix[r][c];
    }

    public void setMatrix(int r, int c, double value) {
        this.matrix[r][c] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
