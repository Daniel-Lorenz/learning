package com.daniellorenz.blog.bookshop;

import bookservice.BookService_;
import bookservice.Books_;
import bookshop.Books;
import carshop.Cars;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@ServiceName(BookService_.CDS_NAME)
public class BookshopHandler implements EventHandler {

    @On(event = CdsService.EVENT_READ, entity = Books_.CDS_NAME)
    public void proposeMapAsResult(CdsReadEventContext context) {
        Map<String, Object> dummy = new HashMap<>();
        dummy.put(Cars.COLOR, "red");
        dummy.put(Books.AUTHOR, "Daniel");
        dummy.put(Books.TITLE, "Title");
        dummy.put(Books.ID, UUID.randomUUID().toString());

        Map<String, Object> dummy2 = new HashMap<>();
        dummy2.put(Books.TITLE, "Title 2");
        dummy2.put(Books.ID, UUID.randomUUID().toString());

        context.setResult(List.of(dummy, dummy2));
        context.setCompleted();
    }
}
