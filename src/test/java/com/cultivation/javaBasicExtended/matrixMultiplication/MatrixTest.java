package com.cultivation.javaBasicExtended.matrixMultiplication;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Difficulty(DifficultyLevel.EASY)
class MatrixTest {
    @Test
    void should_throws_if_matrix_array_is_null() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Matrix(null));
        assertEquals("Raw matrix is null", exception.getMessage());
    }

    @Test
    void should_throws_if_matrix_array_contains_0_rows() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Matrix(new int[0][]));
        assertEquals("Raw matrix contains 0 row", exception.getMessage());
    }

    @Test
    void should_throws_if_matrix_array_contains_null_row() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Matrix(new int[][] {
                new int[3],
                null
            }));
        assertEquals("Raw matrix contains null row", exception.getMessage());
    }

    @Test
    void should_throws_if_matrix_array_contains_0_length_row() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Matrix(new int[][] {
                new int[0],
                new int[0]
            }));
        assertEquals("At least one row of raw matrix contains 0 column", exception.getMessage());
    }

    @Test
    void should_throws_if_matrix_array_is_not_rectangle() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Matrix(new int[][] {
                new int[2],
                new int[1]
            }));
        assertEquals("Raw matrix is not rectangle", exception.getMessage());
    }

    @Test
    void should_throws_if_matrix_is_null() {
        final int[][] rawMatrix = new int[][] {
            new int[2],
            new int[2]
        };

        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(null, null));
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(new Matrix(rawMatrix), null));
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(null, new Matrix(rawMatrix)));
    }

    @Test
    void should_throws_if_matrix_dimension_not_correct() {
        int left[][] = { {3,2,3}, {5,9,8} };
        int right[][] = { {4,7}, {9,3}, {8,1}, {1, 1} };

        assertThrows(
            IllegalArgumentException.class,
            () -> Matrix.multiply(new Matrix(left), new Matrix(right)));
    }

    @Test
    void should_multiply_matrix() {
        int left[][] = { {3,2,3}, {5,9,8} };
        int right[][] = { {4,7}, {9,3}, {8,1} };

        // 3 2 3   4 7
        // 5 9 8   9 3
        //         8 1

        final int[][] expected = {
            {12 + 18 + 24, 21 + 6 + 3},
            {20 + 81 + 64, 35 + 27 + 8}
        };

        Matrix result = Matrix.multiply(new Matrix(left), new Matrix(right));
        assertEquals(new Matrix(expected), result);
    }
}
