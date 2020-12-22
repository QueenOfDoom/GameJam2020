package com.qod.lostboxes.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix3fTest {

    @Test
    void add() {
        Matrix3f a = new Matrix3f(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Matrix3f b = new Matrix3f(8, 7, 6, 5, 4, 3, 2, 1, 0);
        assertEquals(new Matrix3f(8, 8, 8, 8, 8, 8, 8, 8, 8), a.add(b));
    }

    @Test
    void scale() {
        Matrix3f a = new Matrix3f(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(new Matrix3f(0, 2, 4, 6, 8, 10, 12, 14, 16), a.scale(2));
    }

    @Test
    void transpose() {
        Matrix3f a = new Matrix3f(
                0, 1, 2,
                3, 4, 5,
                6, 7, 8);
        assertEquals(new Matrix3f(0, 3, 6, 1, 4, 7, 2, 5, 8), a.transpose());
    }

    @Test
    void identity() {
        IMatrix<Float> a = new Matrix3f().identity();
        assertEquals(new Matrix3f(1, 0, 0, 0, 1, 0, 0, 0, 1), a);
    }

    @Test
    void inverse() {
        Matrix3f matrix = new Matrix3f(1, 2, 3, 3, 2, 1, 2, 1, 3);
        assertEquals(new Matrix3f(-5, 3, 4, 7, 3, -8, 1, -3, 4).scale(1.0/12.0), matrix.inverse());
    }

    @Test
    void rotation() {
        double rad = Math.toRadians(60);
        IMatrix<Float> rot = new Matrix3f().rotation(rad);
        assertEquals(new Matrix3f(
                1/2.0f, (float) -Math.sqrt(3)/2, 0,
                (float) Math.sqrt(3)/2, 1/2.0f, 0,
                0, 0, 1), rot);
    }

    @Test
    void translation() {
        IMatrix<Float> translation = new Matrix3f().translation(2f, 3f);
        assertEquals(new Matrix3f(1, 0, 2, 0, 1, 3, 0, 0, 1), translation);
    }

    @Test
    void determinant() {
        float det = new Matrix3f(1, 2, 3, 4, 5, 6, 7, 8, 9).determinant();
        assertEquals(0, det);
    }

    @Test
    void isSquare() {
        assertTrue(new Matrix3f(0, 0, 0, 0, 0, 0, 0, 0, 0).isSquare());
    }

    @Test
    void get() {
        assertEquals(4, new Matrix3f(0, 1, 2, 3, 4, 5, 6, 7, 8).get(1, 1));
    }

    @Test
    void getRow() {
        assertArrayEquals(new Float[]{3f, 4f, 5f}, new Matrix3f(0, 1, 2, 3, 4, 5, 6, 7, 8).getRow(1));
    }

    @Test
    void getColumn() {
        assertArrayEquals(new Float[]{1f, 4f, 7f}, new Matrix3f(0, 1, 2, 3, 4, 5, 6, 7, 8).getColumn(1));
    }
}