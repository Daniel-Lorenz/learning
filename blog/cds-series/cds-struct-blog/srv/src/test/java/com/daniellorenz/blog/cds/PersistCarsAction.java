package com.daniellorenz.blog.cds;

import carshop.Cars;

public class PersistCarsAction implements TerminalAction<carshop.Cars> {

    @Override
    public void execute(Cars data) {
        System.out.println(data);
    }

    @Override
    public Class<Cars> getIntype() {
        return Cars.class;
    }
}
