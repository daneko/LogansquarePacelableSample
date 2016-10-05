package com.example.daneko.logansquareparcelable;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public final class Simple extends LoganSquareParcelable {

    int a;
    String b;

    Simple() {

    }

    public Simple(int a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simple)) return false;

        Simple simple = (Simple) o;

        if (a != simple.a) return false;
        return b != null ? b.equals(simple.b) : simple.b == null;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    public static final Creator<Simple> CREATOR = creatorFactory(Simple.class);
}
