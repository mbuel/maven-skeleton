package com.seabirdscientific.skeleton;

import com.seabirdscientific.skeleton.interfaces.Standards;

public class Protocol implements Standards {
    private Standards protocol;
    public Protocol(Standards standards) {
        this.protocol = standards;
    }

    public String getProtocol() {
        return protocol.toString();
    }
}
