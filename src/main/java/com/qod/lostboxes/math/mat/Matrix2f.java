package com.qod.lostboxes.math.mat;

import com.qod.lostboxes.math.UnsupportedOperandException;

import java.util.Arrays;

public class Matrix2f implements IMatrix<Float> {

    float[] values;

    public Matrix2f(float... values) {
        if(values.length != 4 && values.length != 0) throw new UnsupportedOperandException();
        this.values = values;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public IMatrix<Float> add(IMatrix<Float> matrix) {
        return new Matrix2f(
                values[0] + matrix.get(0,0),
                values[1] + matrix.get(1, 0),
                values[2] + matrix.get(0, 1),
                values[3] + matrix.get(1, 1)
        );
    }

    @Override
    public IMatrix<Float> mul(IMatrix<Float> matrix) {
        float[] result = new float[4];
        for(int i = 0; i < 4; i++) {
            for(int k = 0; k < 2; k++) {
                result[i] += get(k, i/2) * matrix.get( i%2, k);
            }
        }
        return new Matrix2f(result);
    }

    @Override
    public IMatrix<Float> scale(double scalar) {
        return new Matrix2f(
                values[0] * (float) scalar,
                values[1] * (float) scalar,
                values[2] * (float) scalar,
                values[3] * (float) scalar
        );
    }

    @Override
    public IMatrix<Float> transpose() {
        return new Matrix2f(
                values[0],
                values[2],
                values[1],
                values[3]
        );
    }

    @Override
    public IMatrix<Float> identity() {
        return new Matrix2f(1, 0, 0, 1);
    }

    @Override
    public IMatrix<Float> rotation(double radian) {
        return new Matrix2f(
                (float) Math.cos(radian),
                (float) - Math.sin(radian),
                (float) Math.sin(radian),
                (float) Math.cos(radian)
        );
    }

    @Override
    public IMatrix<Float> translation(Float dx, Float dy) {
        throw new UnsupportedOperandException();
    }

    @Override
    public Float determinant() {
        return values[0]*values[3] - values[1]*values[2];
    }

    @Override
    public boolean isSquare() {
        return true;
    }

    @Override
    public Float get(int x, int y) {
        return values[2*y+x];
    }

    @Override
    public Float[] getRow(int y) {
        return new Float[]{values[2*y], values[2*y+1]};
    }

    @Override
    public Float[] getColumn(int x) {
        return new Float[]{values[x], values[x+2]};
    }

    @Override
    public String toString() {
        return "Matrix2f{values=" + Arrays.toString(values) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix2f matrix2f = (Matrix2f) o;
        return Arrays.equals(values, matrix2f.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}
