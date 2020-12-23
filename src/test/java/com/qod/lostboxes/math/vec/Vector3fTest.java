package com.qod.lostboxes.math.vec;

import com.qod.lostboxes.math.mat.IMatrix;
import com.qod.lostboxes.math.mat.Matrix3f;
import com.qod.lostboxes.math.vec.Vector3f;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class Vector3fTest {

    @Test
    void transform() {
        Vector3f vec = new Vector3f(1, 0, 0);
        IMatrix<Float> rotation = new Matrix3f().rotation(0, 0, Math.PI);
        assertEquals(new Vector3f(-1, 0, 0), vec.transform(rotation));
    }

    @Test
    void add() {
        Vector3f a = new Vector3f(1, 1, 1);
        Vector3f b = new Vector3f(2, 3, -1);
        assertEquals(new Vector3f(3, 4, 0), a.add(b));
    }

    @Test
    void sub() {
        Vector3f a = new Vector3f(1, 1, 1);
        Vector3f b = new Vector3f(2, 3, -1);
        assertEquals(new Vector3f(-1, -2, 2), a.sub(b));
    }

    @Test
    void mul() {
        Vector3f a = new Vector3f(1, 1, 1);
        Vector3f b = new Vector3f(2, 3, -1);
        assertEquals(new Vector3f(-4, 3, 1), a.mul(b));
    }

    @Test
    void dot() {
        Vector3f a = new Vector3f(1, 1, 1);
        Vector3f b = new Vector3f(2, 3, -1);
        assertEquals(4, a.dot(b));
    }

    @Test
    void scale() {
        Vector3f a = new Vector3f(1, 1, 1);
        assertEquals(new Vector3f(2, 2, 2), a.scale(2));
    }

    @Test
    void negate() {
        Vector3f a = new Vector3f(1, 1, 1);
        assertEquals(new Vector3f(-1, -1, -1), a.negate());
    }

    @Test
    void unit() {
        Vector3f a = new Vector3f(4, 0, 0);
        assertEquals(new Vector3f(1, 0, 0), a.unit());
        Vector3f b = new Vector3f(4, 4, 4);
        assertEquals(new Vector3f((float) Math.sqrt(3)/3, (float) Math.sqrt(3)/3, (float) Math.sqrt(3)/3), b.unit());
    }

    @Test
    void norm() {
        Vector3f a = new Vector3f(1, 4, 8);
        assertEquals(new BigDecimal(9), a.norm());
    }

    @Test
    void size() {
        Vector3f a = new Vector3f(0, 0, 0);
        assertEquals(3, a.size());
    }
}