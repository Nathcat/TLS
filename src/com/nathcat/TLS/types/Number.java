package com.nathcat.TLS.types;

public interface Number {
    long value();
    void set(long v);

    /**
     * Compare two Numbers, if other > this return -1, if other == this return 0, if other < this return 1.
     * @param other The other number to compare with.
     * @return The comparison result
     */
    int compare(Number other);

    /**
     * Return the size of this number in bytes.
     *
     * @return
     */
    int size();
}
