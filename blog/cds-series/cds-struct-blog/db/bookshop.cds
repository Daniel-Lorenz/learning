namespace bookshop;

using {  cuid } from '@sap/cds/common';


entity Books : cuid{
    title   : String;
    author  : String;
}

view Titles as select from Books {
    key ID,
    title,
};