package com.example.scheduling.tutorial;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

class TaskTests {
	LocalDateTime dueDate = LocalDateTime.parse("2020-02-20T06:30:00");

	@Test
	void emptyTaskIsEmpty(){
		Task task = new EmptyTask();
		Assertions.assertTrue(task.isEmpty());
	}

	@Test
	void recurringTaskIsNotEmpty(){
		Task task = new RecurringTask(dueDate);
		Assertions.assertFalse(task.isEmpty());
	}

	@Test
	void recurringTaskHasDueDate(){
		Task task = new RecurringTask(dueDate);
		Assertions.assertEquals(dueDate, task.dueDate());
	}

}
