package com.nathcat.TLS;

import com.nathcat.TLS.Messages.ClientHello;
import com.nathcat.TLS.Messages.TLSPlainText;
import com.nathcat.TLS.types.Extension;
import com.nathcat.TLS.types.uint16;
import com.nathcat.TLS.types.uint24;
import com.nathcat.TLS.types.uint8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TLSInputStream extends InputStream {

    private InputStream is;
    public TLSPlainTextHandler plainTextHandler;

    public TLSInputStream(InputStream is) {
        this.is = is;
    }
    public TLSInputStream(InputStream is, TLSPlainTextHandler plainTextHandler) {
        this.is = is;
        this.plainTextHandler = plainTextHandler;
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return is.read(b);
    }

    /**
     * Handles an incoming plain text message when one is expected.
     * @throws IOException Thrown in case of I/O errors
     */
    public void handlePlainText() throws IOException {
        plainTextHandler.handle(readPlainText());
    }

    /**
     * Read a plain text message from the stream
     * @return The plain text message which was read.
     * @throws IOException Thrown in case of I/O errors
     */
    public TLSPlainText readPlainText() throws IOException {
        TLSPlainText r = new TLSPlainText();
        r.type = new uint8(this);
        r.legacy_record_version = new uint16(this);
        r.length = new uint16(this);
        r.fragment = new byte[(int) r.length.value()];

        if (this.read(r.fragment) != (int) r.length.value()) {
            throw new IllegalArgumentException("An error occurred while trying to read the record's contents.");
        }

        return r;
    }

    /**
     * Read a ClientHello message from the stream.
     * @return The received client hello message.
     */
    public ClientHello readClientHello() throws IOException {
        ClientHello msg = new ClientHello();

        // Read the legacy version
        uint16 legacy_version = new uint16(this);
        if (legacy_version.value() != 0x0303L) {
            throw new IllegalArgumentException(STR."Legacy version field is incorrect, should be set to 0x0303, instead got \{legacy_version.value()}");
        }

        // Read the 32 byte random
        if (this.read(msg.random) != 32) {
            throw new IllegalArgumentException("Failed to read the 32 byte random.");
        }

        // Read the legacy session id
        uint8 ls_id_length = new uint8(this);
        for (long i = 0; i < ls_id_length.value(); i++) {
            byte[] b = new byte[1];
            if (this.read(b) != 1) {
                throw new IllegalArgumentException("Failed to read legacy session id vector.");
            }

            msg.legacy_session_id.add(b[0]);
        }

        // Read the cipher suites
        uint16 cs_length = new uint16(this);
        for (long i = 0; i < cs_length.value(); i++) {
            uint8[] cipher_suite = new uint8[] {
                    new uint8(this),
                    new uint8(this)
            };

            msg.cipher_suites.add(cipher_suite);
        }

        // Read the legacy compression methods
        uint8 lcm_length = new uint8(this);
        for (long i = 0; i < lcm_length.value(); i++) {
            byte[] b = new byte[1];
            this.read(b);
            msg.legacy_compression_methods.add(b[0]);
        }

        // Read the extensions
        uint16 ex_length = new uint16(this);
        for (long i = 0; i < ex_length.value(); i++) {
            msg.extensions.add(new Extension(this));
        }

        return msg;
    }
}
