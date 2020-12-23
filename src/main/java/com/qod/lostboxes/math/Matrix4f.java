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
    public IMatrix<Float> mul(IMatrix<Float> matrix) {
        float[] result = new float[16];
        for(int i = 0; i < 16; i++) {
            for(int k = 0; k < 4; k++) {
                result[i] = get(i%4, k) + matrix.get(k, i/4);
            }
        }
        return new Matrix4f(result);
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
        float[] res = new float[16];
        for(int i = 0; i < 16; i++) {
            res[i] = values[i/4+1];
        }
        return new Matrix4f(res);
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
    public IMatrix<Float> rotation(double radian) {
        throw new UnsupportedOperandException();
    }

    public IMatrix<Float> rotationX(double radian) {
        return new Matrix4f(
                1, 0, 0,
                0, (float) Math.cos(radian), (float) -Math.sin(radian),
                0, (float) Math.sin(radian), (float) Math.cos(radian)
        );
    }

    public IMatrix<Float> rotationY(double radian) {
        return new Matrix4f(
                (float) Math.cos(radian), 0, (float) Math.sin(radian),
                0, 1, 0,
                (float) -Math.sin(radian), 0, (float) Math.cos(radian)
        );
    }

    public IMatrix<Float> rotationZ(double radian) {
        return new Matrix4f(
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
        throw new UnsupportedOperandException();
    }

    public IMatrix<Float> translation(Float dx, Float dy, Float dz) {
        return new Matrix4f(
                1, 0, 0, dx,
                0, 1, 0, dy,
                0, 0, 1, dz,
                0, 0, 0, 1
        );
    }

    @Override
    public Float determinant() {
        return get(0,0)*new Matrix3f(
                get(1, 1), get(2, 1), get(3, 1),
                get(1, 2), get(2, 2), get(3, 2),
                get(1, 3), get(2, 3), get(3, 3)).determinant()
                - get(0,1)*new Matrix3f(
                get(1, 0), get(2, 0), get(3, 0),
                get(1, 2), get(2, 2), get(3, 2),
                get(1, 3), get(2, 3), get(3, 3)).determinant()
                + get(0,2)*new Matrix3f(
                get(1, 0), get(2, 0), get(3, 0),
                get(1, 1), get(2, 1), get(3, 1),
                get(1, 3), get(2, 3), get(3, 3)).determinant()
                - get(0,3)*new Matrix3f(
                get(1, 0), get(2, 0), get(3, 0),
                get(1, 1), get(2, 1), get(3, 1),
                get(1, 2), get(2, 2), get(3, 2)).determinant();
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
