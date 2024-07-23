package com.nathcat.TLS.Messages;

import com.nathcat.TLS.types.uint16;
import com.nathcat.TLS.types.uint8;

public class TLSPlainText {
    public enum ContentType {
        invalid(new uint8(0)),
        change_cipher_spec(new uint8(20)),
        alert(new uint8(21)),
        handshake(new uint8(22)),
        application_data(new uint8(23));

        public final uint8 value;
        ContentType(uint8 v) {
            this.value = v;
        }
    }

    public uint8 type;
    public uint16 legacy_record_version;
    public uint16 length;
    public byte[] fragment;
}
