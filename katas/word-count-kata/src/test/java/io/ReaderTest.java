package io;

import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class ReaderTest {

    Reader cut;
    BufferedReader mockedBufferedReader = Mockito.mock(BufferedReader.class);

    @BeforeEach
    void setup() {
        cut = new Reader();
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnTrySuccess_whenReadingEverything() throws IOException {
        given(mockedBufferedReader.readLine()).willReturn("hello word");

        Try<String> actual = cut.readLine(mockedBufferedReader);

        assertThat(actual.isSuccess()).isTrue();
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnExpectedString_whenReadingEverything() throws IOException {
        given(mockedBufferedReader.readLine()).willReturn("hello world");

        Try<String> actual = cut.readLine(mockedBufferedReader);

        assertThat(actual.get()).isEqualTo("hello world");
    }

    @Test
    void givenReadingFromInputStreamFailsBecauseOfIoException_shouldReturnTryFailure_whenReadingEverything() throws IOException {
        given(mockedBufferedReader.readLine()).willThrow(new IOException());

        Try<String> actual = cut.readLine(mockedBufferedReader);

        assertThat(actual.isFailure()).isTrue();
    }

}