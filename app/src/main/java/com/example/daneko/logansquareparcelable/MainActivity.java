package com.example.daneko.logansquareparcelable;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.ArrayList;

import fj.F;
import fj.F2;
import fj.P2;
import fj.data.Enumerator;
import fj.data.List;
import fj.data.Stream;
import fj.function.Effect0;
import fj.function.Effect1;

public class MainActivity extends AppCompatActivity {

    ArrayList<Complex> cl;
    ArrayList<NormalComplex> nl;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            cl = new ArrayList<>();
            nl = new ArrayList<>();
            int mockSize = 400;
            final List<P2<String, Integer>> p2s = generateMockString(mockSize).zipIndex();
            final List<Simple> simples = p2s.map(new F<P2<String, Integer>, Simple>() {
                @Override
                public Simple f(P2<String, Integer> p) {
                    return new Simple(p._2(), p._1());
                }
            });
            final List<NormalSimple> normalSimples = p2s.map(new F<P2<String, Integer>, NormalSimple>() {
                @Override
                public NormalSimple f(P2<String, Integer> p) {
                    return new NormalSimple(p._2(), p._1());
                }
            });
            p2s.foreachDoEffect(new Effect1<P2<String, Integer>>() {
                @Override
                public void f(P2<String, Integer> p) {
                    cl.add(new Complex(p._2(), p._1(), simples.take(p._2()).toJavaList()));
                    nl.add(new NormalComplex(p._2(), p._1(), normalSimples.take(p._2()).toJavaList()));
                }
            });

            Log.d("test", "make mock data size:" + mockSize);
            try {
                final String s = LoganSquare.serialize(cl, Complex.class);
                Log.d("test", "json string size :" + s.length());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

//            withTimeCounter("restore cl", () -> {
//                cl = savedInstanceState.getParcelableArrayList("cl");
//            });
            withTimeCounter("restore cl", new Effect0() {
                @Override
                public void f() {
                    cl = savedInstanceState.getParcelableArrayList("cl");
                }
            });
            withTimeCounter("restore nl", new Effect0() {
                @Override
                public void f() {

                    nl = savedInstanceState.getParcelableArrayList("nl");
                }
            });
        }

//        Log.d("test", "data cl:" + cl);
//        Log.d("test", "data nl:" + nl);
    }

    private void withTimeCounter(String name, Effect0 f) {
        final long s = System.currentTimeMillis();
        final long ss = System.nanoTime();
        f.f();
        final long e = System.currentTimeMillis();
        final long ee = System.nanoTime();
        final String msg = String.format("name(ms): %s / time : %d", name, e - s);
        final String msg2 = String.format("name(ns): %s / time : %d", name, ee - ss);
        Log.d("test", msg);
        Log.d("test", msg2);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {

        withTimeCounter("save cl", new Effect0() {
            @Override
            public void f() {
                outState.putParcelableArrayList("cl", cl);
            }
        });
        withTimeCounter("save nl", new Effect0() {
            @Override
            public void f() {
                outState.putParcelableArrayList("nl", nl);
            }
        });

        super.onSaveInstanceState(outState);
    }

    static List<String> generateMockString(int size) {

        final List<String> data = List.list(
                "hoge",
                "fuga",
                "piyo");

        final F2<String, Integer, String> f = new F2<String, Integer, String>() {
            @Override
            public String f(final String s, Integer i) {
                return Stream.forever(Enumerator.intEnumerator, 0).take(i).
                        foldRight1(new F2<Integer, String, String>() {
                            @Override
                            public String f(Integer integer, String ss) {
                                return ss + s;
                            }
                        }, "");
            }
        };

        final int dataSize = data.length();

        return Stream.forever(Enumerator.intEnumerator, 0)
                .take(size)
                .map(new F<Integer, String>() {
                    @Override
                    public String f(Integer i) {
                        return f.f(data.index(i % dataSize), i);
                    }
                })
                .toList();
    }
}
