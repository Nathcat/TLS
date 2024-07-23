package com.nathcat.TLS;

import com.nathcat.TLS.Messages.ClientHello;
import com.nathcat.TLS.Messages.HandshakeMessage;
import com.nathcat.TLS.Messages.TLSPlainText;
import com.nathcat.TLS.types.uint24;
import com.nathcat.TLS.types.uint8;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TLSInputStreamTest {
    @Test
    public void testReadClientHello() throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        Socket conn = ss.accept();

        TLSInputStream in = new TLSInputStream(conn.getInputStream(), new TLSPlainTextHandler() {
            @Override
            public void invalid(TLSPlainText r) {

            }

            @Override
            public void change_cipher_spec(TLSPlainText r) {

            }

            @Override
            public void alert(TLSPlainText r) {

            }

            @Override
            public void handshake(TLSPlainText r) throws IOException {
                ByteArrayInputStream is = new ByteArrayInputStream(r.fragment);
                uint8 type = new uint8(is);
                uint24 length = new uint24(is);

                if (type.compare(HandshakeMessage.Type.client_hello.value) == 0) {
                    TLSInputStream t = new TLSInputStream(is);
                    ClientHello msg = t.readClientHello();
                    msg.messageType = type;
                    msg.length = length;
                    t.close();

                    System.out.println("Extracted client hello!");
                    assertEquals(0, msg.messageType.compare(HandshakeMessage.Type.client_hello.value));

                    System.out.println(msg.length);
                    System.out.println(msg.legacy_version.value());
                    System.out.println(Arrays.toString(msg.random));
                }

                is.close();
            }

            @Override
            public void application_data(TLSPlainText r) {

            }
        });
        in.handlePlainText();

        in.close();
        conn.close();
        ss.close();
    }
}