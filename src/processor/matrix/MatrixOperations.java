package processor.matrix;

/**
 * @author Yukitteru on 18.11.2020
 * @project Numeric_Matrix_Processor
 */
public class MatrixOperations {

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getCols() == m2.getRows()) {
            return new Matrix(m1.getRows(), m2.getCols(), (r, c) -> {
                double sum = 0.0;
                for (int i = 0; i < m1.getCols(); i++)
                    sum += m1.getMatrix(r, i) * m2.getMatrix(i, c);
                return sum;
            });
        }
        return null;
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        return (m1.getRows() == m2.getRows() && m1.getCols() == m2.getCols()) ?
                new Matrix(m1.getRows(), m2.getCols(), (r, c) -> m1.getMatrix(r, c) + m2.getMatrix(r, c)) : null;
    }

    public static Matrix scalarMultiply(Matrix m, int multiplier) {
        return new Matrix(m.getRows(), m.getCols(), (r, c) -> m.getMatrix(r, c) * multiplier);
    }

    public static Matrix transposeOverMainDiagonal(Matrix m) {
        return new Matrix(m.getCols(), m.getRows(), (r, c) -> m.getMatrix(c, r));
    }

    public static Matrix transposeOverSideDiagonal(Matrix m) {
        return new Matrix(m.getCols(), m.getRows(), (r, c) -> m.getMatrix(m.getCols() - c - 1, m.getRows() - r - 1));
    }

    public static Matrix transposeByVerticalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getCols(), (r, c) -> m.getMatrix(r, m.getCols() - c - 1));
    }

    public static Matrix transposeByHorizontalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getCols(), (r, c) -> m.getMatrix(m.getRows() - r - 1, c));
    }

    public static Matrix removeRow(Matrix m, int index) {
        return new Matrix(m.getRows() - 1, m.getCols(), (r, c) -> m.getMatrix(r >= index ? r + 1 : r, c));
    }

    public static Matrix removeColumn(Matrix m, int index) {
        return new Matrix(m.getRows(), m.getCols() - 1, (r, c) -> m.getMatrix(r, c >= index ? c + 1 : c));
    }

    public static double determinant(Matrix m) {
        return m.getRows() == m.getCols() ? determinantRecursion(m) : 0.0;
    }

    /**
     * Calculate determinant of given matrix by recursion using adjoint
     *
     * @param m data matrix
     * @return determinant
     */
    private static double determinantRecursion(Matrix m) {
        if (m.getRows() == 1) return m.getMatrix(0, 0);
        double sum = 0.0;
        for (int i = 0; i < m.getRows(); i++)
            sum += cofactor(m, 0, i) * m.getMatrix(0, i);
        return sum;
    }

    /**
     * Calculate a cofactor in matrix
     *
     * @param m data matrix
     * @param r row number
     * @param c column number
     * @return cofactor = -1^(r+c) * minor
     */
    public static double cofactor(Matrix m, int r, int c) {
        return Math.pow(-1, r + c) * determinantRecursion(removeColumn(removeRow(m, r), c));
    }

    /**
     * Calculate an inverse matrix using adjoint
     *
     * @param m data matrix
     * @return new inverse matrix
     */
    public static Matrix inverse(Matrix m) {
        double invDeterminant = 1.0 / determinant(m);
        return transposeOverMainDiagonal(new Matrix(m.getRows(), m.getCols(), (r, c) -> cofactor(m, r, c) * invDeterminant));
    }

}
