package io;

import io.vavr.control.Try;

import java.io.InputStream;

public class InputProvider {
    private final InputStream in;
    public InputProvider(InputStream in) {
        this.in = in;
    }

    public Try<String> readAll(){
        return Try.of(() ->new String(in.readAllBytes()));
    }
}
