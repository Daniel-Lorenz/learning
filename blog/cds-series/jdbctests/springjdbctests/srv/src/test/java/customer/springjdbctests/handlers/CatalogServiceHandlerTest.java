package customer.springjdbctests.handlers;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;

import cds.gen.catalogservice.Books;

public class CatalogServiceHandlerTest {

	private CatalogServiceHandler handler = new CatalogServiceHandler();
	private Books book = Books.create();

	@BeforeEach
	public void prepareBook() {
		book.setTitle("title");
	}

	@Test
	public void testDiscount() {
		book.setStock(500);
		handler.discountBooks(Stream.of(book));
		Assertions.assertEquals("title (discounted)", book.getTitle());
	}

	@Test
	public void testNoDiscount() {
		book.setStock(100);
		handler.discountBooks(Stream.of(book));
		Assertions.assertEquals("title", book.getTitle());
	}

	@Test
	public void testNoStockAvailable() {
		handler.discountBooks(Stream.of(book));
		Assertions.assertEquals("title", book.getTitle());
	}

}