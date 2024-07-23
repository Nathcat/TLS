package com.nathcat.TLS.Messages;

import com.nathcat.TLS.types.uint24;
import com.nathcat.TLS.types.uint8;

public class HandshakeMessage {
    public enum Type {
        client_hello(new uint8(1)),
        server_hello(new uint8(2)),
        new_session_ticket(new uint8(4)),
        end_of_early_data(new uint8(5)),
        encrypted_extensions(new uint8(8)),
        certificate(new uint8(11)),
        certificate_request(new uint8(13)),
        certificate_verify(new uint8(15)),
        finished(new uint8(20)),
        key_update(new uint8(24)),
        message_hash(new uint8(254));


        public final uint8 value;
        Type(uint8 v) {
            value = v;
        }
    }

    public uint8 messageType;
    public uint24 length;
}
