package com.daniellorenz.blog.bookshop;

import bookservice.BookService_;
import bookservice.Books_;
import carshop.Cars;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import java.util.*;

@ServiceName(BookService_.CDS_NAME)
public class BookshopHandler {


    @On(event = CdsService.EVENT_READ, entity = Books_.CDS_NAME)
    public void proposeMapAsResult(CdsReadEventContext context){
        Map<String, Object> dummy = new HashMap<>();
        dummy.put(Cars.COLOR, "red");

        context.setResult(Collections.singletonList(dummy));
    }
}
