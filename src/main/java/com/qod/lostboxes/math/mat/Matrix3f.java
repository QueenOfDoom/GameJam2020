package com.qod.lostboxes.math.mat;

import com.qod.lostboxes.math.UnsupportedOperandException;

import java.util.Arrays;

public class Matrix3f implements IMatrix<Float> {

    float[] values;

    public Matrix3f(float... values) {
        this.values = values;
    }

    public Matrix3f() { }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    public IMatrix<Float> add(IMatrix<Float> matrix) {
        return new Matrix3f(
                values[0] + matrix.get(0,0),
                values[1] + matrix.get(1, 0),
                values[2] + matrix.get(2, 0),
                values[3] + matrix.get(0,1),
                values[4] + matrix.get(1,1),
                values[5] + matrix.get(2,1),
                values[6] + matrix.get(0,2),
                values[7] + matrix.get(1,2),
                values[8] + matrix.get(2,2)
        );
    }

    @Override
    public IMatrix<Float> mul(IMatrix<Float> matrix) {
        float[] result = new float[9];
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 3; k++) {
                result[i] += get(k, i/3) * matrix.get( i%3, k);
            }
        }
        return new Matrix3f(result);
    }

    @Override
    public IMatrix<Float> scale(double scalar) {
        float[] result = new float[3*3];
        for(int i = 0; i < result.length; i++)
            result[i] = (float) scalar * values[i];
        return new Matrix3f(result);
    }

    @Override
    public IMatrix<Float> transpose() {
        return new Matrix3f(
                values[0],
                values[3],
                values[6],
                values[1],
                values[4],
                values[7],
                values[2],
                values[5],
                values[8]
        );
    }

    @Override
    public IMatrix<Float> identity() {
        return new Matrix3f(
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        );
    }

    @Override
    public IMatrix<Float> rotation(double radian) {
        throw new UnsupportedOperandException();
    }

    public IMatrix<Float> rotationX(double radian) {
        return new Matrix3f(
                1, 0, 0,
                0, (float) Math.cos(radian), (float) -Math.sin(radian),
                0, (float) Math.sin(radian), (float) Math.cos(radian)
        );
    }

    public IMatrix<Float> rotationY(double radian) {
        return new Matrix3f(
                (float) Math.cos(radian), 0, (float) Math.sin(radian),
                0, 1, 0,
                (float) -Math.sin(radian), 0, (float) Math.cos(radian)
        );
    }

    public IMatrix<Float> rotationZ(double radian) {
        return new Matrix3f(
                (float) Math.cos(radian), (float) -Math.sin(radian), 0,
                (float) Math.sin(radian), (float) Math.cos(radian), 0,
                0, 0, 1
        );
    }

    public IMatrix<Float> rotation(double radX, double radY, double radZ) {
        return rotationX(radX).mul(rotationY(radY)).mul(rotationZ(radZ));
    }

    @Override
    public IMatrix<Float> translation(Float dx, Float dy) {
        return new Matrix3f(
                1, 0, dx,
                0, 1, dy,
                0, 0, 1
        );
    }

    @Override
    public Float determinant() {
        return
                values[0]*(values[4]*values[8] - values[5]*values[7])
                + values[1]*(values[5]*values[6]-values[3]*values[8])
                + values[2]*(values[3]*values[7] - values[4]*values[6]);
    }

    @Override
    public boolean isSquare() {
        return true;
    }

    @Override
    public Float get(int x, int y) {
        return values[3*y+x];
    }

    @Override
    public Float[] getRow(int y) {
        return new Float[]{values[3*y], values[3*y+1], values[3*y+2]};
    }

    @Override
    public Float[] getColumn(int x) {
        return new Float[]{values[x], values[x+3], values[x+2*3]};
    }

    @Override
    public String toString() {
        return "Matrix3f{values=" + Arrays.toString(values) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix3f matrix3f = (Matrix3f) o;
        return Arrays.equals(values, matrix3f.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}
