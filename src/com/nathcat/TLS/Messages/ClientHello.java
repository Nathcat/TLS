package com.nathcat.TLS.Messages;

import com.nathcat.TLS.types.Extension;
import com.nathcat.TLS.types.Vector;
import com.nathcat.TLS.types.uint16;
import com.nathcat.TLS.types.uint8;

/**
 * ClientHello message as specified by <a href="https://datatracker.ietf.org/doc/html/rfc8446#autoid-21">RFC 8446</a>
 * @author Nathan Baines
 */
public class ClientHello extends HandshakeMessage {
    public uint16 legacy_version;
    public byte[] random = new byte[32];
    public Vector<Byte> legacy_session_id = new Vector<>(new uint8(), new uint8(32));
    public Vector<uint8[]> cipher_suites = new Vector<>(new uint16(2), new uint16(0xFFFF - 2));
    public Vector<Byte> legacy_compression_methods = new Vector<>(new uint8(1), new uint8(0xFF - 1));
    public Vector<Extension> extensions = new Vector<>(new uint16(8), new uint16(0XFFFF - 1));
}
