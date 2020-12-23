package com.qod.lostboxes.math.mat;

import com.qod.lostboxes.math.mat.IMatrix;
import com.qod.lostboxes.math.mat.Matrix4f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix4fTest {

    @Test
    void add() {
        Matrix4f a = new Matrix4f(0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0);
        Matrix4f res = new Matrix4f(1,0,0,0,  0,1,0,0,  0,0,1,0,  0,0,0,1);
        assertEquals(res, a.add(new Matrix4f().identity()));
    }

    @Test
    void mul() {
        Matrix4f a = new Matrix4f(0,1,2,3,  4,5,6,7,  8,9,10,11,  12,13,14,15);
        Matrix4f b = new Matrix4f(15,14,13,12,  11,10,9,8,  7,6,5,4,  3,2,1,0);
        Matrix4f res = new Matrix4f(34,28,22,16,  178,156,134,112,  322,284,246,208,  466,412,358,304);
        assertEquals(res, a.mul(b));
        assertEquals(a, a.mul(a.identity()));
    }

    @Test
    void scale() {
        Matrix4f b = new Matrix4f(15,14,13,12,  11,10,9,8,  7,6,5,4,  3,2,1,0);
        Matrix4f res = new Matrix4f(30,28,26,24,  22,20,18,16,  14,12,10,8,  6,4,2,0);
        assertEquals(res, b.scale(2));
    }

    @Test
    void transpose() {
        Matrix4f b = new Matrix4f(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        assertEquals(new Matrix4f(1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 16), b.transpose());

        IMatrix<Float> a = new Matrix4f().translation(1f, 1f, 1f).transpose();
        assertEquals(new Matrix4f(1,0,0,0,  0,1,0,0,  0,0,1,0, 1,1,1,1), a);
    }

    @Test
    void rotation() {
    }

    @Test
    void determinant() {
        assertEquals(-144, new Matrix4f(1,1,2,3,  4,5,6,7,  8,-9,10,11,  12,13,14,15).determinant());
    }
}