package io;

import io.vavr.control.Try;

import java.io.*;

public class Reader {

    public Try<String> readLine(InputStream in){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            return readLine(br);
        } catch (IOException e) {
            return Try.failure(e);
        }
    }

    public Try<String> readLine(BufferedReader br) {
        return Try.of(br::readLine);
    }
}
