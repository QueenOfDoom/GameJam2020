package com.qod.lostboxes.math.vec;

import com.qod.lostboxes.math.MathUtils;
import com.qod.lostboxes.math.mat.IMatrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class Vector4f implements IVector<Float> {
    float i, j, k, l;
    public Vector4f(float i, float j, float k, float l) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
    }

    public Vector4f(float i, float j, float k) {
        this(i, j, k, 1);
    }

    public Vector4f(Vector3f vec) {
        this(vec.getX(), vec.getY(), vec.getZ(), 1);
    }

    public float getI() {
        return i;
    }

    public float getJ() {
        return j;
    }

    public float getK() {
        return k;
    }

    public float getL() {
        return l;
    }

    @Override
    public IVector<Float> transform(IMatrix<Float> matrix) {
        return new Vector4f(
                MathUtils.roundFloat(
                        i * matrix.get(0, 0) +
                                j * matrix.get(1, 0) +
                                k * matrix.get(2, 0) +
                                l * matrix.get(3, 0), 10),
                MathUtils.roundFloat(
                        i * matrix.get(0, 1) +
                                j * matrix.get(1, 1) +
                                k * matrix.get(2, 1) +
                                l * matrix.get(3, 1), 10),
                MathUtils.roundFloat(
                        i * matrix.get(0, 2) +
                                j * matrix.get(1, 2) +
                                k * matrix.get(2, 2) +
                                l * matrix.get(3, 2), 10),
                MathUtils.roundFloat(
                        i * matrix.get(0, 3) +
                                j * matrix.get(1, 3) +
                                k * matrix.get(2, 3) +
                                l * matrix.get(3, 3), 10)
        );
    }

    @Override
    public IVector<Float> add(IVector<Float> operand) {
        return new Vector4f(
                getI() + ((Vector4f) operand).getI(),
                getJ() + ((Vector4f) operand).getJ(),
                getK() + ((Vector4f) operand).getK(),
                getL() + ((Vector4f) operand).getL()
        );
    }

    @Override
    public IVector<Float> sub(IVector<Float> operand) {
        return new Vector4f(
                getI() - ((Vector4f) operand).getI(),
                getJ() - ((Vector4f) operand).getJ(),
                getK() - ((Vector4f) operand).getK(),
                getL() - ((Vector4f) operand).getL()
        );
    }

    @Override
    public Float dot(IVector<Float> operand) {
        return
                getI() * ((Vector4f) operand).getI() +
                getJ() * ((Vector4f) operand).getJ() +
                getK() * ((Vector4f) operand).getK() +
                getL() * ((Vector4f) operand).getL();
    }

    @Override
    public IVector<Float> scale(double scalar) {
        return new Vector4f(
                getI() * (float) scalar,
                getJ() * (float) scalar,
                getK() * (float) scalar,
                getL() * (float) scalar
        );
    }

    @Override
    public IVector<Float> negate() {
        return new Vector4f(-i, -j, -k, -l);
    }

    @Override
    public IVector<Float> unit() {
        float ni = new BigDecimal(i).divide(norm(), MathContext.DECIMAL128).floatValue();
        float nj = new BigDecimal(j).divide(norm(), MathContext.DECIMAL128).floatValue();
        float nk = new BigDecimal(k).divide(norm(), MathContext.DECIMAL128).floatValue();
        float nl = new BigDecimal(l).divide(norm(), MathContext.DECIMAL128).floatValue();
        return new Vector4f(ni, nj, nk, nl);
    }

    @Override
    public BigDecimal norm() {
        return new BigDecimal(i).pow(2)
                .add(new BigDecimal(j).pow(2))
                .add(new BigDecimal(k).pow(2))
                .add(new BigDecimal(l).pow(2))
                .sqrt(MathContext.DECIMAL128);
    }

    @Override
    public int size() {
        return 4;
    }

    public Vector3f toVector3() {
        return new Vector3f(i, j, k);
    }

    public Vector2f toVector2() { return new Vector2f(i, j); }

    @Override
    public String toString() {
        return "Vector4f{i=" + i + ", j=" + j + ", k=" + k + ", l=" + l + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector4f vector4f = (Vector4f) o;
        return Float.compare(vector4f.i, i) == 0 && Float.compare(vector4f.j, j) == 0 && Float.compare(vector4f.k, k) == 0 && Float.compare(vector4f.l, l) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, k, l);
    }
}
