namespace my.bookshop;

entity Books {
  key ID : Integer;
  title  : String;
  stock  : Integer;

}

entity Foo  {
  key ID :UUID;
  title : String;
  books : Association to Books;
}

view Bar as select from Books book left join Foo foo on foo.books.ID = book.ID{
  book.ID,
  foo.title as footitle,
  book.title as booktitle
}
;

entity Bus as select
  book.ID,
  foo.title as footitle,
  book.title as booktitle
from Books book left join Foo foo on foo.books.ID =book.ID;