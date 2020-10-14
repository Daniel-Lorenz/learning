package com.daniellorenz.cashbook.statement;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private final double carryover;
    private final List<StatementItem> statementItems;

    Statement(double carryover, List<StatementItem> items) {
        this.carryover = carryover;
        statementItems =  List.copyOf(items);
    }

    Statement(List<StatementItem> items) {
        this.carryover = 0.0;
        statementItems = List.copyOf(items);
    }

    Statement() {
        this.carryover = 0.0;
        statementItems = new ArrayList<>();
    }

    public double getCarryover() {
        return carryover;
    }

    public List<StatementItem> getStatementItems() {
        return List.copyOf(statementItems);
    }
}
