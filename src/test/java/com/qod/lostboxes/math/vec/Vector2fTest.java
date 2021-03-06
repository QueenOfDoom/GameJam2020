package com.qod.lostboxes.math.vec;

import com.qod.lostboxes.math.mat.IMatrix;
import com.qod.lostboxes.math.mat.Matrix3f;
import com.qod.lostboxes.math.vec.Vector2f;
import com.qod.lostboxes.math.vec.Vector3f;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class Vector2fTest {

    @Test
    void transform() {
        IMatrix<Float> mat1 = new Matrix3f().rotationZ(Math.toRadians(90.0));
        IMatrix<Float> mat2 = new Matrix3f().rotationZ(Math.toRadians(180.0));
        IMatrix<Float> mat3 = new Matrix3f().rotationZ(Math.toRadians(270.0));
        IMatrix<Float> mat4 = new Matrix3f().rotationZ(Math.toRadians(30));
        Vector3f v3 = new Vector3f(1, 0);
        Vector3f isoV = (Vector3f) v3.transform(mat4);
        System.out.printf("Isometric Unit Vector: (%f|%f)\n", isoV.getX(), isoV.getY());
        assertEquals(new Vector3f(0, 1), v3.transform(mat1));
        assertEquals(new Vector3f(-1, 0), v3.transform(mat2));
        assertEquals(new Vector3f(0, -1), v3.transform(mat3));
    }

    @Test
    void add() {
        Vector2f a = new Vector2f(1f, 2f);
        Vector2f b = new Vector2f(3f, 1f);
        assertEquals(new Vector2f(4f, 3f), a.add(b));
    }

    @Test
    void sub() {
        Vector2f a = new Vector2f(1f, 2f);
        Vector2f b = new Vector2f(3f, 1f);
        assertEquals(new Vector2f(-2f, 1f), a.sub(b));
    }

    @Test
    void dot() {
        Vector2f a = new Vector2f(1, 2);
        Vector2f b = new Vector2f(3, 4);
        assertEquals(11, a.dot(b));
    }

    @Test
    void scale() {
        Vector2f a = new Vector2f(2, 5);
        assertEquals(new Vector2f(4, 10), a.scale(2));
    }

    @Test
    void negate() {
        Vector2f x = new Vector2f(2, -3);
        assertEquals(new Vector2f(-2, 3), x.negate());
    }

    @Test
    void unit() {
        Vector2f simple = new Vector2f(8, 0);
        assertEquals(new Vector2f(1, 0), simple.unit());
        Vector2f difficult = new Vector2f(1, 1);
        assertEquals(new Vector2f((float) Math.sqrt(2)/2f, (float) Math.sqrt(2)/2f), difficult.unit());
    }

    @Test
    void norm() {
        BigDecimal five = new BigDecimal(5);
        Vector2f tri = new Vector2f(3, 4);
        assertEquals(five, tri.norm());
    }

    @Test
    void size() {
        Vector2f x = new Vector2f(0, 0);
        assertEquals(2, x.size());
    }

    @Test
    void equals() {
        Vector2f a = new Vector2f(1.0f, 1.101f);
        Vector2f b = new Vector2f(1f, 1.101f);
        assertEquals(a, b);
    }
}