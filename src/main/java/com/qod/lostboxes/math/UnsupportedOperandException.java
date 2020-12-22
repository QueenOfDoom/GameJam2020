package com.qod.lostboxes.math;

public class UnsupportedOperandException extends RuntimeException {
    public UnsupportedOperandException() {
        super("This Operand is not supported for the given Operation!");
    }
}
