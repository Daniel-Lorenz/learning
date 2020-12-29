package io;

import java.io.IOException;
import java.io.OutputStream;

public class Writer {
    private final OutputStream out;

    public Writer(OutputStream out) {
        this.out = out;
    }

    public void write(String msg) throws IOException {
        out.write(msg.getBytes());
    }
}
