package com.example.scheduling.tutorial;

import java.time.LocalDateTime;

public class RecurringTask implements Task {

    LocalDateTime dueDate;

    public RecurringTask(final LocalDateTime dueDate) {
        this.dueDate = LocalDateTime.from(dueDate);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public LocalDateTime dueDate() {
        return dueDate;
    }
}
