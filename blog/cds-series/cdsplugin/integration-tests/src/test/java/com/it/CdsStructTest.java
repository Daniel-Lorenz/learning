package com.it;

import com.sap.cds.Struct;
import my.bookshop.Books;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

public class CdsStructTest {

    @Test
    void testIterableFactory(){
        Books book1 = Books.create();
        book1.setId(1);
        Books book2 = Books.create();
        book2.setId(2);
        List<Books> input = List.of(book1, book2);

        List<catalogservice.Books> result = Struct.stream(input).as(catalogservice.Books.class).collect(Collectors.toList());

        Assertions.assertIterableEquals(input, result);
    }
}
