namespace udpated.schema.sql;

using {Country, managed, cuid } from '@sap/cds/common';


entity Clients : cuid {
    naturalperson : Boolean;
    address : Association to many Client2Addresses on address.client = $self;
    
}

entity Client2Addresses {
    purpose : String enum{
        ![billing only];
        ![postal only];
        ![residential];
    };
    key client : Association to one Clients;
    key address : Association to one Addresses;
}

entity Addresses : cuid {
    zipcode : ZIPCode;
    country : Country;
    street : String;    
}

type ZIPCode : String;