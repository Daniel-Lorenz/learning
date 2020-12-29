package io;

import io.vavr.control.Try;

import java.io.*;

public class Reader {
    private final InputStream in;

    public Reader(InputStream in) {
        this.in = in;
    }

    public Try<String> readLine(){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            return Try.of(br::readLine);
        } catch (IOException e) {
            return Try.failure(e);
        }
    }
}
