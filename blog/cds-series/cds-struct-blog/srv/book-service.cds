using bookshop from '../db/bookshop.cds';

service BookService{

    entity Books as select from bookshop.Books{
        ID,
        title,
    };
};