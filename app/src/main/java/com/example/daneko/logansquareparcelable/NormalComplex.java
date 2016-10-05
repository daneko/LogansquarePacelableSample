package com.example.daneko.logansquareparcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 */
public final class NormalComplex implements Parcelable {

    int a;
    String b;
    List<NormalSimple> c;

    public NormalComplex(int a, String b, List<NormalSimple> c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "NormalComplex{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalComplex)) return false;

        NormalComplex that = (NormalComplex) o;

        if (a != that.a) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        return c != null ? c.equals(that.c) : that.c == null;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }

    protected NormalComplex(Parcel in) {
        a = in.readInt();
        b = in.readString();
        c = in.createTypedArrayList(NormalSimple.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(a);
        dest.writeString(b);
        dest.writeTypedList(c);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NormalComplex> CREATOR = new Creator<NormalComplex>() {
        @Override
        public NormalComplex createFromParcel(Parcel in) {
            return new NormalComplex(in);
        }

        @Override
        public NormalComplex[] newArray(int size) {
            return new NormalComplex[size];
        }
    };
}
