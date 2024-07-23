package com.nathcat.TLS.types;

import java.io.IOException;
import java.io.InputStream;

public class Extension {
    public uint16 extension_type;
    public Vector<Byte> extension_data = new Vector<>(new uint16(0), new uint16(0xFFFF - 1));

    public Extension(InputStream is) throws IOException {
        extension_type = new uint16(is);
        uint16 data_length = new uint16(is);
        for (long i = 0; i < data_length.value(); i++) {
            byte[] b = new byte[1];
            is.read(b);
            extension_data.add(b[0]);
        }
    }
}
