package com.nathcat.TLS;

import com.nathcat.TLS.Messages.TLSPlainText;

import java.io.IOException;

/**
 * Uses the content type field of a TLS Plain Text record
 * to pass the message off to a handler which will
 * appropriately handle the message.
 */
public interface TLSPlainTextHandler {
    void invalid(TLSPlainText r) throws IOException;
    void change_cipher_spec(TLSPlainText r) throws IOException;
    void alert(TLSPlainText r) throws IOException;
    void handshake(TLSPlainText r) throws IOException;
    void application_data(TLSPlainText r) throws IOException;

    default void handle(TLSPlainText r) throws IOException {
        switch ((int) r.type.value()) {
            case 0: invalid(r); break;
            case 20: change_cipher_spec(r); break;
            case 21: alert(r); break;
            case 22: handshake(r); break;
            case 23: application_data(r); break;
            default: throw new IllegalArgumentException("Invalid content type!");
        }
    }
}
