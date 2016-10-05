package com.example.daneko.logansquareparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 */
public final class NormalSimple implements Parcelable {

    int a;
    String b;

    public NormalSimple(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    @Override
    public String toString() {
        return "NormalSimple{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalSimple)) return false;

        NormalSimple that = (NormalSimple) o;

        if (a != that.a) return false;
        return b != null ? b.equals(that.b) : that.b == null;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    protected NormalSimple(Parcel in) {
        a = in.readInt();
        b = in.readString();
    }

    public static final Creator<NormalSimple> CREATOR = new Creator<NormalSimple>() {
        @Override
        public NormalSimple createFromParcel(Parcel in) {
            return new NormalSimple(in);
        }

        @Override
        public NormalSimple[] newArray(int size) {
            return new NormalSimple[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(a);
        dest.writeString(b);
    }
}
