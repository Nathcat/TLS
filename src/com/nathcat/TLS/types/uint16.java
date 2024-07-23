package com.nathcat.TLS.types;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class uint16 implements Number {
    private long data;

    /**
     * Default the value of this number to 0.
     */
    public uint16() {
        data = 0;
    }

    /**
     * Set the value of this number to the value of the given byte
     * @param v The byte given
     */
    public uint16(long v) {
        data = 0;
        this.set(v);
    }

    /**
     * Read the value of this number from an input stream
     * @param is The input stream to read from
     * @throws IOException Thrown if an I/O operation fails
     */
    public uint16(InputStream is) throws IOException {
        byte[] b = new byte[2];
        is.read(b);
        System.out.println(STR."\{Integer.toHexString(b[0])} \{Integer.toHexString(b[1])}");
        System.out.println(Integer.toHexString((int) data));
        data = (b[0] << 8) | b[1];
        System.out.println(Integer.toHexString((int) data));
    }

    @Override
    public long value() {
        return data;
    }

    @Override
    public void set(long v) throws IllegalArgumentException {
        if (v >= 0xFFFF) {
            throw new IllegalArgumentException("The input must be no larger than 2^16.");
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
        return 2;
    }
}
