package io;

import java.io.PrintStream;

public class Writer {
    private final PrintStream out;
    public Writer(PrintStream out) {
        this.out = out;
    }

    public void write(String msg) {
        out.print(msg);
    }
}
