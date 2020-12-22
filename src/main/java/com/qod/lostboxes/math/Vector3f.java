package com.qod.lostboxes.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class Vector3f implements IVector<Float> {

    public float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public IVector<Float> transform(IMatrix<Float> matrix) {
        return new Vector3f(
                MathUtils.roundFloat(x * matrix.get(0, 0) + y * matrix.get(0, 1) * z * matrix.get(0, 2), 10),
                MathUtils.roundFloat(x * matrix.get(1, 0) + y * matrix.get(1, 1) * z * matrix.get(1, 2), 10),
                MathUtils.roundFloat(x * matrix.get(2, 0) + y * matrix.get(2, 1) * z * matrix.get(2, 2), 10)
        );
    }

    @Override
    public IVector<Float> add(IVector<Float> operand) {
        if(!(operand instanceof Vector3f)) throw new UnsupportedOperandException();
        return new Vector3f(x + ((Vector3f) operand).getX(),
                y + ((Vector3f) operand).getY(),
                z + ((Vector3f) operand).getZ());
    }

    @Override
    public IVector<Float> sub(IVector<Float> operand) {
        if(!(operand instanceof Vector3f)) throw new UnsupportedOperandException();
        return new Vector3f(x - ((Vector3f) operand).getX(),
                y - ((Vector3f) operand).getY(),
                z - ((Vector3f) operand).getZ());
    }

    @Override
    public IVector<Float> mul(IVector<Float> operand) {
        if(!(operand instanceof Vector3f)) throw new UnsupportedOperandException();
        return new Vector3f(
                y*((Vector3f) operand).getZ() - z*((Vector3f) operand).getY(),
                z*((Vector3f) operand).getX() - x*((Vector3f) operand).getZ(),
                x*((Vector3f) operand).getY() - y*((Vector3f) operand).getX()
        );
    }

    @Override
    public Float dot(IVector<Float> operand) {
        if(!(operand instanceof Vector3f)) throw new UnsupportedOperandException();
        return x*((Vector3f) operand).getX() + y*((Vector3f) operand).getY() + z*((Vector3f) operand).getZ();

    }

    @Override
    public IVector<Float> scale(double scalar) {
        return new Vector3f(x*(float) scalar, y*(float) scalar, z*(float) scalar);
    }

    @Override
    public IVector<Float> negate() {
        return new Vector3f(-x, -y, -z);
    }

    @Override
    public IVector<Float> unit() {
        float nx = new BigDecimal(x).divide(norm(), MathContext.DECIMAL128).floatValue();
        float ny = new BigDecimal(y).divide(norm(), MathContext.DECIMAL128).floatValue();
        float nz = new BigDecimal(z).divide(norm(), MathContext.DECIMAL128).floatValue();
        return new Vector3f(nx, ny, nz);
    }

    @Override
    public BigDecimal norm() {
        return new BigDecimal(x).pow(2)
                .add(new BigDecimal(y).pow(2))
                .add(new BigDecimal(z).pow(2))
                .sqrt(MathContext.DECIMAL128);
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public String toString() {
        return "Vector3f{x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.x, x) == 0 && Float.compare(vector3f.y, y) == 0 && Float.compare(vector3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
