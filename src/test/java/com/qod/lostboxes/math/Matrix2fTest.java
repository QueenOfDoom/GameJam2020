package com.qod.lostboxes.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2fTest {

    @Test
    void add() {
        Matrix2f a = new Matrix2f(0, 0, 1, 1);
        IMatrix<Float> b = new Matrix2f().identity();
        assertEquals(new Matrix2f(1, 0, 1, 2), a.add(b));
    }

    @Test
    void mul() {
        Matrix2f a = new Matrix2f(0, 1, 2, 3);
        Matrix2f b = new Matrix2f(9, 8, 7, 6);
        assertEquals(new Matrix2f(23, 20, 55, 48), a.mul(b));
    }

    @Test
    void scale() {
        Matrix2f a = new Matrix2f(0, 1, 2, 3);
        assertEquals(new Matrix2f(0, 2, 4, 6), a.scale(2));
    }

    @Test
    void transpose() {
        Matrix2f a = new Matrix2f(1, 2, 3, 4);
        assertEquals(new Matrix2f(1, 3, 2, 4), a.transpose());
    }

    @Test
    void identity() {
        IMatrix<Float> a = new Matrix2f().identity();
        assertEquals(new Matrix2f(1, 0, 0, 1), a.identity());
    }

    @Test
    void rotation() {
        IMatrix<Float> a = new Matrix2f().rotation(Math.toRadians(60));
        assertEquals(new Matrix2f(0.5f, (float) -Math.sqrt(3)/2, (float) Math.sqrt(3)/2, 0.5f), a);
    }

    @Test
    void translation() {
        Matrix2f mat = new Matrix2f(0, 0, 0, 0);
        Exception e = assertThrows(UnsupportedOperandException.class, () -> mat.translation(1f, 1f));
    }

    @Test
    void determinant() {
        assertEquals(-2f, new Matrix2f(1, 2, 3, 4).determinant());
    }

    @Test
    void isSquare() {
        assertTrue(new Matrix2f(0, 0, 0, 0).isSquare());
    }

    @Test
    void get() {
        assertEquals(2, new Matrix2f(0, 1, 2, 3).get(0, 1));
    }

    @Test
    void getRow() {
        assertArrayEquals(new Float[]{0f, 1f}, new Matrix2f(0, 1, 2, 3).getRow(0));
    }

    @Test
    void getColumn() {
        assertArrayEquals(new Float[]{0f, 2f}, new Matrix2f(0, 1, 2, 3).getColumn(0));
    }
}