package com.daniellorenz.repository.repo;

import com.sap.cds.CdsData;
import com.sap.cds.Result;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.services.persistence.PersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersistenceServiceWrapper implements Repository {

    @Autowired
    PersistenceService db;

    @Override
    public Result insert(CdsData data, Class<? extends CdsData> table) {
        return db.run(Insert.into(table.getDeclaredAnnotation(CdsName.class).value()).entry(data));
    }

    @Override
    public Result select(int id, Class<? extends CdsData> table) {
        return db.run(Select.from(table.getDeclaredAnnotation(CdsName.class).value()).byId(id));
    }
    
}