package io;

import java.io.PrintStream;

public class DisplayWriter {
    private final PrintStream out;
    public DisplayWriter(PrintStream out) {
        this.out = out;
    }

    public void displayResult(String result) {
        out.print(result);
    }
}
