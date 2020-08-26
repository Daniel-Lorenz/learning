package com.daniellorenz.repository.repo;

import java.util.*;

import com.sap.cds.CdsData;
import com.sap.cds.Result;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.utils.ResultUtils;

public class InMemoryDb implements Repository {

    Map<Class<? extends CdsData>, Map<Integer, Map<String, Object>>> db = new HashMap<>();

    @Override
    public Result insert(CdsData data, Class<? extends CdsData> tableName) {
        List<CdsData> result = new ArrayList<>();

        if(!db.containsKey(tableName)){
            Map<Integer, Map<String, Object>> newTable = new HashMap<>();
            db.put(tableName, newTable);
        }
        insertDataIntoTable(tableName, data);
        result.add(data);
        return ResultUtils.convert(result);
    }

    @Override
    public Result select(int id, Class<? extends CdsData> table) {
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(new HashMap<String, Object>(db.get(table).get(id)));
        return ResultUtils.convert(result);
    }

    private void insertDataIntoTable(Class<? extends CdsData> tableName, CdsData data){
        if(!data.containsKey("ID")){
            Map<Integer, Map<String, Object>> table = db.get(tableName);
            Integer potentialId = (int) Math.random()*Integer.MAX_VALUE;
            while(table.containsKey(potentialId)){
                potentialId = (int) Math.random()*Integer.MAX_VALUE;
            }
            data.put("ID", potentialId);
        }

        if(db.get(tableName).containsKey((int) data.get("ID"))){
            throw new ServiceException("Primary key constraint issue when INSERTING into " + tableName.getSimpleName());
        }
        db.get(tableName).put((int) data.get("ID"), Map.copyOf(data));
    }

}
