package com.example.daneko.logansquareparcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 */
public abstract class LoganSquareParcelable implements Parcelable {

    public static <T> Creator<T> creatorFactory(final Class<T> clazz) {
        return new Creator<T>() {
            @Override
            public T createFromParcel(Parcel source) {
                try {
                    return LoganSquare.parse(source.readString(), clazz);
                } catch (IOException e) {
                    throw new RuntimeException("Do you forgot implement for logan square? class : " + clazz, e);
                }
            }

            @SuppressWarnings("unchecked")
            @Override
            public T[] newArray(int size) {
                return (T[]) Array.newInstance(clazz, size);
            }
        };
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        try {
            dest.writeString(LoganSquare.serialize(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
