package com.qod.lostboxes.math;

public class Matrix4f implements IMatrix<Float>{
    float[] values;
    public Matrix4f(float... values) {
        this.values = values;
    }


    @Override
    public int getWidth() {
        return 4;
    }

    @Override
    public int getHeight() {
        return 4;
    }

    @Override
    public IMatrix<Float> add(IMatrix<Float> matrix) {
        float[] sum = new float[4*4];
        for(int i = 0; i < 16; i++) {
            sum[i] = values[i] + matrix.get(i%4, i/4);
        }
        return new Matrix4f(sum);
    }

    @Override
    public IMatrix<Float> scale(double scalar) {
        float[] result = new float[4*4];
        for(int i = 0; i < result.length; i++)
            result[i] = (float) scalar * values[i];
        return new Matrix4f(result);
    }

    @Override
    public IMatrix<Float> transpose() {
        return null; //TODO: Some Nerve Wrecking
    }

    @Override
    public IMatrix<Float> identity() {
        return new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    @Override
    public IMatrix<Float> inverse() {
        return null; //TODO: Some more Nerve Wrecking
    }

    @Override
    public IMatrix<Float> rotation(double radian) {
        return null;  //TODO: Fairly Easy
    }

    @Override
    public IMatrix<Float> translation(Float dx, Float dy) {
        return null; //TODO: Even easier
    }

    @Override
    public Float determinant() {
        return null; //TODO: No please, God no!
    }

    @Override
    public boolean isSquare() {
        return true;
    }

    @Override
    public Float get(int x, int y) {
        return values[4*y+x];
    }

    @Override
    public Float[] getRow(int y) {
        return new Float[]{values[4*y], values[4*y+1], values[4*y+2], values[4*y+3]};
    }

    @Override
    public Float[] getColumn(int x) {
        return new Float[]{values[x], values[x+4], values[x+2*4], values[x+3*4]};
    }
}
