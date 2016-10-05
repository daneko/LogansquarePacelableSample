package com.example.daneko.logansquareparcelable;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public final class Complex extends LoganSquareParcelable {

    int a;
    String b;
    List<Simple> c;

    Complex() {

    }

    public Complex(int a, String b, List<Simple> c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complex)) return false;

        Complex complex = (Complex) o;

        if (a != complex.a) return false;
        if (b != null ? !b.equals(complex.b) : complex.b != null) return false;
        return c != null ? c.equals(complex.c) : complex.c == null;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }

    public static final Creator<Complex> CREATOR = creatorFactory(Complex.class);
}
