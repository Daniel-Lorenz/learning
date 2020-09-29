package com.daniellorenz.blog.cds;

import bookshop.Books;
import bookshop.Titles;
import carshop.Cars;
import com.sap.cds.Struct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CdsStructTest {

    @Test
    void createBookWithStruct() {

        bookshop.Books book = Struct.create(bookshop.Books.class);
        Assertions.assertNotNull(book);
        Assertions.assertTrue(book.isEmpty());
    }

    @Test
    void createBookWithStaticMethod(){
        bookshop.Books book = bookshop.Books.create();
        Assertions.assertNotNull(book);
        Assertions.assertTrue(book.isEmpty());
    }

    @Test
    void convertMapToBook(){
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put(Books.AUTHOR, "Daniel Lorenz");
        bookMap.put(Books.TITLE, "A short book about nothing");
        Books book = Struct.access(bookMap).as(Books.class);

        Assertions.assertEquals(bookMap.get(Books.TITLE), book.getTitle());
        Assertions.assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
    }

    @Test
    void accessIsMutable(){
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put(Books.AUTHOR, "Daniel Lorenz");
        bookMap.put(Books.TITLE, "A short book about nothing");
        Books book = Struct.access(bookMap).as(Books.class);
        bookMap.put(Books.AUTHOR, "Some other guy");

        Assertions.assertEquals(bookMap.get(Books.TITLE), book.getTitle());
        Assertions.assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
    }

    @Test
    void accessAsView(){
        Books book = Books.create();
        book.setAuthor("Daniel Lorenz");
        book.setTitle("Some Title");
        book.setId(UUID.randomUUID().toString());

        Titles titleView = Struct.access(book).as(Titles.class);

        Assertions.assertEquals(book.getId(), titleView.getId());
        Assertions.assertEquals(book.getTitle(), titleView.getTitle());
        Assertions.assertEquals(book.getAuthor(), titleView.get(Books.AUTHOR));
    }

    @Test
    void accessAsDifferentEntity(){
        Books book = Books.create();
        book.setAuthor("Daniel Lorenz");
        book.setTitle("Some Title");
        book.setId(UUID.randomUUID().toString());

        Cars car = Struct.access(book).as(Cars.class);

        Assertions.assertEquals(book.getId(), car.getId());
        Assertions.assertEquals(book.getTitle(), car.get(Books.TITLE));
        Assertions.assertEquals(book.getAuthor(), car.get(Books.AUTHOR));

        Assertions.assertNull(car.getColor());
        Assertions.assertNull(car.getModel());
    }

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    class TestServiceEndpoints{
        @Autowired
        private MockMvc mockMvc;

        @Test
        void customHandlerReturnsMap(){

            mockMvc.perform(MockMvcRequestBuilders.get())
        }

        @Test
        void customHandlerReturnsMapWithMoreKeys(){

        }
    }

}
