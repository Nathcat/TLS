package com.nathcat.TLS.types;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Vector <T> {
    private Number floor;
    private Number ceiling;
    private ArrayList<T> data;

    public Vector(Number f, Number c) {
        floor = f;
        ceiling = c;
    }

    public void add(T d) {
        data.add(d);
    }

    public T get(int i) {
        return data.get(i);
    }
}
