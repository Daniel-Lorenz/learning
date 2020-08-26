package com.daniellorenz.repository.repo;

import com.sap.cds.CdsData;
import com.sap.cds.Result;

public interface Repository {

    Result insert(CdsData data, Class<? extends CdsData> table);

	Result select(int id, Class<? extends CdsData> table);

}
