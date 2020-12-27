package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;

class DisplayWriterTest {

    PrintStream mockedOut = Mockito.mock(PrintStream.class);
    DisplayWriter cut;

    @BeforeEach
    void setup() {
        cut = new DisplayWriter(mockedOut);
    }

    @Test
    void shouldPrintResult_whenAskedToDoSo() {
        cut.displayResult("hello world");

        verify(mockedOut).print("hello world");
    }

}