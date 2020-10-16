package com.daniellorenz.blog.cds;

import bookshop.Books;
import bookshop.Titles;
import carshop.Cars;
import com.sap.cds.Struct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CdsStructTest {

    private Books randomBook() {
        Books book = Books.create();
        book.setId(UUID.randomUUID().toString());
        book.setAuthor("Daniel " + ThreadLocalRandom.current().nextInt());
        book.setTitle("Some book " + ThreadLocalRandom.current().nextInt());
        return book;
    }

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

            assertEquals(bookMap.get(Books.TITLE), book.getTitle());
            assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
        }

        @Test
        void accessIsMutable() {
            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put(Books.AUTHOR, "Daniel Lorenz");
            bookMap.put(Books.TITLE, "A short book about nothing");
            Books book = Struct.access(bookMap).as(Books.class);
            bookMap.put(Books.AUTHOR, "Some other guy");

            assertEquals(bookMap.get(Books.TITLE), book.getTitle());
            assertEquals(bookMap.get(Books.AUTHOR), book.getAuthor());
        }

        @Test
        void accessAsView() {
            Books book = Books.create();
            book.setAuthor("Daniel Lorenz");
            book.setTitle("Some Title");
            book.setId(UUID.randomUUID().toString());

            Titles titleView = Struct.access(book).as(Titles.class);

            assertEquals(book.getId(), titleView.getId());
            assertEquals(book.getTitle(), titleView.getTitle());
            assertEquals(book.getAuthor(), titleView.get(Books.AUTHOR));
            Assertions.assertSame(book, titleView);
        }

        @Test
        void accessAsDifferentEntity() {
            Books book = Books.create();
            book.setAuthor("Daniel Lorenz");
            book.setTitle("Some Title");
            book.setId(UUID.randomUUID().toString());

            Cars car = Struct.access(book).as(Cars.class);

            assertEquals(book.getId(), car.getId());
            assertEquals(book.getTitle(), car.get(Books.TITLE));
            assertEquals(book.getAuthor(), car.get(Books.AUTHOR));

            Assertions.assertNull(car.getColor());
            Assertions.assertNull(car.getModel());
        }
    }

    @Nested
    class StructStreamTests {

        @Test
        void testAsStream() {
            List<Books> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(randomBook());
            }

            List<Cars> result = Struct.stream(list)
                    .as(Cars.class)
                    .collect(Collectors.toList());
            list.remove(0);
            list.get(0).setTitle("Different");
            assertEquals(100, result.size());
            assertEquals(1, result.stream()
                    .map(car -> car.get(Books.TITLE).toString())
                    .filter("Different"::contentEquals)
                    .count());
        }
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

            mockMvc.perform(MockMvcRequestBuilders.get(PATH))
                    .andExpect(jsonPath("$.value.[0].title", Matchers.is("Title")))
                    .andExpect(jsonPath("$.value.[0].ID").isNotEmpty())
                    .andExpect(jsonPath("$.value.[0].color").doesNotExist())
                    .andExpect(jsonPath("$.value.[0].author").doesNotExist())
                    .andExpect(jsonPath("$.value.[1].title", Matchers.is("Title 2")))
                    .andExpect(jsonPath("$.value.[1].ID").isNotEmpty())
                    .andDo(MockMvcResultHandlers.print());
        }

    }

}
