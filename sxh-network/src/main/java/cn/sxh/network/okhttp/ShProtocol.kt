package cn.sxh.network.okhttp

import java.io.IOException

enum class ShProtocol(private val protocol: String) {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    QUIC("quic");

    override fun toString(): String {
        return protocol
    }

    companion object {
        @Throws(IOException::class)
        operator fun get(protocol: String): ShProtocol {
            // Unroll the loop over values() to save an allocation.
            if (protocol == HTTP_1_0.protocol) return HTTP_1_0
            if (protocol == HTTP_1_1.protocol) return HTTP_1_1
            if (protocol == HTTP_2.protocol) return HTTP_2
            if (protocol == SPDY_3.protocol) return SPDY_3
            if (protocol == QUIC.protocol) return QUIC
            throw IOException("Unexpected protocol: $protocol")
        }
    }
}