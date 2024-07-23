package com.nathcat.TLS.types;

import java.io.IOException;
import java.io.InputStream;


public class uint8 implements Number {
    private long data;

    /**
     * Default the value of this number to 0.
     */
    public uint8() {
        data =  0;
    }

    /**
     * Set the value of this number to the value of the given byte
     * @param v The byte given
     */
    public uint8(long v) {
        data = 0;
        this.set(v);
    }

    /**
     * Read the value of this number from an input stream
     * @param is The input stream to read from
     * @throws IOException Thrown if an I/O operation fails
     */
    public uint8(InputStream is) throws IOException {
        byte[] b = new byte[1];
        is.read(b);
        data = b[0];
    }

    @Override
    public long value() {
        return data;
    }

    @Override
    public void set(long v) throws IllegalArgumentException {
        if (v >= 256) {
            throw new IllegalArgumentException("The input must be no larger than 255.");
        }

        data = (byte) v;
    }

    @Override
    public int compare(Number other) {
        long b = other.value();
        for (int i = 64; i >= 0; i--) {
            long da = (data & (1L << i)) >> i;
            long db = (b & (1L << i)) >> i;

            if (da != db) {
                if (da > db) return 1;
                else return -1;
            }
        }

        return 0;
    }

    @Override
    public int size() {
        return 1;
    }
}
