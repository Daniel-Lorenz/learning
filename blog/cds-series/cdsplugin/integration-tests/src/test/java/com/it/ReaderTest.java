package com.it;

import com.input.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReaderTest {

    @Autowired
    Reader reader;

    @Test
    void notNull(){
        Assertions.assertNotNull(reader);
        reader.read("text");
    }
}
