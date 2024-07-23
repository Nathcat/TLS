package com.nathcat.TLS.types;

public enum ExtensionType {
    server_name(new uint16()),
    max_fragment_length(new uint16(1)),
    status_request(new uint16(5)),
    supported_groups(new uint16(10)),
    signature_algorithms(new uint16(13)),
    use_srtp(new uint16(14)),
    heartbeat(new uint16(15)),
    application_layer_protocol_negotiation(new uint16(16)),
    signed_certificate_timestamp(new uint16(18)),
    client_certificate_type(new uint16(19)),
    server_certificate_type(new uint16(20)),
    padding(new uint16(21)),
    RESERVED(new uint16(40)),

    pre_shared_key(new uint16(41)),
    early_data(new uint16(42)),
    supported_versions(new uint16(43)),
    cookie(new uint16(44)),
    psk_key_exchange_modes(new uint16(45)),
    RESERVED2(new uint16(46)),

    certificate_authorities(new uint16(47)),
    oid_filters(new uint16(48)),
    post_handshake_auth(new uint16(49)),
    signature_algorithms_cert(new uint16(50)),
    key_share(new uint16(51))
    ;

    public final uint16 value;
    ExtensionType(uint16 i) {
        this.value = i;
    }
}
