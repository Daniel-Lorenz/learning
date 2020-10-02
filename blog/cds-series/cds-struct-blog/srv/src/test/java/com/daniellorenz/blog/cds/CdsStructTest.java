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

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CdsStructTest {

    @Nested
    class StructAccessTests {
        @Test
        void createBookWithStruct() {

            bookshop.Books book = Struct.create(bookshop.Books.class);
            Assertions.assertNotNull(book);
            Assertions.assertTrue(book.isEmpty());
        }

        @Test
        void createBookWithStaticMethod() {
            bookshop.Books book = bookshop.Books.create();
            Assertions.assertNotNull(book);
            Assertions.assertTrue(book.isEmpty());
        }

        @Test
        void convertMapToBook() {
            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put(Books.AUTHOR, "Daniel Lorenz");
            bookMap.put(Books.TITLE, "A short book about nothing");
            Books book = Struct.access(bookMap).as(Books.class);

            Assertions.assertEquals(bookMap.get(Books.TITLE), book.getTitle());
            Assertions.assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
        }

        @Test
        void accessIsMutable() {
            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put(Books.AUTHOR, "Daniel Lorenz");
            bookMap.put(Books.TITLE, "A short book about nothing");
            Books book = Struct.access(bookMap).as(Books.class);
            bookMap.put(Books.AUTHOR, "Some other guy");

            Assertions.assertEquals(bookMap.get(Books.TITLE), book.getTitle());
            Assertions.assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
        }

        @Test
        void accessAsView() {
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
        void accessAsDifferentEntity() {
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
    }

    @Nested
    class StructStreamTests{

        @Test
        void testAsStream(){

            List<Books> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(randomBook());
            }

            List<Cars> result = Struct.stream(list).as(Cars.class).collect(Collectors.toList());
            list.remove(0);
            list.get(0).setTitle("Different");
            Assertions.assertEquals(100, result.size());
            Assertions.assertEquals(1, result.stream().filter(car -> car.get(Books.TITLE).toString().contentEquals("Different")).count());
        }
    }

    private Books randomBook() {
        Books book = Books.create();
        book.setId(UUID.randomUUID().toString());
        book.setAuthor("Daniel " + ThreadLocalRandom.current().nextInt());
        book.setTitle("Some book " + ThreadLocalRandom.current().nextInt());
        return book;
    }

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    class TestServiceEndpoints {
        public static final String PATH = "/odata/v4/BookService/Books";
        @Autowired
        private MockMvc mockMvc;

        @Test
        void customHandlerReturnsMap() throws Exception {

            String response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)).andReturn().getResponse().getContentAsString();
            System.out.println(response);
        }

    }

}
