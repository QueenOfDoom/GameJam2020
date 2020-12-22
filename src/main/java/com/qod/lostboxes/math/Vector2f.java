package com.qod.lostboxes.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class Vector2f implements IVector<Float> {

    float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public IVector<Float> transform(IMatrix<Float> matrix) {
        // TODO: Adapt Vector to Matrix Size (fill with 0's)
        if(!(matrix instanceof Matrix2f)) throw new UnsupportedOperandException();
        return new Vector2f(
                matrix.get(0, 0) * x + matrix.get(0, 1) * y,
                matrix.get(1, 0) * x + matrix.get(1, 1) * y
        );
    }

    @Override
    public IVector<Float> add(IVector<Float> operand) {
        if(!(operand instanceof Vector2f)) throw new UnsupportedOperandException();
        return new Vector2f(
                x + ((Vector2f) operand).getX(),
                y + ((Vector2f) operand).getY());
    }

    @Override
    public IVector<Float> sub(IVector<Float> operand) {
        if(!(operand instanceof Vector2f)) throw new UnsupportedOperandException();
        return new Vector2f(
                x - ((Vector2f) operand).getX(),
                y - ((Vector2f) operand).getY());
    }

    @Override
    public IVector<Float> mul(IVector<Float> operand) {
        throw new UnsupportedOperationException("Vector Multiplication cannot perform with Vector2f.");
    }

    @Override
    public Float dot(IVector<Float> operand) {
        if(!(operand instanceof Vector2f)) throw new UnsupportedOperandException();
        return this.x * ((Vector2f) operand).getX() + this.y * ((Vector2f) operand).getY();
    }

    @Override
    public IVector<Float> scale(double scalar) {
        return new Vector2f((float) scalar * x, (float) scalar * y);
    }

    @Override
    public IVector<Float> negate() {
        return new Vector2f(-x, -y);
    }

    @Override
    public IVector<Float> unit() {
        float nx = new BigDecimal(x).divide(norm(), MathContext.DECIMAL128).floatValue();
        float ny = new BigDecimal(y).divide(norm(), MathContext.DECIMAL128).floatValue();
        return new Vector2f(nx, ny);
    }

    @Override
    public BigDecimal norm() {
        BigDecimal sq = new BigDecimal(x).pow(2).add(new BigDecimal(y).pow(2));
        return sq.sqrt(MathContext.DECIMAL128);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public String toString() {
        return "Vector2f{x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.x, x) == 0 && Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
