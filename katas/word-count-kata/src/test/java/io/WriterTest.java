package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;

class WriterTest {

    PrintStream mockedOut = Mockito.mock(PrintStream.class);
    Writer cut;

    @BeforeEach
    void setup() {
        cut = new Writer(mockedOut);
    }

    @Test
    void shouldPrintResult_whenAskedToDoSo() throws IOException {
        cut.write("hello world");

        verify(mockedOut).print("hello world");
    }

}