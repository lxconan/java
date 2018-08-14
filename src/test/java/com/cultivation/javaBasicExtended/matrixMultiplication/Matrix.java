package com.cultivation.javaBasicExtended.matrixMultiplication;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        if (matrixArray == null) {throw new IllegalArgumentException("Raw matrix is null");}
        ensureValidRectMatrix(matrixArray);
        this.storage = matrixArray;
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null) { throw new IllegalArgumentException(); }
        int leftColumns = left.columns();
        if (leftColumns != right.rows()) { throw new IllegalArgumentException(); }
        int leftRows = left.rows();
        int rightColumns = right.columns();

        int[][] result = new int[leftRows][rightColumns];
        for (int leftRowIndex = 0; leftRowIndex < leftRows; ++leftRowIndex) {
            for (int rightColumnIndex = 0; rightColumnIndex < rightColumns; ++rightColumnIndex) {
                for (int leftColumnIndex = 0; leftColumnIndex < leftColumns; ++leftColumnIndex) {
                    result[leftRowIndex][rightColumnIndex] +=
                        left.storage[leftRowIndex][leftColumnIndex] * right.storage[leftColumnIndex][rightColumnIndex];
                }
            }
        }

        return new Matrix(result);
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start
    private static void ensureValidRectMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }

        boolean anyNull = Arrays.stream(matrix).anyMatch(Objects::isNull);
        if (anyNull) {
            throw new IllegalArgumentException("Raw matrix contains null row");
        }

        int columns = matrix[0].length;
        if (columns == 0) {
            throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
        }

        boolean notRectangular = Arrays.stream(matrix).anyMatch(row -> row.length != columns);
        if (notRectangular) {
            throw new IllegalArgumentException("Raw matrix is not rectangle");
        }
    }
    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
