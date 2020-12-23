package com.qod.lostboxes.math.vec;

import com.qod.lostboxes.math.mat.IMatrix;

import java.math.BigDecimal;

public interface IVector<T extends Number> {
    IVector<T> transform(IMatrix<T> matrix);

    IVector<T> add(IVector<T> operand);
    IVector<T> sub(IVector<T> operand);

    T dot(IVector<T> operand);

    IVector<T> scale(double scalar);
    IVector<T> negate();
    IVector<T> unit();
    BigDecimal norm();

    int size();
}
