package com.nathcat.TLS;

import javax.net.ssl.SSLContext;
import java.net.Socket;

/**
 * A TCP socket extension which implements TLS over the base TCP protocol.
 */
public class TLSServer implements TLSSocket {
    private final Socket socket;

    public TLSServer(Socket socket, SSLContext sslContext) {
        this.socket = socket;
    }


    @Override
    public void doHandshake() {

    }
}
