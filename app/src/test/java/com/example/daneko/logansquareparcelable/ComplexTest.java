package com.example.daneko.logansquareparcelable;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import org.assertj.core.api.Assertions;

import java.util.Arrays;


/**
 */
@RunWith(RobolectricTestRunner.class)
public class ComplexTest {
    @Test
    public void complex_case1() throws Exception {

        final Complex complex =
                new Complex(1, "hoge", Arrays.asList(new Simple(10, "a"), new Simple(20, "b")));

        final Parcel parcel = Parcel.obtain();
        complex.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        final Complex fromParcel = Complex.CREATOR.createFromParcel(parcel);

        Assertions.assertThat(fromParcel).isEqualTo(complex);
    }

    @Test
    public void complex_case2() throws Exception {

        final Complex complex =
                new Complex(1, null, Arrays.asList(new Simple(10, "a"), new Simple(20, null)));

        final Parcel parcel = Parcel.obtain();
        complex.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        final Complex fromParcel = Complex.CREATOR.createFromParcel(parcel);

        Assertions.assertThat(fromParcel).isEqualTo(complex);
    }
}