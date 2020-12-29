package io;

import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ReaderTest {

    private final String HELLO_WORLD = "hello world";
    Reader cut;
    BufferedInputStream in;


    @BeforeEach
    void setup() {
        var is = new ByteArrayInputStream(HELLO_WORLD.getBytes());
        in = new BufferedInputStream(is);
        cut = new Reader(in);
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnTrySuccess_whenReadingEverything() {

        Try<String> actual = cut.readLine();

        assertThat(actual.isSuccess()).isTrue();
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnExpectedString_whenReadingEverything() {

        Try<String> actual = cut.readLine();

        assertThat(actual.get()).isEqualTo(HELLO_WORLD);
    }

    @Test
    void givenReadingFromInputStreamFailsBecauseOfIoException_shouldReturnTryFailure_whenReadingEverything() throws IOException {
        in.close();

        Try<String> actual = cut.readLine();

        assertThat(actual.isFailure()).isTrue();
    }

}