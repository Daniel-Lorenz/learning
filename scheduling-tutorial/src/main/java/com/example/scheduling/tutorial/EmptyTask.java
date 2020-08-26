package com.example.scheduling.tutorial;

import java.time.LocalDateTime;

class EmptyTask implements Task {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public LocalDateTime dueDate() {
        //TODO: introcude own DateClass to handle empty tasks and stuff
        return null;
    }
}
