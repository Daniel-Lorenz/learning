package io;

import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class InputProviderTest {

    InputProvider cut;
    InputStream mockedInputStream = Mockito.mock(InputStream.class);

    @BeforeEach
    void setup() {
        cut = new InputProvider(mockedInputStream);
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnTrySuccess_whenReadingEverything() throws IOException {
        given(mockedInputStream.readAllBytes()).willReturn("hello word".getBytes());

        Try<String> actual = cut.readAll();

        assertThat(actual.isSuccess()).isTrue();
    }

    @Test
    void givenReadingFromInputStreamIsSuccessful_shouldReturnExpectedString_whenReadingEverything() throws IOException {
        given(mockedInputStream.readAllBytes()).willReturn("hello world".getBytes());

        Try<String> actual = cut.readAll();

        assertThat(actual.get()).isEqualTo("hello world");
    }

    @Test
    void givenReadingFromInputStreamFailsBecauseOfIoException_shouldReturnTryFailure_whenReadingEverything() throws IOException {
        given(mockedInputStream.readAllBytes()).willThrow(new IOException());

        Try<String> actual = cut.readAll();

        assertThat(actual.isFailure()).isTrue();
    }

}