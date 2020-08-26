package com.example.scheduling.tutorial;

import java.time.LocalDateTime;

public interface Task {

    boolean isEmpty();

    LocalDateTime dueDate();
}
