package com.qod.lostboxes.math;

public class Matrix2f implements IMatrix<Float> {

    float[] values;

    public Matrix2f(float... values) {
        if(values.length != 4) throw new UnsupportedOperandException();
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
                values[3],
                values[2],
                values[1]
        );
    }

    @Override
    public IMatrix<Float> inverse() {
        return new Matrix2f(values[3], -values[1], -values[2], values[0]).scale(1.0/this.determinant());
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
}
