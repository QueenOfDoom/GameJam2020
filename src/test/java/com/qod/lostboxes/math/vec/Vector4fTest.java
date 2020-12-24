package com.qod.lostboxes.math.vec;

import com.qod.lostboxes.math.mat.Matrix4f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector4fTest {

    @Test
    void transform() {
        Vector4f vec = new Vector4f(5, 4, 3, 1);

        Matrix4f mat = new Matrix4f(
                1, 0, 0, 2,
                0, 1, 0, 2,
                0, 0, 1, 3,
                0, 0, 0, 1
        );
        assertEquals(mat, new Matrix4f().translation(2f, 2f, 3f));

        assertEquals(new Vector4f(7, 6, 6, 1), vec.transform(mat));

        Vector4f origin = new Vector4f(0, 0, 0, 1);
        assertEquals(new Vector4f(2, 2, 3, 1), origin.transform(mat));
    }
}