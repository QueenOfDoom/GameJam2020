package com.qod.lostboxes.math;

public interface IMatrix<T extends Number> {
    int getWidth();
    int getHeight();

    IMatrix<T> add(IMatrix<T> matrix);
    IMatrix<T> mul(IMatrix<T> matrix);

    IMatrix<T> scale(double scalar);
    IMatrix<T> transpose();
    IMatrix<T> identity();
    IMatrix<T> rotation(double radian);
    IMatrix<T> translation(T dx, T dy);
    T determinant();

    boolean isSquare();
    T get(int x, int y);
    T[] getRow(int y);
    T[] getColumn(int x);
}
